package adk.lims.dayschedule.model.entity;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "day_schedules")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_schedule_id")
    private CalendarSchedule calendarSchedule;

    @Column(name = "curr_date", nullable = false)
    private LocalDate currentDate;

    private Date createdOn;

    private Long hourNine;
    private Long hourTen;
    private Long hourEleven;
    private Long hourTwelve;
    private Long hourThirteen;
    private Long hourFourteen;
    private Long hourFifteen;
    private Long hourSixteen;

    public DaySchedule() {
        this.createdOn = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalendarSchedule getCalendarSchedule() {
        return calendarSchedule;
    }

    public void setCalendarSchedule(CalendarSchedule calendarSchedule) {
        this.calendarSchedule = calendarSchedule;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public Long getHourNine() {
        return hourNine;
    }

    public void setHourNine(Long hourNine) {
        this.hourNine = hourNine;
    }

    public Long getHourTen() {
        return hourTen;
    }

    public void setHourTen(Long hourTen) {
        this.hourTen = hourTen;
    }

    public Long getHourEleven() {
        return hourEleven;
    }

    public void setHourEleven(Long hourEleven) {
        this.hourEleven = hourEleven;
    }

    public Long getHourTwelve() {
        return hourTwelve;
    }

    public void setHourTwelve(Long hourTwelve) {
        this.hourTwelve = hourTwelve;
    }

    public Long getHourThirteen() {
        return hourThirteen;
    }

    public void setHourThirteen(Long hourThirteen) {
        this.hourThirteen = hourThirteen;
    }

    public Long getHourFourteen() {
        return hourFourteen;
    }

    public void setHourFourteen(Long hourFourteen) {
        this.hourFourteen = hourFourteen;
    }

    public Long getHourFifteen() {
        return hourFifteen;
    }

    public void setHourFifteen(Long hourFifteen) {
        this.hourFifteen = hourFifteen;
    }

    public Long getHourSixteen() {
        return hourSixteen;
    }

    public void setHourSixteen(Long hourSixteen) {
        this.hourSixteen = hourSixteen;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
