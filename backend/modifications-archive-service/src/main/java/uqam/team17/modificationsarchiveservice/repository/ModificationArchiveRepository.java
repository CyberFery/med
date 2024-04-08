package uqam.team17.modificationsarchiveservice.repository;
import uqam.team17.modificationsarchiveservice.model.Modification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import uqam.team17.modificationsarchiveservice.model.ModificationType;


public interface ModificationArchiveRepository extends JpaRepository<Modification, Long>{

    //@Query("SELECT m FROM Modification m WHERE m.creator = :creator")
   // List<Modification> getAllModificationByCreator(@Param("creator") Doctor creator);
    Optional<Modification> findTopByHealthInsuranceNumberAndTypeAndStatusNotOrderByTimestampDesc(String healthInsuranceNumber,
                                                                                                 ModificationType type,
                                                                                                 Modification.Status status);
}