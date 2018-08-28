package adk.lims.analysisresult.service;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.service.AnalysisService;
import adk.lims.analysisresult.model.binding.CreateAnalysisResult;
import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.analysisresult.model.view.AllMyAnalysisResult;
import adk.lims.analysisresult.model.view.OneMyAnalysisResultVewModel;
import adk.lims.analysisresult.repository.AnalysisResultRepository;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisResultServiceImpl implements AnalysisResultService{

    private final AnalysisResultRepository analysisResultRepository;
    private final PatientService patientService;
    private final AnalysisService analysisService;
    private final ObjectMapper objectMapper;

    public AnalysisResultServiceImpl(AnalysisResultRepository analysisResultRepository,
                                     PatientService patientService,
                                     AnalysisService analysisService,
                                     ObjectMapper objectMapper) {
        this.analysisResultRepository = analysisResultRepository;
        this.patientService = patientService;
        this.analysisService = analysisService;
        this.objectMapper = objectMapper;
    }

    @Override
    public AnalysisResult createAnalysisResult(CreateAnalysisResult createAnalysisResult) {
        Analysis analysis = this.analysisService.getById(createAnalysisResult.getAnalysisId());
        Patient patient = this.patientService.findPatientById(createAnalysisResult.getPatientId());

        Date today = new Date();
        LocalDateTime ldt = LocalDateTime.from(today.toInstant()).plusDays(analysis.getPeriodOfProduct());
        Date dueDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        AnalysisResult analysisResult = new AnalysisResult();
        analysisResult.setAnalysis(analysis);
        analysisResult.setPatient(patient);
        analysisResult.setDueDate(dueDate);

        return this.analysisResultRepository.save(analysisResult);
    }

    @Override
    public List<AllMyAnalysisResult> allMyAnalysisResult() {
        Patient currentPatient = this.patientService.getCurrentPatient();

        List<AnalysisResult> allMyAnalysisResult = this.analysisResultRepository.findAllByPatient_Id(currentPatient.getId());

        List<AllMyAnalysisResult> results = new ArrayList<>();

        for (AnalysisResult currentAnalysisResult : allMyAnalysisResult) {
            AllMyAnalysisResult result = new AllMyAnalysisResult();
            result.setId(currentAnalysisResult.getId());
            result.setDueDate(currentAnalysisResult.getDueDate());
            result.setAnalysisName(currentAnalysisResult.getAnalysis().getName());
            results.add(result);
        }

        return results;
    }

    @Override
    public OneMyAnalysisResultVewModel getOneMyAnalysisResult(Long id) {
        AnalysisResult analysisResult = this.analysisResultRepository.getOne(id);
        OneMyAnalysisResultVewModel result = this.objectMapper.convertValue(analysisResult, OneMyAnalysisResultVewModel.class);
        return result;
    }
}
