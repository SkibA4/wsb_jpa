package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    public void createVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription) {

        var patient = findOne(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " not found");
        }

        var doctor = doctorDao.findOne(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " not found");
        }

        VisitEntity visit = new VisitEntity();
        visit.setDescription(visitDescription);
        visit.setTime(visitDate);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);
    }
}
