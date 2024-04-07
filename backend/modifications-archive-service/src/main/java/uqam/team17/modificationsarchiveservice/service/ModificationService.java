package uqam.team17.modificationsarchiveservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.model.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ModificationService{

    private final ModificationArchiveRepository modificationRepository;

    public ModificationService(ModificationArchiveRepository repository){
        this.modificationRepository = repository;
    }
    @Transactional
    public Modification saveContactInformation(ContactInformationRequest contactRequest){
        Modification modification = new Modification();
        modification.setHealthInsuranceNumber(contactRequest.getHealthInsuranceNumber());
        modification.setTimestamp(LocalDateTime.now());
        modification.setType(contactRequest.getContactInformation().getType());
        modification.setContactInformation(contactRequest.getContactInformation());
        modification.setStatus(Modification.Status.UPDATE);

        return modificationRepository.save(modification);
    }
    @Transactional
    public Modification saveMedicalHistory(MedicalHistoryRequest historyRequest){
        Modification modification = new Modification();
        modification.setHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber());
        modification.setTimestamp(LocalDateTime.now());
        modification.setType(historyRequest.getMedicalHistory().getType());
        modification.setMedicalHistory(historyRequest.getMedicalHistory());
        modification.setStatus(Modification.Status.UPDATE);
        //modification.setTest(test);

        return modificationRepository.save(modification);

    }


    public Modification saveMedicalVisit(MedicalVisitRequest visitRequest){
        Modification modification = new Modification();
        modification.setHealthInsuranceNumber(visitRequest.getHealthInsuranceNumber());
        modification.setTimestamp(LocalDateTime.now());
        modification.setType(visitRequest.getMedicalVisit().getType());
        modification.setMedicalVisit(visitRequest.getMedicalVisit());
        modification.setStatus(Modification.Status.UPDATE);

        return modificationRepository.save(modification);
    }

    public Optional<Modification> getModificationById(Long modificationId){

        return modificationRepository.findById(modificationId);
    }


}