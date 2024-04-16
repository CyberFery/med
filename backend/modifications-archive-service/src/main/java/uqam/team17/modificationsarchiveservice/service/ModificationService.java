package uqam.team17.modificationsarchiveservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.entity.*;

import java.time.LocalDateTime;

@Service
@Transactional
public class ModificationService {
    private final ModificationArchiveRepository modificationRepository;

    public ModificationService(ModificationArchiveRepository repository) {
        this.modificationRepository = repository;
    }

    public Modification saveContactInformation(String healthInsuranceNumber, ContactInformation contact) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.CONTACT_INFORMATION)
                .addContact(contact)
                .addStatus(Modification.Status.UPDATE)
                .build();

            return modificationRepository.save(modification);

    }

    public Modification saveMedicalHistory(String healthInsuranceNumber, MedicalHistory history) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.MEDICAL_HISTORY)
                .addHistory(history)
                .addStatus(Modification.Status.UPDATE)
                .build();

        return modificationRepository.save(modification);

    }


    public Modification saveMedicalVisit(String healthInsuranceNumber, MedicalVisit visit) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.MEDICAL_VISIT)
                .addVisit(visit)
                .addStatus(Modification.Status.UPDATE)
                .build();

        return modificationRepository.save(modification);
    }

    public Modification cancelVisit(String healthInsuranceNumber, MedicalVisit visit) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.MEDICAL_VISIT)
                .addVisit(visit)
                .addStatus(Modification.Status.CANCEL)
                .build();

        return modificationRepository.save(modification);
    }

    public Modification cancelHistory(String healthInsuranceNumber, MedicalHistory history) {

        Modification modification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.MEDICAL_HISTORY)
                .addHistory(history)
                .addStatus(Modification.Status.CANCEL)
                .build();

        return modificationRepository.save(modification);
    }



}