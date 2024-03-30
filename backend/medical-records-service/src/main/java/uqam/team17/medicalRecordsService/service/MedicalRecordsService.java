package uqam.team17.medicalRecordsService.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.medicalRecordsService.entity.MedicalRecord;
import uqam.team17.medicalRecordsService.repository.MedicalRecordsRepository;

@Service
@Transactional
public class MedicalRecordsService {
    private final MedicalRecordsRepository medicalRecordsRepository;

    public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
    }

    public MedicalRecord getMedicalRecord(String healthInsuranceNumber) {
        return medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber);
    }
}
