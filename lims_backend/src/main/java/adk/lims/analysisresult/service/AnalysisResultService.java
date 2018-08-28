package adk.lims.analysisresult.service;

import adk.lims.analysisresult.model.binding.CreateAnalysisResult;
import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.analysisresult.model.view.AllMyAnalysisResult;
import adk.lims.analysisresult.model.view.OneMyAnalysisResultVewModel;

import java.util.List;

public interface AnalysisResultService {
    AnalysisResult createAnalysisResult(CreateAnalysisResult createAnalysisResult);

    List<AllMyAnalysisResult> allMyAnalysisResult();

    OneMyAnalysisResultVewModel getOneMyAnalysisResult(Long id);
}
