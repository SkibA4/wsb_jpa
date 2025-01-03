package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    @Transactional
    public void shouldCreateVisitForPatient() {
        // Given
        Long patientId = 1L; // Jane Doe
        Long doctorId = 1L;  // John Doe
        LocalDateTime visitDate = LocalDateTime.of(2024, 12, 10, 14, 0);
        String visitDescription = "Post-surgery checkup";

        // When
        patientDao.createVisitForPatient(patientId, doctorId, visitDate, visitDescription);

        // Then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotNull();
        assertThat(patient.getVisits().size()).isEqualTo(2);

        // Sprawdzamy szczegóły dodanej wizyty
        VisitEntity newVisit = patient.getVisits().stream()
                .filter(v -> v.getDescription().equals(visitDescription))
                .findFirst()
                .orElse(null);

        assertThat(newVisit).isNotNull();
        assertThat(newVisit.getDescription()).isEqualTo(visitDescription);
        assertThat(newVisit.getTime()).isEqualTo(visitDate);
        assertThat(newVisit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(newVisit.getPatient().getId()).isEqualTo(patientId);
    }
}
