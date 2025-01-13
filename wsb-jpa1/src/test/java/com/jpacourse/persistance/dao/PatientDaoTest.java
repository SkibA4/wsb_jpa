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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Test
    @Transactional
    public void shouldCreateVisitForPatient() {
        Long patientId = 2L;
        Long doctorId = 2L;
        LocalDateTime visitDate = LocalDateTime.of(2024, 12, 10, 14, 0);
        String visitDescription = "Post-surgery checkup";

        patientDao.createVisitForPatient(patientId, doctorId, visitDate, visitDescription);

        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotNull();
        assertThat(patient.getVisits().size()).isEqualTo(2);

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

    @Test
    public void shouldReturnPatientsWithGivenLastName() {
        String lastName = "Doe";

        List<PatientEntity> patients = patientDao.findPatientsByLastName(lastName);

        assertThat(patients).allMatch(patientEntity -> patientEntity.getLastName().equals(lastName));
    }

    @Test
    public void shouldReturnPatientsWithVisitsCountAbove() {
        long visitsCount = 3L;

        List<PatientEntity> patients = patientDao.findPatientsWithVisitsAbove(visitsCount);

        assertThat(patients).allMatch(patientEntity -> patientEntity.getVisits().size() > visitsCount);
    }

    @Test
    public void shouldReturnPatientsWithHeightAbove() {
        Long height = 170L;

        List<PatientEntity> patients = patientDao.findPatientsWithHeightAbove(height);

        assertThat(patients).allMatch(patientEntity -> patientEntity.getHeight() > height);
    }

    @Test
    @Transactional
    public void shouldReturnOptimisticLockException() {
        EntityManager em1 = entityManagerFactory.createEntityManager();
        em1.getTransaction().begin();
        PatientEntity patientInstance1 = em1.find(PatientEntity.class, 1L);

        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();
        PatientEntity patientInstance2 = em2.find(PatientEntity.class, 1L);

        patientInstance1.setPatientNumber("Próba aktualizacji numeru 1");
        em1.merge(patientInstance1);
        em1.getTransaction().commit();
        em1.close();

        patientInstance2.setPatientNumber("Próba aktualizacji numeru 2");

        try {
            em2.merge(patientInstance2);
            em2.getTransaction().commit();
            fail("Oczekiwano OptimisticLockException, ale taki nie wystąpił");
        } catch (RollbackException e) {
            assertTrue(e.getCause() instanceof OptimisticLockException);
        } finally {
            em2.close();
        }
    }
}
