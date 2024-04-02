package uqam.team17.modificationsarchiveservice.repository;
import uqam.team17.modificationsarchiveservice.model.Modification;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ModificationArchiveRepository extends JpaRepository<Modification, Long>{

    //@Query("SELECT m FROM Modification m WHERE m.creator = :creator")
   // List<Modification> getAllModificationByCreator(@Param("creator") Doctor creator);
}