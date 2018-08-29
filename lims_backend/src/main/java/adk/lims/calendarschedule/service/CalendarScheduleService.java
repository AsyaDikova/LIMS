package adk.lims.calendarschedule.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;

import java.util.List;

public interface CalendarScheduleService {
    CalendarSchedule save(CalendarSchedule calendarSchedule);

    List<CalendarSchedule> findAll();
}
