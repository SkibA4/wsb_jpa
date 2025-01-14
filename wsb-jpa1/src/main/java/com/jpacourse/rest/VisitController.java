package com.jpacourse.rest;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitController {

    @Autowired
    VisitService visitService;

    @GetMapping("/visit/{patientId}")
    public List<VisitTO> getVisitsByPatientId(@PathVariable  Long patientId) {
        return visitService.findVisitsByPatientId(patientId);
    }
}
