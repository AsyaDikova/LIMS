package adk.lims.analysis.controller;

import adk.lims.analysis.model.binding.AddAnalysisBindingModel;
import adk.lims.analysis.model.entity.Analysis;
import adk.lims.analysis.model.view.AllAnalyzesViewModel;
import adk.lims.analysis.model.view.AnalysisDetailViewModel;
import adk.lims.analysis.model.view.AnalyzesNameListViewModel;
import adk.lims.analysis.service.AnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static adk.lims.core.constants.MessageMapping.Analysis.PROBLEM_WITH_SAVING_ANALYSIS;

@Controller
public class AnalysisController {
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping(value = "/analyzes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllAnalyzes(){
        List<AllAnalyzesViewModel> allAnalyzesViewModelList = this.analysisService.allAnalyzes();
        if(allAnalyzesViewModelList == null)
            return new ResponseEntity<>(new HashMap<String, Object>(){{put("analyzes", new ArrayList<>());}} , HttpStatus.OK);

        return new ResponseEntity<>(new HashMap<String, Object>(){{put("analyzes", allAnalyzesViewModelList);}} , HttpStatus.OK);
    }

    @GetMapping(value = "/analyzes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOneAnalysisById(@PathVariable Long id){
        AnalysisDetailViewModel analysis = this.analysisService.getAnalysisById(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }

    @PostMapping(value = "/analysis/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addAnalysis(@RequestBody AddAnalysisBindingModel model){
        Analysis savedAnalysis = this.analysisService.createAnalysis(model);
        if(savedAnalysis == null)
            return new ResponseEntity<>(PROBLEM_WITH_SAVING_ANALYSIS, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new HashMap<String, String>(){{put("success", "ok");}}, HttpStatus.OK);
    }

    @GetMapping(value = "analysis/namesList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAnalyzesList(){
        List<AnalyzesNameListViewModel> analyzesNamesList = this.analysisService.getAnalyzesNamesList();

        return new ResponseEntity<>(analyzesNamesList,  HttpStatus.OK);
    }
}
