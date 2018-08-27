package adk.lims.dayschedule.model.view;

public class FreeHoursInDayViewModel {
    private int hour;

    public FreeHoursInDayViewModel() {
    }

    public FreeHoursInDayViewModel(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
