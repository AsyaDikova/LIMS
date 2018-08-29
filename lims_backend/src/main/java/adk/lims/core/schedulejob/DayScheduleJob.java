package adk.lims.core.schedulejob;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.calendarschedule.service.CalendarScheduleService;
import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.dayschedule.service.DayScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DayScheduleJob {
    private static final int ONE_DAY = 1;

    private final DayScheduleService dayScheduleService;
    private final CalendarScheduleService calendarScheduleService;

    public DayScheduleJob(DayScheduleService dayScheduleService,
                          CalendarScheduleService calendarScheduleService) {
        this.dayScheduleService = dayScheduleService;
        this.calendarScheduleService = calendarScheduleService;
    }


    @Scheduled(cron = "${schedule.cron.add.day.in.calendar}")
    public void addOneDayToCalendar() {
        List<CalendarSchedule> allCalendars = this.calendarScheduleService.findAll();

        if (allCalendars != null) {
            for (CalendarSchedule currentCalendar : allCalendars) {
                List<DaySchedule> daySchedules = currentCalendar.getDaySchedules();
                LocalDate maxCurrentDate = daySchedules.get(0).getCurrentDate();

                for (DaySchedule currentDaySchedule : daySchedules) {
                    if (currentDaySchedule.getCurrentDate().isAfter(maxCurrentDate))
                        maxCurrentDate = currentDaySchedule.getCurrentDate();
                }

                DaySchedule newDaySchedule = new DaySchedule();
                newDaySchedule.setCurrentDate(maxCurrentDate.plusDays(ONE_DAY));
                newDaySchedule.setCalendarSchedule(currentCalendar);
                this.dayScheduleService.save(newDaySchedule);
            }
        }
    }
}
