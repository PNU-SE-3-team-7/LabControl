package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.api.dto.AssignmentPreviewDto;
import com.pnu.lab.control.labcontrol.api.dto.AssignmentSearchRequest;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.domain.QAssignment;
import com.pnu.lab.control.labcontrol.domain.QSubmission;
import com.pnu.lab.control.labcontrol.utils.QueryDslFactory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends BaseSearchRepository<Assignment> {
    QAssignment qAssignment = QAssignment.assignment;

    default List<AssignmentPreviewDto> getList(AssignmentSearchRequest request, String userId) {
        BooleanBuilder predicate = new BooleanBuilder();
        Optional.ofNullable(request.getCourseId())
                .ifPresentOrElse(courseId -> predicate.or(qAssignment.courseId.eq(courseId)),
                        () -> predicate.or(qAssignment.parentId.eq(request.getAssignmentId())));

        QSubmission qSubmission = QSubmission.submission;
        return QueryDslFactory.getQueryFactory()
                .select(Projections.bean(AssignmentPreviewDto.class, qAssignment.id, qAssignment.type, qAssignment.dueDate, qSubmission.grade, qAssignment.maxGrade))
                .from(qAssignment)
                .leftJoin(qSubmission).on(qSubmission.assignmentId.eq(qAssignment.id))
                .where(predicate, qSubmission.userId.eq(userId))
                .fetch();
    }

    @Query(value = """
            with recursive r as (select id
                                 from lab_control.assignment
                                 where parent_id = ?1
                                 union all
                                 select c.id
                                 from lab_control.assignment as c
                                          join r
                                               on c.parent_id = r.id)
            select *
            from r;
            """, nativeQuery = true)
    List<String> getLowerAssignmentIds(String parentId);

    @Override
    default EntityPathBase<Assignment> getQEntity() {
        return qAssignment;
    }
}
