package adk.lims.consultation.service;

import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.user.patient.model.entity.Patient;

import java.util.List;

public interface ConsultationService {
    Consultation saveConsultation(CreateConsultationBindingModel model);

    Consultation findConsultationById(Long consultationId);
}
