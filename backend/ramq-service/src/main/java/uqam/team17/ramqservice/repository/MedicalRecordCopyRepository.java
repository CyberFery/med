package uqam.team17.ramqservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqam.team17.ramqservice.entity.MedicalRecordCopy;

@Repository
public interface MedicalRecordCopyRepository extends JpaRepository <MedicalRecordCopy, Long> {
    // You can define custom query methods here if needed
}
