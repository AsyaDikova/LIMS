package adk.lims.user.patient.service;

import adk.lims.user.patient.model.entity.Patient;

public interface PatientService {
    Patient findPatientByEmail(String email);
}
