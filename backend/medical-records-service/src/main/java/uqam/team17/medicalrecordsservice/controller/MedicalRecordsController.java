package uqam.team17.medicalrecordsservice.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.medicalrecordsservice.ExceptionHandler.MedicalRecordsException;
import uqam.team17.medicalrecordsservice.entity.MedicalRecord;
import uqam.team17.medicalrecordsservice.service.MedicalRecordsService;
import uqam.team17.medicalrecordsservice.utility.*;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordsController {
    private final MedicalRecordsService medicalRecordsService;

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService){
        this.medicalRecordsService = medicalRecordsService;
    }
    @GetMapping("/medical-record")
    public ResponseEntity<?> getMedicalRecord(@RequestBody HealthInsuranceNumber request) {
        if ( !Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())) {
           return ResponseEntity.badRequest().body("Invalid health insurance number format!");
       }
        MedicalRecord medicalRecord = medicalRecordsService.getMedicalRecord(request.getHealthInsuranceNumber());
        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical Record not found for patient with health insurance number : " + request.getHealthInsuranceNumber());
        }
        return ResponseEntity.ok(medicalRecord);
    }

    @PutMapping("/update-contact-information")
    public ResponseEntity<?> updateContactInformation(@RequestBody ContactInformationRequest request) throws Exception {
        if (!Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getContactInformation() == null)
            return ResponseEntity.badRequest().body("Failed to update contact information, invalid format");

        medicalRecordsService.updateContactInformation(request.getHealthInsuranceNumber(), request.getContactInformation());
        return ResponseEntity.ok(request.getContactInformation());
    }

    @PutMapping("/update-medical-visit")
    public ResponseEntity<?> updateMedicalVisit(@RequestBody MedicalVisitRequest request) throws MedicalRecordsException {

        if (!Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getMedicalVisit() == null) {
            return ResponseEntity.badRequest().body("Failed to update medical visit, invalid format");
        }
        medicalRecordsService.updateMedicalVisit(request.getHealthInsuranceNumber(), request.getMedicalVisit());
        return ResponseEntity.ok(request.getMedicalVisit());
    }

    @PutMapping("/update-medical-history")
    public ResponseEntity<?> updateMedicalHistory(@RequestBody MedicalHistoryRequest request) throws MedicalRecordsException {

        if (!Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getMedicalHistory() == null) {
            return ResponseEntity.badRequest().body("Failed to update medical history, invalid format");
        }
        medicalRecordsService.updateMedicalHistory(request.getHealthInsuranceNumber(), request.getMedicalHistory());
        return ResponseEntity.ok(request.getMedicalHistory());
    }

}
