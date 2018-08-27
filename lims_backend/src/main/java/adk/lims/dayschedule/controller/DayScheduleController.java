package adk.lims.dayschedule.controller;

import adk.lims.dayschedule.model.binding.DaySchedulesFreeHoursBindingModel;
import adk.lims.dayschedule.model.view.DayScheduleFreeHoursByAnalysisIdViewModel;
import adk.lims.dayschedule.service.DayScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/daySchedule")
public class DayScheduleController {
    private final DayScheduleService dayScheduleService;

    public DayScheduleController(DayScheduleService dayScheduleService) {
        this.dayScheduleService = dayScheduleService;
    }

    @GetMapping(value = "/freeHours", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getFreeHoursByAnalysisId(@RequestParam Long analysisId){
        List<DayScheduleFreeHoursByAnalysisIdViewModel> dayScheduleResult = this.dayScheduleService.getFreeHourByAnalysisId(analysisId);

        return new ResponseEntity<>(dayScheduleResult, HttpStatus.OK);

    }
}
