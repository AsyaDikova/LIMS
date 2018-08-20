package adk.lims.user.employee.model.entity;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.user.abstractuser.model.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @OneToMany(mappedBy = "employee")
    private List<Analysis> analyzes;

    @OneToOne(mappedBy = "employee")
    private CalendarSchedule calendarSchedule;

    public Employee() {
        super();
        this.analyzes = new ArrayList<>();
    }

    public List<Analysis> getAnalyzes() {
        return analyzes;
    }

    public void setAnalyzes(List<Analysis> analyzes) {
        this.analyzes = analyzes;
    }

    public CalendarSchedule getCalendarSchedule() {
        return calendarSchedule;
    }

    public void setCalendarSchedule(CalendarSchedule calendarSchedule) {
        this.calendarSchedule = calendarSchedule;
    }
}
