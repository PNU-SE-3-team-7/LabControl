package com.pnu.lab.control.labcontrol.api.validator;

import com.pnu.lab.control.labcontrol.domain.Submission;
import com.pnu.lab.control.labcontrol.exception.ValidationException;
import com.pnu.lab.control.labcontrol.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SubmissionValidator implements Validator {

    private final SubmissionService submissionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Submission.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Submission submission = (Submission) target;

        if (submission.isChild() && !submissionService.existsById(submission.getParentId())) {
            throw new ValidationException("Parent Submission does not exist");
        }
    }
}
