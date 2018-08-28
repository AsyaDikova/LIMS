package adk.lims.dayschedule.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.dayschedule.model.view.DayScheduleFreeHoursByAnalysisIdViewModel;
import adk.lims.dayschedule.model.view.FreeHoursInDayViewModel;
import adk.lims.dayschedule.repository.DayScheduleRepository;
import adk.lims.user.employee.model.entity.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DayScheduleServiceImpl implements DayScheduleService {
    private final static int COUNT_OF_DAILY_SCHEDULE = 30;

    private final DayScheduleRepository dayScheduleRepository;

    public DayScheduleServiceImpl(DayScheduleRepository dayScheduleRepository) {
        this.dayScheduleRepository = dayScheduleRepository;
    }

    @Override
    public List<DaySchedule> OneMonthDailySchedule(CalendarSchedule calendarSchedule) {
        List<DaySchedule> daySchedules = new ArrayList<>();
        LocalDate currentTime = LocalDate.now();
        for (int d = 0; d < COUNT_OF_DAILY_SCHEDULE; d++) {
             DaySchedule currentDaySchedule = new DaySchedule();
             currentDaySchedule.setCurrentDate(currentTime);
             currentDaySchedule.setCalendarSchedule(calendarSchedule);
             DaySchedule saved = this.dayScheduleRepository.save(currentDaySchedule);
             daySchedules.add(saved);
             currentTime = currentTime.plusDays(1L);
        }

        return daySchedules;
    }

    @Override
    public DaySchedule getOneByEmployeeAndDate(Employee employee, LocalDate localDate) {
        return this.dayScheduleRepository.getOneByCalendarSchedule_EmployeeAndCurrentDate(employee, localDate);
    }

    @Override
    public DaySchedule edit(DaySchedule daySchedule) {
        return this.dayScheduleRepository.save(daySchedule);
    }

    @Override
    public DaySchedule editByEmployeeDateAndHour(Employee employee, LocalDate currentDate, int hour, Long consultationId) {
        DaySchedule daySchedule = this.getOneByEmployeeAndDate(employee, currentDate);
        this.populateHourSchedule(daySchedule, hour, consultationId);
        return this.edit(daySchedule);
    }

    @Override
    public List<DayScheduleFreeHoursByAnalysisIdViewModel> getFreeHourByAnalysisId(Long analysisId) {
        Long calendarScheduleId = this.dayScheduleRepository.getCalendarIdByAnalysisId(analysisId);
//        List<DaySchedule> daySchedules = this.dayScheduleRepository.findAllByCalendarSchedule_Id(calendarScheduleId);
        LocalDate currentDate = LocalDate.now();
        List<DaySchedule> daySchedules = this.dayScheduleRepository.findAllByCalendarSchedule_IdAndCurrentDateAfter(calendarScheduleId, currentDate);
        List<DayScheduleFreeHoursByAnalysisIdViewModel> result = new ArrayList<>();
        for (DaySchedule currentDaySchedule : daySchedules) {
            result.add(this.populateOneDay(currentDaySchedule));
        }

        return result;
    }

    private void populateHourSchedule(DaySchedule daySchedule, int hour, Long consultationId){
        switch (hour){
            case 9 : daySchedule.setHourNine(consultationId); return;
            case 10 : daySchedule.setHourTen(consultationId); return;
            case 11 : daySchedule.setHourEleven(consultationId); return;
            case 12 : daySchedule.setHourTwelve(consultationId); return;
            case 13 : daySchedule.setHourThirteen(consultationId); return;
            case 14 : daySchedule.setHourFourteen(consultationId); return;
            case 15 : daySchedule.setHourFifteen(consultationId); return;
            case 16 : daySchedule.setHourSixteen(consultationId); return;
        }
    }

    private DayScheduleFreeHoursByAnalysisIdViewModel populateOneDay(DaySchedule currentDaySchedule){
        DayScheduleFreeHoursByAnalysisIdViewModel result = new DayScheduleFreeHoursByAnalysisIdViewModel();
        result.setCurrentDate(currentDaySchedule.getCurrentDate());

        List<FreeHoursInDayViewModel> freeHours = new ArrayList<>();
        if(currentDaySchedule.getHourNine() == null)
            freeHours.add(new FreeHoursInDayViewModel(9));
        if(currentDaySchedule.getHourTen() == null)
            freeHours.add(new FreeHoursInDayViewModel(10));
        if(currentDaySchedule.getHourEleven() == null)
            freeHours.add(new FreeHoursInDayViewModel(11));
        if(currentDaySchedule.getHourTwelve() == null)
            freeHours.add(new FreeHoursInDayViewModel(12));
        if(currentDaySchedule.getHourThirteen() == null)
            freeHours.add(new FreeHoursInDayViewModel(13));
        if(currentDaySchedule.getHourFourteen() == null)
            freeHours.add(new FreeHoursInDayViewModel(14));
        if(currentDaySchedule.getHourFifteen() == null)
            freeHours.add(new FreeHoursInDayViewModel(15));
        if(currentDaySchedule.getHourSixteen() == null)
            freeHours.add(new FreeHoursInDayViewModel(16));

        result.setFreeHours(freeHours);

        return result;
    }
}
