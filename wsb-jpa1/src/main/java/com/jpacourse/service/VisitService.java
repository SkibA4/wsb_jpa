package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface VisitService {
    public VisitTO findById(Long id);
    public List<VisitTO> findVisitsByPatientId(Long id);
}
