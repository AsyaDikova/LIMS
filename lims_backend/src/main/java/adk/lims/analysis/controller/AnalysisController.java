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

import static adk.lims.core.constants.MessageMapping.Analysis.MODEL_FIELD;
import static adk.lims.core.constants.MessageMapping.Analysis.PROBLEM_WITH_SAVING_ANALYSIS;
import static adk.lims.core.constants.MessageMapping.Analysis.SUCCESS_SAVING_ANALYSIS;
import static adk.lims.core.constants.URLMapping.Analysis.*;

@Controller
public class AnalysisController {
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping(value = ANALYZES_BASE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllAnalyzes(){
        List<AllAnalyzesViewModel> allAnalyzesViewModelList = this.analysisService.allAnalyzes();
        if(allAnalyzesViewModelList == null)
            return new ResponseEntity<>(new HashMap<String, Object>(){{put("analyzes", new ArrayList<>());}} , HttpStatus.OK);

        return new ResponseEntity<>(new HashMap<String, Object>(){{put("analyzes", allAnalyzesViewModelList);}} , HttpStatus.OK);
    }

    @GetMapping(value = ANALYZES_ONE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOneAnalysisById(@PathVariable Long id){
        AnalysisDetailViewModel analysis = this.analysisService.getAnalysisById(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }

    @PostMapping(value = ANALYSIS_ADD, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addAnalysis(@RequestBody AddAnalysisBindingModel model){

        if(model.getDescription().isEmpty() ||
                model.getName().isEmpty() ||
                model.getPeriodOfProduct()<1 ||
                model.getPrice()<1.00 ||
                model.getType().isEmpty()){
            return new ResponseEntity<>(new HashMap<String, Object>(){{
                put("success", false);
                put("message", MODEL_FIELD);
            }}, HttpStatus.BAD_REQUEST);
        }

        Analysis savedAnalysis = this.analysisService.createAnalysis(model);

        if(savedAnalysis == null)
            return new ResponseEntity<>(new HashMap<String, Object>(){{
                put("success", false);
                put("message", PROBLEM_WITH_SAVING_ANALYSIS);
            }}, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new HashMap<String, Object>(){{
            put("success", true);
            put("message", SUCCESS_SAVING_ANALYSIS);
        }}, HttpStatus.OK);
    }

    @GetMapping(value = ANALYSIS_NAMES_LIST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAnalyzesList(){
        List<AnalyzesNameListViewModel> analyzesNamesList = this.analysisService.getAnalyzesNamesList();

        return new ResponseEntity<>(analyzesNamesList,  HttpStatus.OK);
    }
}
