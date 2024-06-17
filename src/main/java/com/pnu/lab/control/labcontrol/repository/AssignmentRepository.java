package com.pnu.lab.control.labcontrol.repository;

import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.domain.QAssignment;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends BaseSearchRepository<Assignment> {
    QAssignment qAssignment = QAssignment.assignment;
    @Override
    default EntityPathBase<Assignment> getQEntity() {
        return qAssignment;
    }
}
