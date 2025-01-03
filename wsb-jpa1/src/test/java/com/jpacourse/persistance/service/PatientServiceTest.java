package com.jpacourse.persistance.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.enums.Gender;
import com.jpacourse.persistence.enums.TreatmentType;
import com.jpacourse.service.DoctorService;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private DoctorService doctorService;


    //remove patient with id 1 from data.sql
    @Test
    public void removePatientCascadeTest() {

        Long patientId = 1L;
        Long visitId = 1L;
        Long doctorId = 1L;

        var patientBeforeDeletion = patientService.findById(patientId);

        assertThat(patientBeforeDeletion).isNotNull();

        patientService.deleteById(patientId);

        var visit = visitService.findById(visitId);
        assertThat(visit).isNull();

        var doctor = doctorService.findById(doctorId);
        assertThat(doctor).isNotNull();
    }

    @Test
    public void findPatientByIdShouldReturnCorrectTO() {
        Long patientId = 1L;

        PatientTO patientTO = patientService.findById(patientId);

        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);
        assertThat(patientTO.getFirstName()).isEqualTo("Jane");
        assertThat(patientTO.getLastName()).isEqualTo("Doe");
        assertThat(patientTO.getTelephoneNumber()).isEqualTo("456789123");
        assertThat(patientTO.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(patientTO.getPatientNumber()).isEqualTo("PAT001");
        assertThat(patientTO.getDateOfBirth()).isEqualTo(LocalDate.of(1985, 6, 15));
        assertThat(patientTO.getGender()).isEqualTo(Gender.FEMALE);

        assertThat(patientTO.getAddress()).isNotNull();
        assertThat(patientTO.getAddress().getCity()).isEqualTo("Warsaw");
        assertThat(patientTO.getAddress().getAddressLine1()).isEqualTo("Main Street 10");
        assertThat(patientTO.getAddress().getAddressLine2()).isEqualTo("Apt 101");
        assertThat(patientTO.getAddress().getPostalCode()).isEqualTo("00-001");

        assertThat(patientTO.getVisits()).isNotNull().isNotEmpty();
        assertThat(patientTO.getVisits().size()).isEqualTo(1);
        VisitTO visitTO = patientTO.getVisits().get(0);
        assertThat(visitTO.getDescription()).isEqualTo("Checkup appointment");
        assertThat(visitTO.getTime()).isEqualTo(LocalDateTime.of(2024, 12, 5, 9, 30));
        assertThat(visitTO.getDoctorFirstName()).isEqualTo("John");
        assertThat(visitTO.getDoctorLastName()).isEqualTo("Doe");
        assertThat(visitTO.getTreatmentTypes()).containsExactly(TreatmentType.RTG);
    }

}