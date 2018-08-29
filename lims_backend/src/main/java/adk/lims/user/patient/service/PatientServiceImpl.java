package adk.lims.user.patient.service;

import adk.lims.core.role.Role;
import adk.lims.core.role.RoleRepository;
import adk.lims.core.role.RoleType;
import adk.lims.user.abstractuser.model.User;
import adk.lims.user.abstractuser.service.UserService;
import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PatientServiceImpl implements PatientService{

    private static final Random RANDOM = new SecureRandom();
    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public PatientServiceImpl(PatientRepository patientRepository,
                              ObjectMapper objectMapper,
                              RoleRepository roleRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              UserService userService) {
        this.patientRepository = patientRepository;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public Patient findPatientByEmail(String email) {
        return this.patientRepository.findByUser_Email(email);
    }

    @Override
    public Patient registerPatient(RegisterPatientBingingModel model) {
        User currentUser = this.objectMapper.convertValue(model, User.class);
        Role patientRole = this.roleRepository.findByAuthority(RoleType.ROLE_PATIENT.name());
        currentUser.setPassword(this.bCryptPasswordEncoder.encode(model.getPassword()));
        currentUser.getRoles().add(patientRole);
        User savedUser = this.userService.save(currentUser);

        Patient currentPatient = new Patient();
        currentPatient.setUser(savedUser);

        return this.patientRepository.save(currentPatient);
    }

    @Override
    public Patient findPatientById(Long id) {
        return this.patientRepository.getPatientById(id);
    }

    @Override
    public Patient getCurrentPatient() {
        String principalEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.patientRepository.findByUser_Email(principalEmail);
    }

    @Override
    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public boolean isExist(String email) {
        return this.patientRepository.findByUser_Email(email) != null;
    }
}
