package adk.lims.analysis.service;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;

import java.util.List;

public interface AnalysisService {
    List<AllAnalyzesViewModel> allAnalyzes();

    Analysis getById(Long id);

    AnalysisDetailViewModel getAnalysisById(Long id);

    Analysis createAnalysis(AddAnalysisBindingModel model);
}
