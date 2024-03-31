package uqam.team17.medicalRecordsService.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public MedicalRecord getMedicalRecord(String healthInsuranceNumber) throws Exception {
        MedicalRecord medicalRecord = medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber);
        if (medicalRecord != null) {
            return medicalRecord;
        }
        throw new Exception("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
    }
    public void updateContactInformation(String healthInsuranceNumber, Patient.ContactInformation contactInformation) throws Exception {
        MedicalRecord medicalRecord = medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber);
        System.out.println("number: " + healthInsuranceNumber);
        System.out.println("Contact:" + contactInformation);
        if (medicalRecord != null) {
            medicalRecord.getPatient().updateContactInformation(contactInformation);
            System.out.println("Contact:" + medicalRecord.getPatient().getContactInformation());
            medicalRecordsRepository.save(medicalRecord);
        } else {
            throw new ClassNotFoundException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }
}
