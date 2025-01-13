package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (!patientDao.exists(id)) {
            throw new IllegalArgumentException("Patient with id: " + id + " does not exist.");
        }
        patientDao.delete(id);
    }

    @Override
    public List<PatientTO> findPatientsByLastName(String lastName) {
        return patientDao.findPatientsByLastName(lastName)
                .stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findPatientsWithVisitsAbove(Long threshold) {
        return patientDao.findPatientsWithVisitsAbove(threshold)
                .stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findPatientsWithHeightAbove(Long height) {
        return patientDao.findPatientsWithHeightAbove(height)
                .stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
