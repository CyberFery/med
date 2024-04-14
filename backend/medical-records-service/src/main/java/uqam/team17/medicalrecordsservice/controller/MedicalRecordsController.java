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
    public ResponseEntity<?> getMedicalRecord(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber) {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }

        MedicalRecord medicalRecord = medicalRecordsService.getMedicalRecord(healthInsuranceNumber);

        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical Record not found for patient with health insurance number : " + healthInsuranceNumber);
        }

        medicalRecordsService.downloadMedicalRecordJsonStrategy(medicalRecord);
        medicalRecordsService.downloadMedicalRecordTxtStrategy(medicalRecord);
        return ResponseEntity.ok(medicalRecord);
    }

    @PutMapping("/update-contact-information")
    public ResponseEntity<?> updateContactInformation(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber, @RequestBody Patient.ContactInformation contactInformation) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)
                || contactInformation == null
                || contactInformation.getResidentialAddressList() == null
                || contactInformation.getPhoneNumberList() == null
                || contactInformation.getEmailAddressList() == null
        )
            return ResponseEntity.badRequest().body("Failed to update contact information, invalid format");

        Patient.ContactInformation contactInformationUpdated = medicalRecordsService.updateContactInformation(healthInsuranceNumber, contactInformation);

        return ResponseEntity.ok(contactInformationUpdated);
    }

    @PutMapping("/update-medical-visit")
    public ResponseEntity<?> updateMedicalVisit(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber, @RequestBody MedicalVisit medicalVisit) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)
                || medicalVisit == null
                || medicalVisit.getDiagnosisList() == null
        )
            return ResponseEntity.badRequest().body("Failed to update medical visit, invalid format");

        MedicalVisit medicalVisitUpdated = medicalRecordsService.updateMedicalVisit(healthInsuranceNumber, medicalVisit);

        return ResponseEntity.ok(medicalVisitUpdated);
    }

    @PutMapping("/update-medical-history")
    public ResponseEntity<?> updateMedicalHistory(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber, @RequestBody MedicalHistory medicalHistory) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)
                || medicalHistory == null
                || medicalHistory.getIllnessList() == null
        ) {
            return ResponseEntity.badRequest().body("Failed to update medical history, invalid format");
        }

        MedicalHistory medicalHistoryUpdated = medicalRecordsService.updateMedicalHistory(healthInsuranceNumber, medicalHistory);

        return ResponseEntity.ok(medicalHistoryUpdated);
    }

    @DeleteMapping("/delete-medical-visit")
    public ResponseEntity<?> deleteMedicalVisit(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber, @RequestParam Long medicalVisitId) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }
        MedicalVisit medicalVisit = medicalRecordsService.deleteMedicalVisit(healthInsuranceNumber, medicalVisitId);

        return ResponseEntity.ok(medicalVisit);
    }

    @DeleteMapping("/delete-medical-history")
    public ResponseEntity<?> deleteMedicalHistory(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber, @RequestParam Long medicalHistoryId) throws MedicalRecordsException {
        if (Validation.validHealthInsuranceNumber(healthInsuranceNumber)) {
            return ResponseEntity.badRequest().body("Invalid health insurance number format!");
        }
        MedicalHistory medicalHistory = medicalRecordsService.deleteMedicalHistory(healthInsuranceNumber, medicalHistoryId);

        return ResponseEntity.ok(medicalHistory);
    }

}
