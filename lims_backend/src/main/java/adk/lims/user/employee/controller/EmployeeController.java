package adk.lims.user.employee.controller;

import adk.lims.user.employee.model.binding.EmployeeRegistryBindingModel;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static adk.lims.core.constants.URLMapping.Employee.EMPLOYEE_BASE;
import static adk.lims.core.constants.URLMapping.REGISTER;

@Controller
@RequestMapping(EMPLOYEE_BASE)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(REGISTER)
    public ResponseEntity<?> registryEmployee(@RequestBody EmployeeRegistryBindingModel registerModel){
        Employee savedEmployee = this.employeeService.createEmployee(registerModel);
        return new ResponseEntity(savedEmployee, HttpStatus.OK);
    }
}
