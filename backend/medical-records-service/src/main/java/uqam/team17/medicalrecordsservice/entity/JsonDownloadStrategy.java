package uqam.team17.medicalrecordsservice.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonDownloadStrategy implements DownloadStrategy {
    @Override
    public void downloadMedicalRecord(MedicalRecord medicalRecord) {
        String filePath = "./MedicalRecord.json";
        writeMedicalRecordToJsonFile(medicalRecord, filePath);
    }

    private static void writeMedicalRecordToJsonFile(MedicalRecord medicalRecord, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), medicalRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
