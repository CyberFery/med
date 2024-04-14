package uqam.team17.ramqservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.ramqservice.entity.*;
import uqam.team17.ramqservice.service.MedicalRecordCopyService;

import java.util.List;

@RestController
@RequestMapping("/ramq")
public class RamqController {
    private final MedicalRecordCopyService medicalRecordCopyService;

    public RamqController(MedicalRecordCopyService medicalRecordCopyService) {
        this.medicalRecordCopyService = medicalRecordCopyService;
    }

    @GetMapping("/medical-record-copies")
    public List<MedicalRecordCopy> getAllMedicalRecordCopies(@RequestHeader("LoggedInUser") String username) {
        return medicalRecordCopyService.getAllMedicalRecordCopies();
    }

    @PostMapping("/medical-record-copy")
    public ResponseEntity<?> createMedicalRecordCopy( @RequestBody MedicalRecordCopy medicalRecordCopy) {
        if (medicalRecordCopy == null || medicalRecordCopy.getPatient() == null
                || medicalRecordCopy.getMedicalVisitList() == null || medicalRecordCopy.getMedicalVisitList().isEmpty()
                || medicalRecordCopy.getMedicalHistoryList() == null || medicalRecordCopy.getMedicalHistoryList().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid medical record copy format!");
        }

        MedicalRecordCopy savedMedicalRecordCopy = medicalRecordCopyService.saveMedicalRecordCopy(medicalRecordCopy);

        return ResponseEntity.ok(savedMedicalRecordCopy);
    }
}
