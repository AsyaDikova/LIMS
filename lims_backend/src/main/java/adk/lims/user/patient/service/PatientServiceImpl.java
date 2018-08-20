package adk.lims.user.patient.service;

import adk.lims.user.patient.model.entity.Patient;
import adk.lims.user.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;


@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findPatientByEmail(String email) {
        Patient patient = this.patientRepository.findByEmail(email);
        return patient;
    }
}
