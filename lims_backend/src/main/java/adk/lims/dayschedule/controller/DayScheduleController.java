package adk.lims.dayschedule.controller;

import adk.lims.dayschedule.model.view.DayScheduleByEmployeeViewModel;
import adk.lims.dayschedule.model.view.DayScheduleFreeHoursByAnalysisIdViewModel;
import adk.lims.dayschedule.service.DayScheduleService;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
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
    private final EmployeeService employeeService;

    public DayScheduleController(DayScheduleService dayScheduleService, EmployeeService employeeService) {
        this.dayScheduleService = dayScheduleService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/freeHours", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getFreeHoursByAnalysisId(@RequestParam Long analysisId){
        List<DayScheduleFreeHoursByAnalysisIdViewModel> dayScheduleResult = this.dayScheduleService.getFreeHourByAnalysisId(analysisId);

        return new ResponseEntity<>(dayScheduleResult, HttpStatus.OK);

    }

    @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getEmployeeDaySchedule(){

        Long employeeId = this.employeeService.getCurrentEmployee().getId();

        List<DayScheduleByEmployeeViewModel> dayScheduleResult = this.dayScheduleService.getDaySchedulesForEmployee(employeeId);

        return new ResponseEntity<>(dayScheduleResult, HttpStatus.OK);
    }


}
