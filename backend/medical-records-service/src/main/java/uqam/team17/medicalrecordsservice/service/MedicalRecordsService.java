package uqam.team17.medicalrecordsservice.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.medicalrecordsservice.ExceptionHandler.*;
import uqam.team17.medicalrecordsservice.entity.*;
import uqam.team17.medicalrecordsservice.entity.MedicalRecord;
import uqam.team17.medicalrecordsservice.entity.MedicalVisit;
import uqam.team17.medicalrecordsservice.entity.Patient;
import uqam.team17.medicalrecordsservice.repository.MedicalRecordsRepository;

import java.util.List;

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
    public void updateMedicalVisit(String healthInsuranceNumber, MedicalVisit medicalVisit) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);
        if (medicalRecord != null) {
            List<MedicalVisit> medicalVisitList = medicalRecord.getMedicalVisitList();
            medicalVisitList.add(medicalVisit);
            medicalRecord.setMedicalVisitList(medicalVisitList);
            medicalRecordsRepository.save(medicalRecord);
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }

    }
    public void updateMedicalHistory(String healthInsuranceNumber, MedicalHistory medicalHistory) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);
        if (medicalRecord != null) {
            List<MedicalHistory> medicalHistoryList = medicalRecord.getMedicalHistoryList();
            medicalHistoryList.add(medicalHistory);
            medicalRecord.setMedicalHistoryList(medicalHistoryList);
            medicalRecordsRepository.save(medicalRecord);
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordsRepository.save(medicalRecord);
    }
}
