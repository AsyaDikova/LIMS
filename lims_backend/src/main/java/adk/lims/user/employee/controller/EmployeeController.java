package adk.lims.user.employee.controller;

import adk.lims.user.employee.model.binding.EmployeeRegistryBindingModel;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

import static adk.lims.core.constants.URLMapping.Employee.EMPLOYEE_BASE;
import static adk.lims.core.constants.URLMapping.REGISTER;

@Controller
@RequestMapping(EMPLOYEE_BASE)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public EmployeeController(EmployeeService employeeService,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = REGISTER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> registryEmployee(@RequestBody EmployeeRegistryBindingModel registerModel){
        Employee savedEmployee = this.employeeService.createEmployee(registerModel);

        if(savedEmployee == null){
            return new ResponseEntity<>(new HashMap<>(){{
                put("success", false);
                put("message", "You are not register employee " + registerModel.getEmail());
            }}, HttpStatus.BAD_REQUEST);
        }

        String pass = this.bCryptPasswordEncoder.encode(savedEmployee.getUser().getPassword());

        return new ResponseEntity<>(new HashMap<>(){{
            put("success", true);
            put("message", "You are correct register employee " + savedEmployee.getUser().getEmail());
            put("employeePass", pass);
        }}, HttpStatus.OK);
    }
}
