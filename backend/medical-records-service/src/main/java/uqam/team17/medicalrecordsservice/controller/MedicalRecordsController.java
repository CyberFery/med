package uqam.team17.medicalrecordsservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.medicalrecordsservice.exception.*;
import uqam.team17.medicalrecordsservice.entity.*;
import uqam.team17.medicalrecordsservice.service.MedicalRecordsService;
import uqam.team17.medicalrecordsservice.utility.*;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordsController {
    private final MedicalRecordsService medicalRecordsService;

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @PostMapping("/patient")
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        if (medicalRecord == null || medicalRecord.getPatient() == null
                || medicalRecord.getMedicalVisitList() == null || medicalRecord.getMedicalVisitList().isEmpty()
                || medicalRecord.getMedicalHistoryList() == null || medicalRecord.getMedicalHistoryList().isEmpty())
            return ResponseEntity.badRequest().body("Invalid medical record format!");

        MedicalRecord savedMedicalRecord = medicalRecordsService.saveMedicalRecord(medicalRecord);

        return ResponseEntity.ok(savedMedicalRecord);
    }

    @GetMapping("/patient")
    public ResponseEntity<?> getMedicalRecord(@RequestBody HealthInsuranceNumber request) {
        if (Validation.validHealthInsuranceNumber(request.healthInsuranceNumber())) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }

        MedicalRecord medicalRecord = medicalRecordsService.getMedicalRecord(request.healthInsuranceNumber());

        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical Record not found for patient with health insurance number : " + request.healthInsuranceNumber());
        }

        medicalRecordsService.downloadMedicalRecordJsonStrategy(medicalRecord);
        medicalRecordsService.downloadMedicalRecordTxtStrategy(medicalRecord);
        return ResponseEntity.ok(medicalRecord);
    }

    @PutMapping("/update-contact-information")
    public ResponseEntity<?> updateContactInformation(@RequestBody ContactInformationRequest request) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getContactInformation() == null)
            return ResponseEntity.badRequest().body("Failed to update contact information, invalid format");

        Patient.ContactInformation contactInformation = medicalRecordsService.updateContactInformation(request.getHealthInsuranceNumber(), request.getContactInformation());

        return ResponseEntity.ok(contactInformation);
    }

    @PutMapping("/update-medical-visit")
    public ResponseEntity<?> updateMedicalVisit(@RequestBody MedicalVisitRequest request) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getMedicalVisit() == null)
            return ResponseEntity.badRequest().body("Failed to update medical visit, invalid format");

        MedicalVisit medicalVisit = medicalRecordsService.updateMedicalVisit(request.getHealthInsuranceNumber(), request.getMedicalVisit());

        return ResponseEntity.ok(medicalVisit);
    }

    @PutMapping("/update-medical-history")
    public ResponseEntity<?> updateMedicalHistory(@RequestBody MedicalHistoryRequest request) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getMedicalHistory() == null) {
            return ResponseEntity.badRequest().body("Failed to update medical history, invalid format");
        }

        MedicalHistory medicalHistory = medicalRecordsService.updateMedicalHistory(request.getHealthInsuranceNumber(), request.getMedicalHistory());

        return ResponseEntity.ok(medicalHistory);
    }

    @DeleteMapping("/delete-medical-visit")
    public ResponseEntity<?> deleteMedicalVisit(@RequestBody HealthInsuranceNumber request, @RequestParam Long medicalVisitId) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(request.healthInsuranceNumber())) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }
        MedicalVisit medicalVisit = medicalRecordsService.deleteMedicalVisit(request.healthInsuranceNumber(), medicalVisitId);

        return ResponseEntity.ok(medicalVisit);
    }

    @DeleteMapping("/delete-medical-history")
    public ResponseEntity<?> deleteMedicalHistory(@RequestBody HealthInsuranceNumber request, @RequestParam Long medicalHistoryId) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(request.healthInsuranceNumber())) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }
        MedicalHistory medicalHistory = medicalRecordsService.deleteMedicalHistory(request.healthInsuranceNumber(), medicalHistoryId);

        return ResponseEntity.ok(medicalHistory);
    }

}
