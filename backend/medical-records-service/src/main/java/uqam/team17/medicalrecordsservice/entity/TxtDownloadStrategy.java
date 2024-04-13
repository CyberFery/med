package uqam.team17.medicalrecordsservice.entity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtDownloadStrategy implements DownloadStrategy {
    @Override
    public void downloadMedicalRecord(MedicalRecord medicalRecord) {
        String filePath = "./MedicalRecord.txt";
        writeMedicalRecordToTxt(medicalRecord, filePath);
    }

    private static void writeMedicalRecordToTxt(MedicalRecord medicalRecord, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(medicalRecord.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
