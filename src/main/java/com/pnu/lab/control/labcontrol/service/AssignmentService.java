package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.domain.Assignment;
import com.pnu.lab.control.labcontrol.repository.AssignmentRepository;
import com.pnu.lab.control.labcontrol.repository.BaseSearchRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService extends AbstractSearchService<Assignment> {

    private final AssignmentRepository repository;

    @Override
    public Class<Assignment> getType() {
        return Assignment.class;
    }

    @Override
    public BaseSearchRepository<Assignment> getRepository() {
        return repository;
    }
}
