package uqam.team17.modificationsarchiveservice.repository;

import uqam.team17.modificationsarchiveservice.model.Modification;
import uqam.team17.modificationsarchiveservice.model.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;




public interface ModificationArchiveRepository extends JpaRepository<Modification, Long>{

    @Query("SELECT m FROM Modification m WHERE m.creator = :creator")
    List<Modification> getAllModificationByCreator(@Param("creator") Doctor creator);
}