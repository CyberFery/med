package uqam.team17.modificationsarchiveservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.model.*;

import java.util.Optional;

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

    public Optional<Modification> getModificationById(Long modificationId){

        return modificationRepository.findById(modificationId);
    }

    //public Modification getModificationById()


}