package adk.lims.consultation.service;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.service.AnalysisService;
import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.repository.ConsultationRepository;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final AnalysisService analysisService;
    private final PatientService patientService;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
                                   AnalysisService analysisService,
                                   PatientService patientService) {
        this.consultationRepository = consultationRepository;
        this.analysisService = analysisService;
        this.patientService = patientService;
    }


    @Override
    public Consultation saveConsultation(CreateConsultationBindingModel model) {

        Analysis analysis = this.analysisService.getById(model.getAnalysisId());
        Patient patient = this.patientService.findPatientById(model.getPatientId());

        Consultation consultation = new Consultation();
        consultation.setAnalysis(analysis);
        consultation.setDateOfConsultation(model.getDateOfConsultation());
        consultation.setHourOfConsultation(model.getHourOfConsultation());
        consultation.setPatient(patient);

        return this.consultationRepository.save(consultation);
    }
}
