package uqam.team17.medicalRecordsService.service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.medicalRecordsService.ExceptionHandler.MedicalRecordsException;
import uqam.team17.medicalRecordsService.entity.MedicalRecord;
import uqam.team17.medicalRecordsService.entity.Patient;
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
    public void updateContactInformation(String healthInsuranceNumber, Patient.ContactInformation contactInformation) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);

        if (medicalRecord != null) {
            medicalRecord.getPatient().updateContactInformation(contactInformation);
            medicalRecordsRepository.save(medicalRecord);
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }
}
