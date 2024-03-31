package uqam.team17.medicalRecordsService.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqam.team17.medicalRecordsService.entity.MedicalRecord;
import uqam.team17.medicalRecordsService.entity.Patient;
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
    public ResponseEntity<?> getMedicalRecord(@RequestBody HealthInsuranceNumber request) throws Exception {
        if ( !Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())) {
           return ResponseEntity.badRequest().body("Invalid health insurance number format!");
       }
        MedicalRecord medicalRecord = medicalRecordsService.getMedicalRecord(request.getHealthInsuranceNumber());
       return ResponseEntity.ok(medicalRecord);
    }

    @PutMapping("/update-contact-information")
    public ResponseEntity<?> updateContactInformation(@RequestBody ContactInformationRequest request) throws Exception {
        System.out.println("number: " + request.getHealthInsuranceNumber());
        if (!Validation.validHealthInsuranceNumber(request.getHealthInsuranceNumber())
                || request.getContactInformation() == null
                || ((request.getContactInformation().getEmailAddressList() == null
                || request.getContactInformation().getEmailAddressList().isEmpty())
                && (request.getContactInformation().getResidentialAddressList() == null
                || request.getContactInformation().getResidentialAddressList().isEmpty())
                && (request.getContactInformation().getPhoneNumberList() == null
                || request.getContactInformation().getPhoneNumberList().isEmpty())))
            return ResponseEntity.badRequest().body("Failed to update contact information, invalid format");

        medicalRecordsService.updateContactInformation(request.getHealthInsuranceNumber(), request.getContactInformation());
        return ResponseEntity.ok(request.getContactInformation());
    }
}
