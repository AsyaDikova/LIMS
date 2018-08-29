package adk.lims.consultation.service;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.service.AnalysisService;
import adk.lims.consultation.model.binding.CreateConsultationBindingModel;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.repository.ConsultationRepository;
import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.dayschedule.service.DayScheduleService;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final AnalysisService analysisService;
    private final PatientService patientService;
    private final DayScheduleService dayScheduleService;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
                                   AnalysisService analysisService,
                                   PatientService patientService,
                                   DayScheduleService dayScheduleService) {
        this.consultationRepository = consultationRepository;
        this.analysisService = analysisService;
        this.patientService = patientService;
        this.dayScheduleService = dayScheduleService;
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

        Consultation saveConsultation = this.consultationRepository.save(consultation);

        this.dayScheduleService.editByEmployeeDateAndHour(analysis.getEmployee(), model.getDateOfConsultation(), model.getHourOfConsultation(), saveConsultation.getId());

        return saveConsultation;
    }

    @Override
    public Consultation findConsultationById(Long consultationId) {
        return this.consultationRepository.getOne(consultationId);
    }
}
