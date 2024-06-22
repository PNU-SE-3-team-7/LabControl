package com.pnu.lab.control.labcontrol.api.validator;


import com.pnu.lab.control.labcontrol.constant.AssignmentType;
import com.pnu.lab.control.labcontrol.constant.GradeType;
import com.pnu.lab.control.labcontrol.domain.Assignment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.security.InvalidParameterException;
import java.util.Objects;

@Component
public class AssignmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Assignment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assignment assignment = (Assignment) target;

        if (Objects.requireNonNull(assignment.getGradeType()) == GradeType.DISCRETE) {
            if (assignment.getThreshold() <= 0) {
                throw new InvalidParameterException("threshold cannot be negative or null while grade type is Discrete");
            }
        }
        if (assignment.getType() == AssignmentType.MATERIAL) {
            if (assignment.getDueDate() != null) {
                throw new InvalidParameterException("due date must be null if assignment type is Material");
            }
        }
    }
}
