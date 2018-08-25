package adk.lims.user.employee.model.entity;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.user.abstractuser.model.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "employee")
    private List<Analysis> analyzes;

    @OneToOne(mappedBy = "employee")
    private CalendarSchedule calendarSchedule;

    @OneToOne
    private User user;


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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
