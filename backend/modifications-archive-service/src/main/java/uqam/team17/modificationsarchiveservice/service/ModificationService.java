package uqam.team17.modificationsarchiveservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import uqam.team17.modificationsarchiveservice.dto.CancelModificationRequest;
import uqam.team17.modificationsarchiveservice.dto.ContactInformationRequest;
import uqam.team17.modificationsarchiveservice.dto.MedicalHistoryRequest;
import uqam.team17.modificationsarchiveservice.dto.MedicalVisitRequest;
import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.entity.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ModificationService {
    private final ModificationArchiveRepository modificationRepository;

    public ModificationService(ModificationArchiveRepository repository) {
        this.modificationRepository = repository;
    }

    public Modification saveContactInformation(ContactInformationRequest contactRequest) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(contactRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(contactRequest.getContactInformation().getType())
                .addContact(contactRequest.getContactInformation())
                .addStatus(Modification.Status.UPDATE)
                .build();

            return modificationRepository.save(modification);

    }

    public Modification saveMedicalHistory(MedicalHistoryRequest historyRequest) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(historyRequest.getMedicalHistory().getType())
                .addHistory(historyRequest.getMedicalHistory())
                .addStatus(Modification.Status.UPDATE)
                .build();

        return modificationRepository.save(modification);

    }


    public Modification saveMedicalVisit(MedicalVisitRequest visitRequest) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(visitRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(visitRequest.getMedicalVisit().getType())
                .addVisit(visitRequest.getMedicalVisit())
                .addStatus(Modification.Status.UPDATE)
                .build();

        return modificationRepository.save(modification);
    }

    public Optional<Modification> cancelLastModification(CancelModificationRequest cancelRequest) {

        String healthNumber = cancelRequest.getHealthInsuranceNumber();
        ModificationType modType = cancelRequest.getType();

        Optional<Modification> optionalModif = modificationRepository.
                findTopByHealthInsuranceNumberAndTypeAndStatusNotOrderByTimestampDesc(healthNumber, modType,
                        Modification.Status.CANCEL);

        if (optionalModif.isPresent()) {
            Modification modification = optionalModif.get();

            modification.setStatus(Modification.Status.CANCEL);
            modificationRepository.save(modification);
            return Optional.of(modification);
        }

        return Optional.empty();
    }
}