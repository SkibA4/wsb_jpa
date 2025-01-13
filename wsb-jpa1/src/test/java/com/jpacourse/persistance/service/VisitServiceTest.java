package com.jpacourse.persistance.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.VisitService;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {

    @Autowired
    VisitService visitService;

    @Autowired
    PatientDao patientDao;

    @Test
    public void shouldReturnAllPatientVisitsByPatientId() {
        long id = 1;

        PatientEntity patient = patientDao.findOne(id);

        assertThat(patient).isNotNull();
        int visitsSize = patient.getVisits().size();

        List<VisitTO> visits = visitService.findVisitsByPatientId(id);

        assertThat(visits.size()).isEqualTo(visitsSize);
    }
}
