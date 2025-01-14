package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping("/patient/{id}")
    public PatientTO getPatientById(Long patientId) {
        return patientService.findById(patientId);
    }

    @GetMapping("/patient")
    public List<PatientTO> getPatientsByLastName(@RequestParam(value = "lastName", required = true) String lastName) {
        return patientService.findPatientsByLastName(lastName);
    }

    @GetMapping("/patient/visits/{visitsThreshold}")
    public List<PatientTO> getPatientsWithVisitsAbove(@PathVariable Long visitsThreshold) {
        return patientService.findPatientsWithVisitsAbove(visitsThreshold);
    }

    @GetMapping("/patient/height/{height}")
    public List<PatientTO> getPatientsWithHeightAbove(@PathVariable Long height) {
        return patientService.findPatientsWithHeightAbove(height);
    }
}
