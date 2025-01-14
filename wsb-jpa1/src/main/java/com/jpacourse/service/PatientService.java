package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;

import java.util.List;

public interface PatientService {
    public PatientTO findById(final Long id);
    public List<PatientTO> findPatientsByLastName(final String lastName);
    public List<PatientTO> findPatientsWithVisitsAbove(Long threshold);
    public List<PatientTO> findPatientsWithHeightAbove(Long height);
    void deleteById(Long id);
}
