package adk.lims.user.patient.controller;

import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static adk.lims.core.constants.MessageMapping.Patient.PROBLEM_WITH_SAVING_PATIENT;
import static adk.lims.core.constants.URLMapping.Patient.PATIENT_BASE;
import static adk.lims.core.constants.URLMapping.REGISTER;

@Controller
@RequestMapping(PATIENT_BASE)
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(REGISTER)
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientBingingModel model){
        Patient savedPatient = this.patientService.registerPatient(model);

        if(savedPatient == null)
            return new ResponseEntity<>(PROBLEM_WITH_SAVING_PATIENT, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(savedPatient, HttpStatus.OK);
    }
}
