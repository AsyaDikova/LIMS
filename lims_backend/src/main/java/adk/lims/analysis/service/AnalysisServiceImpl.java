package adk.lims.analysis.service;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;
import adk.lims.analysis.repository.AnalysisRepository;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;
    private final ObjectMapper objectMapper;
    private final EmployeeService employeeService;

    public AnalysisServiceImpl(AnalysisRepository analysisRepository,
                               ObjectMapper objectMapper,
                               EmployeeService employeeService) {
        this.analysisRepository = analysisRepository;
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<AllAnalyzesViewModel> allAnalyzes() {


        return null;
    }

    @Override
    public Analysis getById(Long id) {
        return this.analysisRepository.getOne(id);
    }

    @Override
    public AnalysisDetailViewModel getAnalysisById(Long id) {
        Analysis customAnalysis = this.analysisRepository.getOne(id);
        return this.objectMapper.convertValue(customAnalysis, AnalysisDetailViewModel.class);
    }

    @Override
    public Analysis createAnalysis(AddAnalysisBindingModel model) {
        Employee employee = this.employeeService.findEmployeeById(model.getEmployeeId());
        Analysis analysis = new Analysis();
        analysis.setEmployee(employee);
        analysis.setDescription(model.getDescription());
        analysis.setName(model.getName());
        analysis.setPeriodOfProduct(model.getPeriodOfProduct());
        analysis.setPrice(model.getPrice());
        analysis.setType(model.getType());

        return this.analysisRepository.save(analysis);
    }
}
