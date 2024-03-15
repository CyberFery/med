package uqam.team17.ramqservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.ramqservice.model.medical_record.MedicalRecord;

@RestController
@RequestMapping("/ramq")
public class RamqController {
    @GetMapping("/medical-record-copy")
    public String getMedicalRecordCopy() {
        return "Here's a medical record copy!";
    }

    @GetMapping("/medical-record-copies")
    public String getAllMedicalRecordCopies() {
        return "Here's all the medical record copies!";
    }

    @PostMapping("/medical-record")
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Medical record created successfully");
    }
}
