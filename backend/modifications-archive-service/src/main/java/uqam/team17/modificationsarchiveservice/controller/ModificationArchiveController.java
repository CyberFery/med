package uqam.team17.modificationsarchiveservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uqam.team17.modificationsarchiveservice.entity.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;



@RestController
@RequestMapping("/modifications")
public class ModificationArchiveController {
    private final ModificationService modificationService;

    public ModificationArchiveController(ModificationService modification) {
        this.modificationService = modification;
    }

    @PostMapping("/medical-visit")
    public ResponseEntity<?> createMedicalVisitModification(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber,
                                                            @RequestBody MedicalVisit visit) {
        try {
            if ( healthInsuranceNumber == null ||
                    visit == null ||
                    visit.getDiagnosisList() == null) {

                return ResponseEntity.badRequest().body("Failed to backup medical visit, wrong format");
            } else {

                final Modification response = modificationService.saveMedicalVisit(healthInsuranceNumber, visit);
                if(response != null){
                    return ResponseEntity.ok().body("Medical visit has been correctly archived");
                }else {
                    return ResponseEntity.ok().body("The medical visit has not been archived");
                }

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }
    }

    @PostMapping("/medical-history")
    public ResponseEntity<?> createMedicalHistoryModification(@RequestHeader("healthInsuranceNumber")
                                                                  String healthInsuranceNumber,
                                                              @RequestBody MedicalHistory history) {

        try {
            if (healthInsuranceNumber == null || history == null ||
                history.getIllnessList() == null) {

                return ResponseEntity.badRequest().body("Failed to backup medical history, wrong format");
            } else {

                final Modification response = modificationService.saveMedicalHistory(healthInsuranceNumber, history);
                if(response != null){
                    return ResponseEntity.ok().body("Medical history has been correctly archived");
                }else {
                    return ResponseEntity.ok().body("The medical history has not been archived");
                }

            }

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }
    }

    @PostMapping("/contact-information")
    public ResponseEntity<?> createContactInfoModification(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber,
                                                           @RequestBody ContactInformation contact) {
        try {
            if (healthInsuranceNumber == null || contact == null ||
                contact.getResidentialAddressList() == null ||
                contact.getPhoneNumberList() == null ||
                contact.getEmailAddressList() == null) {
                return ResponseEntity.badRequest().body("Failed to backup contact information, wrong format");
            } else {

                final Modification response = modificationService.saveContactInformation(healthInsuranceNumber, contact);

                if(response != null){
                    return ResponseEntity.ok().body("Contact Information has been correctly archived");
                }else {
                    return ResponseEntity.ok().body("Contact Information has not been archived");
                }

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }

    }

    @PostMapping("/cancel-medical-visit")
    public ResponseEntity<?> cancelMedicalVisit(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber,
                                                @RequestBody MedicalVisit visit){
        try {
            if (healthInsuranceNumber == null || visit == null) {
                return ResponseEntity.badRequest().body("Failed to cancel medical visit, wrong format");
            } else {

                final Modification response = modificationService.cancelVisit(healthInsuranceNumber, visit);
                if(response != null){
                    return ResponseEntity.ok().body("Medical visit has been cancelled");
                }else {
                    return ResponseEntity.ok().body("The medical visit has not been cancelled");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }

    }

    @PostMapping("/cancel-medical-history")
    public ResponseEntity<?> cancelMedicalHistory(@RequestHeader("healthInsuranceNumber") String healthInsuranceNumber,
                                                @RequestBody MedicalHistory history){
        try {
            if (healthInsuranceNumber == null || history == null) {
                return ResponseEntity.badRequest().body("Failed to cancel medical visit, wrong format");
            } else {

                final Modification response = modificationService.cancelHistory(healthInsuranceNumber, history);
                if(response != null){
                    return ResponseEntity.ok().body("Medical history has been cancelled");
                }else {
                    return ResponseEntity.ok().body("The medical history has not been cancelled");
                }

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }

    }


}