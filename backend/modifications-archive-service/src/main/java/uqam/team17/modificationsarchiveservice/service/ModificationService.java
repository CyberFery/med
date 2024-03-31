package uqam.team17.modificationsarchiveservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.model.*;

@Service
@Transactional
public class ModificationService{

    private final ModificationArchiveRepository modificationRepository;

    public ModificationService(ModificationArchiveRepository repository){
        this.modificationRepository = repository;
    }

    public Modification saveModification(Modification modification){
        return modificationRepository.save(modification);
    }

    public Modification getModification(Long fromId){
        return modificationRepository.getReferenceById(fromId);
    }

    //public Modification getModificationById()


}