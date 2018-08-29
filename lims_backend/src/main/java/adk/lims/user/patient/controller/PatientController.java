package adk.lims.user.patient.controller;

import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.model.view.PatientConsultationViewModel;
import adk.lims.consultation.service.ConsultationService;
import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static adk.lims.core.constants.URLMapping.Consultation.CONSULTATION_BASE;
import static adk.lims.core.constants.URLMapping.Patient.PATIENT_BASE;
import static adk.lims.core.constants.URLMapping.REGISTER;

@Controller
@RequestMapping(PATIENT_BASE)
public class PatientController {

    private final PatientService patientService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientController(PatientService patientService,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientService = patientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = REGISTER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientBingingModel model) {
        Patient savedPatient = this.patientService.registerPatient(model);

        if (savedPatient == null) {
            return new ResponseEntity<>(new HashMap<String, Object>() {{
                put("success", false);
                put("message", "Problem with register patient");
            }}, HttpStatus.BAD_REQUEST);
        }

        String pass = this.bCryptPasswordEncoder.encode(savedPatient.getUser().getPassword());

        return new ResponseEntity<>(new HashMap<String, Object>() {{
            put("success", true);
            put("patientId", savedPatient.getId());
            put("patientPass", pass);
        }}, HttpStatus.OK);
    }

    @GetMapping(value =CONSULTATION_BASE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getPatientConsultations(){
        Patient currentPatient = this.patientService.getCurrentPatient();

        List<Consultation> allConsultations = currentPatient.getConsultations();
        List<PatientConsultationViewModel> result = new ArrayList<>();

        for (Consultation currentConsultation : allConsultations) {
            PatientConsultationViewModel cons = new PatientConsultationViewModel(currentConsultation.getAnalysis().getName(),
                    currentConsultation.getHourOfConsultation(),
                    currentConsultation.getDateOfConsultation());
            result.add(cons);
        }


        return new ResponseEntity<>(new HashMap<String, Object>() {{
            put("consultations", result);
        }}, HttpStatus.OK);
    }
}
