package adk.lims.user.abstractuser.service;

import adk.lims.user.abstractuser.model.User;
import adk.lims.user.abstractuser.repository.UserRepository;
import adk.lims.user.employee.model.entity.Employee;
import adk.lims.user.patient.model.entity.Patient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //throw exception when user = (patient and employee) is not found

//         Employee employee = this.employeeService.findEmployeeByEmail(email);
//
//         if(employee == null){
//             Patient patient = this.patientService.findPatientByEmail(email);
//             if(patient != null)
//                 return patient;
//         }
//
//         return employee;

        return this.userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String currentUserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userRepository.findByEmail(currentUserEmail);
    }
}
