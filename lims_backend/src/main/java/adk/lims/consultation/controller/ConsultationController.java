package adk.lims.consultation.controller;

import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.service.ConsultationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static adk.lims.core.constants.URLMapping.CREATE;
import static adk.lims.core.constants.URLMapping.Consultation.CONSULTATION_BASE;

@Controller
@RequestMapping(CONSULTATION_BASE)
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(CREATE)
    public ResponseEntity<?> createConsultation(@RequestBody CreateConsultationBindingModel model){
        Consultation savedConsultation = this.consultationService.saveConsultation(model);

        return new ResponseEntity<>(savedConsultation, HttpStatus.OK);
    }
}
