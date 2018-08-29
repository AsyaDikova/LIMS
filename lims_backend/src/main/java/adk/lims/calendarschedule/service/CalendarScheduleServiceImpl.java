package adk.lims.calendarschedule.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.calendarschedule.repository.CalendarScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarScheduleServiceImpl implements CalendarScheduleService {
    private final CalendarScheduleRepository calendarScheduleRepository;

    public CalendarScheduleServiceImpl(CalendarScheduleRepository calendarScheduleRepository) {
        this.calendarScheduleRepository = calendarScheduleRepository;
    }

    @Override
    public CalendarSchedule save(CalendarSchedule calendarSchedule) {
        return this.calendarScheduleRepository.save(calendarSchedule);
    }

    @Override
    public List<CalendarSchedule> findAll() {
        return this.calendarScheduleRepository.findAll();
    }
}
