package adk.lims.user.abstractuser.service;

import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.employee.service.EmployeeService;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.service.PatientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final EmployeeService employeeService;
    private final PatientService patientService;

    public UserServiceImpl(EmployeeService employeeService,
                           PatientService patientService) {
        this.employeeService = employeeService;
        this.patientService = patientService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //throw exception when user = (patient and employee) is not found

         Employee employee = this.employeeService.findEmployeeByEmail(email);

         if(employee == null){
             Patient patient = this.patientService.findPatientByEmail(email);
             if(patient != null)
                 return patient;
         }

         return employee;
    }
}
