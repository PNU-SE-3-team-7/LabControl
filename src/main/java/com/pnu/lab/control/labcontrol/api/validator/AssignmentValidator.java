package com.pnu.lab.control.labcontrol.api.validator;


import com.pnu.lab.control.labcontrol.constant.AssignmentType;
import com.pnu.lab.control.labcontrol.constant.GradeType;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.exception.ValidationException;
import com.pnu.lab.control.labcontrol.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AssignmentValidator implements Validator {

    private final AssignmentService assignmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Assignment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assignment assignment = (Assignment) target;

        if (assignment.isChild() && !assignmentService.existsById(assignment.getParentId())) {
            throw new ValidationException("Undefined parent Assignment");
        }

        if (assignment.getGradeType() == GradeType.DISCRETE) {
            if (assignment.getThreshold() <= 0) {
                throw new ValidationException("threshold cannot be negative or null while grade type is Discrete");
            }
        }

        if (assignment.getType() == AssignmentType.MATERIAL) {
            if (Objects.isNull(assignment.getDueDate())) {
                throw new ValidationException("due date must be null if assignment type is Material");
            }
        }
    }
}
