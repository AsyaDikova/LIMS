package adk.lims.dayschedule.repository;

import adk.lims.dayschedule.model.entity.DaySchedule;
import adk.lims.user.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long>{
    DaySchedule getOneByCalendarSchedule_EmployeeAndCurrentDate(Employee employee, LocalDate currentDate);

    @Query(value = "SELECT cs.id FROM calendar_schedules AS cs\n" +
            "INNER JOIN employees AS e\n" +
            "ON cs.employee_id = e.id\n" +
            "INNER JOIN analyzes AS a\n" +
            "ON e.id = a.employee_id\n" +
            "WHERE a.id = :analysisId", nativeQuery = true)
    Long getCalendarIdByAnalysisId(@Param("analysisId") Long analysisId);

    List<DaySchedule> findAllByCalendarSchedule_Id(Long calendarScheduleId);

    List<DaySchedule> findAllByCalendarSchedule_IdAndCurrentDateAfter(Long calendarScheduleId, LocalDate currentDate);
}
