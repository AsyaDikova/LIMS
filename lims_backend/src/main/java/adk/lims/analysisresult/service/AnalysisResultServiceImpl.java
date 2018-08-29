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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisResultServiceImpl implements AnalysisResultService{

    private static final String SUNDAY = "SUNDAY";
    private static final String SATURDAY = "SATURDAY";

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

        LocalDate dueDate = LocalDate.now();
        dueDate = dueDate.plusDays(this.calculateDueDate(dueDate, analysis.getPeriodOfProduct()));

        AnalysisResult analysisResult = new AnalysisResult();
        analysisResult.setAnalysis(analysis);
        analysisResult.setPatient(patient);
        analysisResult.setDueDate(dueDate);

        AnalysisResult saveAnalysisResult = this.analysisResultRepository.save(analysisResult);

        return saveAnalysisResult;
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
        return this.objectMapper.convertValue(analysisResult, OneMyAnalysisResultVewModel.class);
    }

    private int calculateDueDate(LocalDate currentDate, int analysisPeriod){
        int dayOfWeek = currentDate.getDayOfWeek().getValue();
        int calculatePeriod = analysisPeriod + (dayOfWeek + analysisPeriod) * 2 / 7 - 1;

        LocalDate calcDate = currentDate.plusDays(calculatePeriod);
        if(calcDate.getDayOfWeek().name().equals(SUNDAY)) {
            calculatePeriod += 1;
        } else if(calcDate.getDayOfWeek().name().equals(SATURDAY)){
            calculatePeriod += 2;
        }

        return calculatePeriod;
    }
}
