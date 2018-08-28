package adk.lims.analysis.service;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;
import adk.lims.analysis.repository.AnalysisRepository;
import adk.lims.user.abstractuser.model.User;
import adk.lims.user.abstractuser.service.UserService;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;
    private final EmployeeService employeeService;

    public AnalysisServiceImpl(AnalysisRepository analysisRepository,
                               EmployeeService employeeService) {
        this.analysisRepository = analysisRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<AllAnalyzesViewModel> allAnalyzes() {
        List<Analysis> allAnalyses = this.analysisRepository.findAll();

        List<AllAnalyzesViewModel> result = new ArrayList<>();
        for (Analysis currentAnalysis : allAnalyses) {
            result.add(new AllAnalyzesViewModel(currentAnalysis.getId(), currentAnalysis.getName(), currentAnalysis.getType(), currentAnalysis.getPrice()));
        }

        return result;
    }

    @Override
    public Analysis getById(Long id) {
        return this.analysisRepository.getOne(id);
    }

    @Override
    public AnalysisDetailViewModel getAnalysisById(Long id) {
        Analysis customAnalysis = this.analysisRepository.getOne(id);
        AnalysisDetailViewModel viewModel = new AnalysisDetailViewModel();
        viewModel.setDescription(customAnalysis.getDescription());
        viewModel.setId(customAnalysis.getId());
        viewModel.setName(customAnalysis.getName());
        viewModel.setPeriodOfProduct(customAnalysis.getPeriodOfProduct());
        viewModel.setPrice(customAnalysis.getPrice());
        viewModel.setType(customAnalysis.getType());
        return viewModel;
    }

    @Override
    public Analysis createAnalysis(AddAnalysisBindingModel model) {

        Employee employee = this.employeeService.getCurrentEmployee();
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
