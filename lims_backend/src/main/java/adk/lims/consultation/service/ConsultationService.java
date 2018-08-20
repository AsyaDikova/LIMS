package adk.lims.consultation.service;


import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;

public interface ConsultationService {
    Consultation saveConsultation(CreateConsultationBindingModel model);
}
