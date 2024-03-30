package uqam.team17.medicalRecordsService.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.medicalRecordsService.entity.MedicalRecord;
import uqam.team17.medicalRecordsService.service.MedicalRecordsService;
import uqam.team17.medicalRecordsService.utility.Validation;
@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordsController {
    private final MedicalRecordsService medicalRecordsService;

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService){
        this.medicalRecordsService = medicalRecordsService;
    }

    @GetMapping("/medical-record")
    public ResponseEntity<?> getMedicalRecord(@RequestBody String healthInsuranceNumber) {
       if ( !Validation.validHealthInsuranceNumber(healthInsuranceNumber)) {
           return ResponseEntity.badRequest().body("Invalid health insurance number format!");
       }
        MedicalRecord medicalRecord = medicalRecordsService.getMedicalRecord(healthInsuranceNumber);
       return ResponseEntity.ok(medicalRecord);
    }
}
