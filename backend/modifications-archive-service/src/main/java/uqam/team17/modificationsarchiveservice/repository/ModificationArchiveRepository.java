package uqam.team17.modificationsarchiveservice.repository;

import uqam.team17.modificationsarchiveservice.entity.Modification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import uqam.team17.modificationsarchiveservice.entity.ModificationType;


public interface ModificationArchiveRepository extends JpaRepository<Modification, Long> {
    Optional<Modification> findTopByHealthInsuranceNumberAndTypeAndStatusNotOrderByTimestampDesc(String healthInsuranceNumber,
                                                                                                 ModificationType type,
                                                                                                 Modification.Status status);
}