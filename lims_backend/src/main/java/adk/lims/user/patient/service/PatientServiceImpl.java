package adk.lims.user.patient.service;

import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;

    public PatientServiceImpl(PatientRepository patientRepository,
                              ObjectMapper objectMapper) {
        this.patientRepository = patientRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Patient findPatientByEmail(String email) {
        Patient patient = this.patientRepository.findByEmail(email);
        return patient;
    }

    @Override
    public Patient registerPatient(RegisterPatientBingingModel model) {
        Patient currentPatient = this.objectMapper.convertValue(model, Patient.class);
        Patient savedPatient = this.patientRepository.save(currentPatient);

        return savedPatient;
    }

    @Override
    public Patient findPatientById(Long id) {
        return this.patientRepository.getOne(id);
    }

    @Override
    public Patient getCurrentPatient() {
        String principalEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.patientRepository.findByEmail(principalEmail);
    }
}
