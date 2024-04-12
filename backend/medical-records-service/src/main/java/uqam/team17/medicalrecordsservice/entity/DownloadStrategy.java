package uqam.team17.medicalrecordsservice.entity;

public interface DownloadStrategy {
    void downloadMedicalRecord(MedicalRecord medicalRecord);
}
