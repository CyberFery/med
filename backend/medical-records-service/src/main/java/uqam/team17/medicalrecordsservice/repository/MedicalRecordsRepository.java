package uqam.team17.medicalrecordsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqam.team17.medicalrecordsservice.entity.MedicalRecord;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findByPatientHealthInsuranceNumber(String healthInsuranceNumber);
}