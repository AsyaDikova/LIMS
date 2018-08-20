package adk.lims.user.employee.service;

import adk.lims.core.role.Role;
import adk.lims.core.role.RoleRepository;
import adk.lims.core.role.RoleType;
import adk.lims.user.employee.model.binding.EmployeeRegistryBindingModel;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ObjectMapper objectMapper,
                               RoleRepository roleRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return this.employeeRepository.findByEmail(email);
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee createEmployee(EmployeeRegistryBindingModel model) {
        Employee customEmployeeModel = this.objectMapper.convertValue(model, Employee.class);
        Role employeeRole = this.roleRepository.findByAuthority(RoleType.ROLE_EMPLOYEE.name());
        customEmployeeModel.setPassword(this.bCryptPasswordEncoder.encode(model.getPassword()));
        customEmployeeModel.getRoles().add(employeeRole);
        return this.save(customEmployeeModel);
    }
}
