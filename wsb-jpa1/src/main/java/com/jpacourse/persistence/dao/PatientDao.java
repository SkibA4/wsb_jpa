package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    public void createVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription);
    public List<PatientEntity> findPatientsByLastName(String lastName);
    public List<PatientEntity> findPatientsWithVisitsAbove(Long threshold);
    public List<PatientEntity> findPatientsWithHeightAbove(Long height);
}
