package adk.lims.user.patient.service;

import adk.lims.user.patient.model.binding.RegisterPatientBingingModel;
import adk.lims.user.patient.model.entity.Patient;

public interface PatientService {
    Patient findPatientByEmail(String email);

    Patient registerPatient(RegisterPatientBingingModel model);

    Patient findPatientById(Long id);

    Patient getCurrentPatient();

    Patient save(Patient patient);

    boolean isExist(String email);
}
