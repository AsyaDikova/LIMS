package adk.lims.dayschedule.model.view;

import java.time.LocalDate;
import java.util.List;

public class DayScheduleFreeHoursByAnalysisIdViewModel {
    private LocalDate currentDate;
    private List<FreeHoursInDayViewModel> freeHours;

    public DayScheduleFreeHoursByAnalysisIdViewModel() {
    }

    public DayScheduleFreeHoursByAnalysisIdViewModel(LocalDate currentDate, List<FreeHoursInDayViewModel> freeHours) {
        this.currentDate = currentDate;
        this.freeHours = freeHours;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public List<FreeHoursInDayViewModel> getFreeHours() {
        return freeHours;
    }

    public void setFreeHours(List<FreeHoursInDayViewModel> freeHours) {
        this.freeHours = freeHours;
    }
}
