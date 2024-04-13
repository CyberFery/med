package uqam.team17.medicalrecordsservice.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.medicalrecordsservice.exception.*;
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
    private MedicalRecordDownloader downloader = new MedicalRecordDownloader();

    public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
    }
    public MedicalRecord getMedicalRecord(String healthInsuranceNumber) {
       return medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber);
    }

    public void downloadMedicalRecordJsonStrategy(MedicalRecord medicalRecord) {
        DownloadStrategy jsonStrategy = new JsonDownloadStrategy();
        downloader.setDownloadStrategy(jsonStrategy);
        downloader.downloadMedicalRecord(medicalRecord);
    }

    public void downloadMedicalRecordTxtStrategy(MedicalRecord medicalRecord){
        DownloadStrategy txtStrategy = new TxtDownloadStrategy();
        downloader.setDownloadStrategy(txtStrategy);
        downloader.downloadMedicalRecord(medicalRecord);
    }

    public Patient.ContactInformation updateContactInformation(String healthInsuranceNumber, Patient.ContactInformation contactInformation) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);

        if (medicalRecord != null) {
            medicalRecord.getPatient().updateContactInformation(contactInformation);
            medicalRecordsRepository.save(medicalRecord);
            return medicalRecord.getPatient().getContactInformation();
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    public MedicalVisit updateMedicalVisit(String healthInsuranceNumber, MedicalVisit medicalVisit) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);
        if (medicalRecord != null) {
            List<MedicalVisit> medicalVisitList = medicalRecord.getMedicalVisitList();
            medicalVisitList.add(medicalVisit);
            medicalRecord.setMedicalVisitList(medicalVisitList);
            medicalRecordsRepository.save(medicalRecord);
            return medicalVisitList.get(medicalVisitList.size()-1);
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    public MedicalHistory updateMedicalHistory(String healthInsuranceNumber, MedicalHistory medicalHistory) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);
        if (medicalRecord != null) {
            List<MedicalHistory> medicalHistoryList = medicalRecord.getMedicalHistoryList();
            medicalHistoryList.add(medicalHistory);
            medicalRecord.setMedicalHistoryList(medicalHistoryList);
            medicalRecordsRepository.save(medicalRecord);
            return medicalHistoryList.get(medicalHistoryList.size()-1);
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordsRepository.save(medicalRecord);
    }

    public MedicalVisit deleteMedicalVisit(String healthInsuranceNumber, Long medicalVisitId) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);

        if (medicalRecord != null) {
            List<MedicalVisit> medicalVisitList = medicalRecord.getMedicalVisitList();
            int id = findMedicalVisitById(medicalVisitList, medicalVisitId);
            if(id >= 0) {
                MedicalVisit medicalVisit = medicalVisitList.remove(id);
                medicalRecord.setMedicalVisitList(medicalVisitList);
                medicalRecordsRepository.save(medicalRecord);
                return medicalVisit;
            } else {
                throw new MedicalRecordsException("Medical visit not found for patient with medical visit id " + medicalVisitId);
            }
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    public MedicalHistory deleteMedicalHistory(String healthInsuranceNumber, Long medicalHistoryId) throws MedicalRecordsException {
        MedicalRecord medicalRecord = getMedicalRecord(healthInsuranceNumber);

        if (medicalRecord != null) {
            List<MedicalHistory> medicalHistoryList = medicalRecord.getMedicalHistoryList();
            int id = findMedicalHistoryById(medicalHistoryList, medicalHistoryId);
            if(id >= 0) {
                MedicalHistory medicalHistory = medicalHistoryList.remove(id);
                medicalRecord.setMedicalHistoryList(medicalHistoryList);
                medicalRecordsRepository.save(medicalRecord);
                return medicalHistory;
            } else {
                throw new MedicalRecordsException("Medical History not found for patient with medical history id " + medicalHistoryId);
            }
        } else {
            throw new MedicalRecordsException("Medical Record not found for patient with health insurance number " + healthInsuranceNumber);
        }
    }

    private int findMedicalVisitById(List<MedicalVisit> medicalVisitList, Long medicalVisitId) {
        for (int i = 0; i < medicalVisitList.size(); i++) {
            MedicalVisit medicalVisit = medicalVisitList.get(i);
            if (medicalVisit.getMedicalVisitId().equals(medicalVisitId)) {
                return i;
            }
        }
        return -1;
    }

    private int findMedicalHistoryById(List<MedicalHistory> medicalHistoryList, Long medicalHistoryId) {
        for (int i = 0; i < medicalHistoryList.size(); i++) {
            MedicalHistory medicalHistory = medicalHistoryList.get(i);
            if (medicalHistory.getMedicalHistoryId().equals(medicalHistoryId)) {
                return i;
            }
        }
        return -1;
    }
}
