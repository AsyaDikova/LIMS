package adk.lims.user.patient.controller;

import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.model.view.PatientConsultationViewModel;
import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static adk.lims.core.constants.MessageMapping.Patient.PROBLEM_WITH_SAVING_PATIENT;
import static adk.lims.core.constants.URLMapping.Consultation.CONSULTATION_BASE;
import static adk.lims.core.constants.URLMapping.IS_EXIST;
import static adk.lims.core.constants.URLMapping.Patient.PATIENT_BASE;
import static adk.lims.core.constants.URLMapping.REGISTER;

@Controller
@RequestMapping(PATIENT_BASE)
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = REGISTER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientBingingModel model) {
        Patient savedPatient = this.patientService.registerPatient(model);

        if (savedPatient == null) {
            return new ResponseEntity<>(new HashMap<String, Object>() {{
                put("success", false);
                put("message", PROBLEM_WITH_SAVING_PATIENT);
            }}, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new HashMap<String, Object>() {{
            put("success", true);
            put("patientId", savedPatient.getId());
            put("patientPassword", model.getPassword());
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

    @GetMapping(value = IS_EXIST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> patientIsExist(@RequestParam("email") String email){

        boolean result = this.patientService.isExist(email);
        if(result){
            Long patientId = this.patientService.findPatientByEmail(email).getId();

            return new ResponseEntity<>(new HashMap<String, Object>() {{
                put("isExist", result);
                put("patientId", patientId);
            }}, HttpStatus.OK);
        }

        return new ResponseEntity<>(new HashMap<String, Object>() {{
            put("isExist", result);
        }}, HttpStatus.OK);
    }
}
