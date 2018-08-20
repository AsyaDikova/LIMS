package adk.lims.user.employee.service;

import adk.lims.user.employee.model.binding.EmployeeRegistryBindingModel;
import adk.lims.user.employee.model.entity.Employee;

public interface EmployeeService {
    Employee findEmployeeByEmail(String email);

    Employee save(Employee employee);

    Employee createEmployee(EmployeeRegistryBindingModel model);

    Employee findEmployeeById(Long id);

    Employee getCurrentEmployee();
}
