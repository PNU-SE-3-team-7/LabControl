package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.ChangeGradeSubmissionRequest;
import com.pnu.lab.control.labcontrol.constant.AutoType;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;
import com.pnu.lab.control.labcontrol.repository.SubmissionRepository;
import com.pnu.lab.control.labcontrol.service.event.AssignmentDeleteEvent;
import com.pnu.lab.control.labcontrol.service.event.SubmissionUpdateGradeEvent;
import com.pnu.lab.control.labcontrol.service.event.UserLeaveCourseEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService extends AbstractSearchService<Submission> {

    private final SubmissionRepository repository;
    private final AssignmentService assignmentService;
    private final SubmissionCommentService commentService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Submission getByAssignmentId(String assignmentId) {
        return repository.getByAssignmentId(assignmentId);
    }

    public Submission changeGrade(ChangeGradeSubmissionRequest request) {
        Submission submission = findOne(request.getSubmissionId());
        int oldGrade = submission.getGrade();
        submission.setGrade(request.getGrade());
        applicationEventPublisher.publishEvent(new SubmissionUpdateGradeEvent(oldGrade, submission));
        return update(submission);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void onSubmissionGradeUpdate(SubmissionUpdateGradeEvent event) {
        Submission submission = event.getSubmission();
        if (submission.getGrade() == event.getOldGrade()) {
            return;
        }
        if (!submission.isChild()) {
            return;
        }

        String parentId = submission.getParentId();
        Submission parent = findOne(parentId);
        Assignment parentAssignment = assignmentService.findOne(parent.getAssignmentId());

        List<Submission> childSubmissions = repository.getSubmissionsByParentId(parentId);
        if (CollectionUtils.isEmpty(childSubmissions)) {
            return;
        }

        List<String> childAssignmentIds = childSubmissions.stream().map(Submission::getAssignmentId).collect(Collectors.toList());
        List<Assignment> childrenAssignments = assignmentService.findAll(childAssignmentIds);
        Map<String, Assignment> assignmentMap = childrenAssignments.stream().collect(Collectors.toMap(Assignment::getId, Function.identity()));

        int weightSum = childrenAssignments.stream().mapToInt(Assignment::getWeight).sum();
        int gradeSum = childSubmissions.stream()
                .map(childSubmission -> {
                    Assignment assignment = assignmentMap.get(childSubmission.getAssignmentId());
                    int grade = childSubmission.getGrade();
                    int maxGrade = assignment.getMaxGrade();
                    int weight = assignment.getWeight();

                    return (grade / maxGrade) * (weight / weightSum);
                })
                .reduce(Integer::sum)
                .map(sum -> sum * parentAssignment.getMaxGrade())
                .orElse(0);

        int oldGrade = parent.getGrade();
        parent.setAutoGrade(gradeSum);
        if (parentAssignment.getAutoType().equals(AutoType.AUTO)) {
            parent.setGrade(gradeSum);
        }

        onSubmissionGradeUpdate(new SubmissionUpdateGradeEvent(oldGrade, parent));
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(fallbackExecution = true)
    public void onAssignmentDelete(AssignmentDeleteEvent event) {
        List<String> lowerAssignmentIds = event.getLowerAssignmentIds();
        List<String> submissionIds = repository.getSubmissionIdsByAssignmentIds(lowerAssignmentIds);

        commentService.deleteByPrimaryObjectIds(submissionIds);
        repository.deleteAllById(submissionIds);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(fallbackExecution = true)
    public void onUserCourseLeave(UserLeaveCourseEvent event) {
        List<String> submissionIds = repository.getSubmissionIdsByCourseIdAndUserId(event.getCourseId(), event.getUserId());

        commentService.deleteByPrimaryObjectIds(submissionIds);
        repository.deleteAllById(submissionIds);
    }

    @Override
    public void delete(Submission entity) {
        commentService.deleteByPrimaryObjectId(entity.getId());
        super.delete(entity);
    }

    @Override
    public Class<Submission> getType() {
        return Submission.class;
    }

    @Override
    public BaseSearchRepository<Submission> getRepository() {
        return repository;
    }
}
