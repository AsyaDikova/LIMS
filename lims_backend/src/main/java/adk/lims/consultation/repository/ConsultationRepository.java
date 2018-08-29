package adk.lims.consultation.repository;

import adk.lims.consultation.model.entity.Consultation;
import adk.lims.user.patient.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>{
    List<Consultation> findAllByPatient(Patient patient);
}
