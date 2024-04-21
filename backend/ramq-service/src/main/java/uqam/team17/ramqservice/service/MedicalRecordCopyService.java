package uqam.team17.ramqservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.ramqservice.entity.MedicalRecordCopy;
import uqam.team17.ramqservice.repository.MedicalRecordCopyRepository;

import java.util.List;

@Service
@Transactional
public class MedicalRecordCopyService {
    private final MedicalRecordCopyRepository medicalRecordCopyRepository;

    public MedicalRecordCopyService(MedicalRecordCopyRepository medicalRecordCopyRepository
    ) {
        this.medicalRecordCopyRepository = medicalRecordCopyRepository;
    }

    public MedicalRecordCopy saveMedicalRecordCopy(MedicalRecordCopy medicalRecordCopy) {
        return medicalRecordCopyRepository.save(medicalRecordCopy);
    }

    public List<MedicalRecordCopy> getAllMedicalRecordCopies() {
        return medicalRecordCopyRepository.findAll();
    }
}
