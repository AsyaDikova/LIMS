package adk.lims.analysisresult.controller;

import adk.lims.analysisresult.model.binding.CreateAnalysisResult;
import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.analysisresult.model.view.AllMyAnalysisResult;
import adk.lims.analysisresult.model.view.OneMyAnalysisResultVewModel;
import adk.lims.analysisresult.service.AnalysisResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static adk.lims.core.constants.MessageMapping.AnalysisResult.PROBLEM_WITH_SAVING_ANALYSIS_RESULT;
import static adk.lims.core.constants.URLMapping.AnalysisResult.ALL_MY_RESULT;
import static adk.lims.core.constants.URLMapping.AnalysisResult.ANALYSIS_RESULT_BASE;
import static adk.lims.core.constants.URLMapping.AnalysisResult.ONE_MY_RESULT;
import static adk.lims.core.constants.URLMapping.CREATE;

@Controller
@RequestMapping(ANALYSIS_RESULT_BASE)
public class AnalysisResultController {
    private final AnalysisResultService analysisResultService;

    public AnalysisResultController(AnalysisResultService analysisResultService) {
        this.analysisResultService = analysisResultService;
    }

    @PostMapping(CREATE)
    public ResponseEntity<?> createAnalysisResult(@RequestBody CreateAnalysisResult model){
        AnalysisResult savedAnalysisResult = this.analysisResultService.createAnalysisResult(model);
        if(savedAnalysisResult == null){
            new ResponseEntity<>(PROBLEM_WITH_SAVING_ANALYSIS_RESULT, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedAnalysisResult, HttpStatus.OK);
    }

    @GetMapping(ALL_MY_RESULT)
    public ResponseEntity<?> allMyResults(){
        List<AllMyAnalysisResult> results = this.analysisResultService.allMyAnalysisResult();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(ONE_MY_RESULT)
    public ResponseEntity<?> getOneMyResult(@PathVariable Long id){
        OneMyAnalysisResultVewModel result = this.analysisResultService.getOneMyAnalysisResult(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
