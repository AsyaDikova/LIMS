package adk.lims.dayschedule.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.dayschedule.model.view.DayScheduleByEmployeeViewModel;
import adk.lims.dayschedule.model.view.DayScheduleFreeHoursByAnalysisIdViewModel;
import adk.lims.user.employee.model.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface DayScheduleService {
    List<DaySchedule> OneMonthDailySchedule(CalendarSchedule calendarSchedule);

    DaySchedule getOneByEmployeeAndDate(Employee employee, LocalDate localDate);

    DaySchedule edit(DaySchedule daySchedule);

    DaySchedule editByEmployeeDateAndHour(Employee employee, LocalDate currentDate, int hour, Long consultationId);

    List<DayScheduleFreeHoursByAnalysisIdViewModel> getFreeHourByAnalysisId(Long analysisId);

    List<DayScheduleByEmployeeViewModel> getDaySchedulesForEmployee(Long employeeId);
}
