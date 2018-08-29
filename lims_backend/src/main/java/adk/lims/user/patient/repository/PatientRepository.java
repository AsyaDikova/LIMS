package adk.lims.user.patient.repository;

import adk.lims.user.patient.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUser_Email(String email);

    @Query(value = "SELECT * FROM patients AS p WHERE p.id=:id", nativeQuery = true)
    Patient getPatientById(@Param("id") Long id);
}
