package adk.lims.user.employee.service;

import adk.lims.calendarschedule.model.entity.CalendarSchedule;
import adk.lims.calendarschedule.service.CalendarScheduleService;
import adk.lims.core.role.Role;
import adk.lims.core.role.RoleRepository;
import adk.lims.core.role.RoleType;
import adk.lims.dayschedule.service.DayScheduleService;
import adk.lims.user.abstractuser.model.User;
import adk.lims.user.abstractuser.service.UserService;
import adk.lims.user.employee.model.binding.EmployeeRegistryBindingModel;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final DayScheduleService dayScheduleService;
    private final CalendarScheduleService calendarScheduleService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ObjectMapper objectMapper,
                               RoleRepository roleRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder,
                               UserService userService,
                               DayScheduleService dayScheduleService,
                               CalendarScheduleService calendarScheduleService) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.dayScheduleService = dayScheduleService;
        this.calendarScheduleService = calendarScheduleService;
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee createEmployee(EmployeeRegistryBindingModel model) {

        User userModel = this.objectMapper.convertValue(model, User.class);
        Role employeeRole = this.roleRepository.findByAuthority(RoleType.ROLE_EMPLOYEE.name());
        userModel.setPassword(this.bCryptPasswordEncoder.encode(model.getPassword()));
        userModel.getRoles().add(employeeRole);
        User savedUser = this.userService.save(userModel);

        Employee currentEmployee = new Employee();

        Employee saveEmployee = this.employeeRepository.save(currentEmployee);

        CalendarSchedule calendar = new CalendarSchedule();
        CalendarSchedule savedCalendar = this.calendarScheduleService.save(calendar);
        savedCalendar.setDaySchedules(this.dayScheduleService.OneMonthDailySchedule(savedCalendar));
        savedCalendar.setEmployee(saveEmployee);
        CalendarSchedule savedCalendarWithSchedule = this.calendarScheduleService.save(savedCalendar);

        saveEmployee.setUser(savedUser);
        saveEmployee.setCalendarSchedule(savedCalendarWithSchedule);

        return this.save(saveEmployee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.getOne(id);
    }

    @Override
    public Employee getCurrentEmployee() {
        String principalEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.employeeRepository.findByUser_Email(principalEmail);
    }

    @Override
    public Employee findEmployeeByUserId(Long userId) {
        return this.employeeRepository.findByUser_Id(userId);
    }

}
