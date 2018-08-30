package adk.lims.consultation.controller;

import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.service.ConsultationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.HashMap;

import static adk.lims.core.constants.MessageMapping.Consultation.ERROR_MESSAGE;
import static adk.lims.core.constants.MessageMapping.Consultation.PROBLEM_WITH_CREATE_CONSULTATION;
import static adk.lims.core.constants.MessageMapping.Consultation.SUCCESSFUL_CREATE_CONSULTATION;
import static adk.lims.core.constants.URLMapping.CREATE;
import static adk.lims.core.constants.URLMapping.Consultation.CONSULTATION_BASE;

@Controller
@RequestMapping(CONSULTATION_BASE)
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(value = CREATE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createConsultation(@RequestBody CreateConsultationBindingModel model){
        if(model.getAnalysisId()<1 || model.getPatientId() <1 || model.getDateOfConsultation().isBefore(LocalDate.now()) ||
                model.getHourOfConsultation()<=8 || model.getHourOfConsultation() >=17 ){
            return new ResponseEntity<>(new HashMap<String, Object>(){{
                put("success", false);
                put("message", ERROR_MESSAGE);
            }}, HttpStatus.BAD_REQUEST);
        }

        Consultation savedConsultation = this.consultationService.saveConsultation(model);

        if(savedConsultation == null){
            return new ResponseEntity<>(new HashMap<String, Object>(){{
                put("success", false);
                put("message", PROBLEM_WITH_CREATE_CONSULTATION);
            }}, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new HashMap<String, Object>(){{
            put("success", true);
            put("consultation", savedConsultation.getId());
            put("message", SUCCESSFUL_CREATE_CONSULTATION);
        }}, HttpStatus.OK);
    }
}
