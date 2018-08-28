package adk.lims.dayschedule.model.view;

import java.time.LocalDate;
import java.util.List;

public class DayScheduleByEmployeeViewModel {
    private LocalDate currentDate;
    private List<ConsultationByEmployeeViewModel> hours;

    public DayScheduleByEmployeeViewModel() {
    }

    public DayScheduleByEmployeeViewModel(LocalDate currentDate, List<ConsultationByEmployeeViewModel> hours) {
        this.currentDate = currentDate;
        this.hours = hours;
    }

    public LocalDate getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public List<ConsultationByEmployeeViewModel> getHours() {
        return this.hours;
    }

    public void setHours(List<ConsultationByEmployeeViewModel> hours) {
        this.hours = hours;
    }
}
