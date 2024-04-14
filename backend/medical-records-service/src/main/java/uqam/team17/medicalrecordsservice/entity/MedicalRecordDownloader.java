package uqam.team17.medicalrecordsservice.entity;

public class MedicalRecordDownloader {
    private DownloadStrategy downloadStrategy;

    public void setDownloadStrategy(DownloadStrategy downloadStrategy) {
        this.downloadStrategy = downloadStrategy;
    }

    public void downloadMedicalRecord(MedicalRecord medicalRecord) {
        downloadStrategy.downloadMedicalRecord(medicalRecord);
    }
}
