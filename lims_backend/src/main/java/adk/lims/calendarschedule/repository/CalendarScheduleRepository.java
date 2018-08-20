package adk.lims.calendarschedule.repository;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarScheduleRepository extends JpaRepository<CalendarSchedule,Long> {
}
