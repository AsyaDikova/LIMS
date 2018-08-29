package adk.lims.dayschedule.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.calendarschedule.service.CalendarScheduleService;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.consultation.repository.ConsultationRepository;
import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.dayschedule.model.view.ConsultationByEmployeeViewModel;
import adk.lims.dayschedule.model.view.DayScheduleByEmployeeViewModel;
import adk.lims.dayschedule.model.view.DayScheduleFreeHoursByAnalysisIdViewModel;
import adk.lims.dayschedule.model.view.FreeHoursInDayViewModel;
import adk.lims.dayschedule.repository.DayScheduleRepository;
import adk.lims.user.employee.model.entity.Employee;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DayScheduleServiceImpl implements DayScheduleService {
    private final static int COUNT_OF_DAILY_SCHEDULE = 30;

    private final DayScheduleRepository dayScheduleRepository;
    private final ConsultationRepository consultationRepository;

    public DayScheduleServiceImpl(DayScheduleRepository dayScheduleRepository,
                                  ConsultationRepository consultationRepository) {
        this.dayScheduleRepository = dayScheduleRepository;
        this.consultationRepository = consultationRepository;
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
        LocalDate currentDate = LocalDate.now();
        List<DaySchedule> daySchedules = this.dayScheduleRepository.findAllByCalendarSchedule_IdAndCurrentDateAfter(calendarScheduleId, currentDate);
        List<DayScheduleFreeHoursByAnalysisIdViewModel> result = new ArrayList<>();
        for (DaySchedule currentDaySchedule : daySchedules) {
            result.add(this.populateOneDay(currentDaySchedule));
        }

        return result;
    }

    @Override
    public List<DayScheduleByEmployeeViewModel> getDaySchedulesForEmployee(Long employeeId) {

        List<DaySchedule> allDayScheduleByEmployee = this.dayScheduleRepository.findByEmployeeId(employeeId);
        List<DaySchedule> restricted = new ArrayList<>();

        LocalDate currentDate = LocalDate.now().minusDays(1);

        for (DaySchedule currentDaySchedule : allDayScheduleByEmployee) {
            if(currentDaySchedule.getCurrentDate().isAfter(currentDate))
                restricted.add(currentDaySchedule);
        }

        return this.populateEmployeeDaySchedule(restricted);
    }

    @Override
    public DaySchedule save(DaySchedule daySchedule) {
        return this.dayScheduleRepository.save(daySchedule);
    }

    private void populateHourSchedule(DaySchedule daySchedule, int hour, Long consultationId){
        switch (hour){
            case 9 : daySchedule.setHourNine(consultationId); break;
            case 10 : daySchedule.setHourTen(consultationId); break;
            case 11 : daySchedule.setHourEleven(consultationId); break;
            case 12 : daySchedule.setHourTwelve(consultationId); break;
            case 13 : daySchedule.setHourThirteen(consultationId); break;
            case 14 : daySchedule.setHourFourteen(consultationId); break;
            case 15 : daySchedule.setHourFifteen(consultationId); break;
            case 16 : daySchedule.setHourSixteen(consultationId); break;
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

    private List<DayScheduleByEmployeeViewModel> populateEmployeeDaySchedule(List<DaySchedule> employeeSchedule){
        List<DayScheduleByEmployeeViewModel> result = new ArrayList<>();

        for (DaySchedule currentDaySchedule : employeeSchedule) {
            DayScheduleByEmployeeViewModel dayModel = new DayScheduleByEmployeeViewModel();
            dayModel.setCurrentDate(currentDaySchedule.getCurrentDate());
            dayModel.setHours(this.populationConsultations(currentDaySchedule));
            result.add(dayModel);
        }

        return result;
    }

    private List<ConsultationByEmployeeViewModel> populationConsultations(DaySchedule daySchedule){
        List<ConsultationByEmployeeViewModel> result = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ConsultationByEmployeeViewModel consultation = new ConsultationByEmployeeViewModel();
            consultation.setHour(i + 9);
            result.add(consultation);
        }

        for (ConsultationByEmployeeViewModel currentConsultation : result) {
            switch (currentConsultation.getHour()){
                case 9 : currentConsultation.setConsultationId(daySchedule.getHourNine()); break;
                case 10 : currentConsultation.setConsultationId(daySchedule.getHourTen()); break;
                case 11 : currentConsultation.setConsultationId(daySchedule.getHourEleven()); break;
                case 12 : currentConsultation.setConsultationId(daySchedule.getHourTwelve()); break;
                case 13 : currentConsultation.setConsultationId(daySchedule.getHourThirteen()); break;
                case 14 : currentConsultation.setConsultationId(daySchedule.getHourFourteen()); break;
                case 15 : currentConsultation.setConsultationId(daySchedule.getHourFifteen()); break;
                case 16 : currentConsultation.setConsultationId(daySchedule.getHourSixteen()); break;
            }


            if(currentConsultation.getConsultationId() != null){
                Consultation cons = this.consultationRepository.getOne(currentConsultation.getConsultationId());
                String patientName = cons.getPatient().getFullName();
                String analysisName = cons.getAnalysis().getName();

                currentConsultation.setAnalysisName(analysisName);
                currentConsultation.setPatientName(patientName);
                currentConsultation.setAnalysisId(cons.getAnalysis().getId());
                currentConsultation.setPatientId(cons.getPatient().getId());
            }
        }
        return result;
    }
}
