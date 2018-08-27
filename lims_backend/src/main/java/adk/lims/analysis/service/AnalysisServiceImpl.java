package adk.lims.analysis.service;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;
import adk.lims.analysis.model.view.AnalyzesNameListViewModel;
import adk.lims.analysis.repository.AnalysisRepository;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public AnalysisServiceImpl(AnalysisRepository analysisRepository,
                               EmployeeService employeeService,
                               ModelMapper modelMapper) {
        this.analysisRepository = analysisRepository;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AllAnalyzesViewModel> allAnalyzes() {
        List<Analysis> allAnalyses = this.analysisRepository.findAll();

        List<AllAnalyzesViewModel> result = new ArrayList<>();
        for (Analysis currentAnalysis : allAnalyses) {
            result.add(new AllAnalyzesViewModel(currentAnalysis.getId(), currentAnalysis.getName(), currentAnalysis.getType(), currentAnalysis.getPrice(), currentAnalysis.getPeriodOfProduct()));
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
//        AnalysisDetailViewModel viewModel = new AnalysisDetailViewModel();
//        viewModel.setDescription(customAnalysis.getDescription());
//        viewModel.setId(customAnalysis.getId());
//        viewModel.setName(customAnalysis.getName());
//        viewModel.setPeriodOfProduct(customAnalysis.getPeriodOfProduct());
//        viewModel.setPrice(customAnalysis.getPrice());
//        viewModel.setType(customAnalysis.getType());

        return this.modelMapper.map(customAnalysis, AnalysisDetailViewModel.class);
    }

    @Override
    public Analysis createAnalysis(AddAnalysisBindingModel model) {

        Employee employee = this.employeeService.getCurrentEmployee();

        Analysis analysis = this.modelMapper.map(model, Analysis.class);
        analysis.setEmployee(employee);
//
//        Analysis analysis = new Analysis();
//        analysis.setEmployee(employee);
//        analysis.setDescription(model.getDescription());
//        analysis.setName(model.getName());
//        analysis.setPeriodOfProduct(model.getPeriodOfProduct());
//        analysis.setPrice(model.getPrice());
//        analysis.setType(model.getType());

        return this.analysisRepository.save(analysis);
    }

    @Override
    public List<AnalyzesNameListViewModel> getAnalyzesNamesList() {
        List<Analysis> allAnalyzes = this.analysisRepository.findAll();

        return modelMapper.map(allAnalyzes, new TypeToken<List<AnalyzesNameListViewModel>>(){}.getType());
    }
}
