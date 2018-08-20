package adk.lims.analysis.controller;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;
import adk.lims.analysis.service.AnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static adk.lims.core.constants.MessageMapping.Analysis.PROBLEM_WITH_SAVING_ANALYSIS;

@Controller
public class AnalysisController {
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/analyzes/all")
    public ResponseEntity<?> getAllAnalyzes(){
        List<AllAnalyzesViewModel> allAnalyzesViewModelList = this.analysisService.allAnalyzes();
        return new ResponseEntity<>(allAnalyzesViewModelList, HttpStatus.OK);
    }

    @GetMapping("/analyzes/{id}")
    public ResponseEntity<?> getOneAnalysisById(@PathVariable Long id){
        AnalysisDetailViewModel analysis = this.analysisService.getAnalysisById(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }

    @PostMapping("/analysis/add")
    public ResponseEntity<?> addAnalysis(@RequestBody AddAnalysisBindingModel model){
        Analysis savedAnalysis = this.analysisService.createAnalysis(model);
        if(savedAnalysis == null)
            return new ResponseEntity<>(PROBLEM_WITH_SAVING_ANALYSIS, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(savedAnalysis, HttpStatus.OK);
    }
}
