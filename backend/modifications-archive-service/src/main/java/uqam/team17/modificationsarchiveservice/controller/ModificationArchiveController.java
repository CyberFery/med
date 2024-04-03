package uqam.team17.modificationsarchiveservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uqam.team17.modificationsarchiveservice.model.ContactInformation;
import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;



import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/modifications")
public class ModificationArchiveController{
    private final ModificationService modificationService;

    public ModificationArchiveController (ModificationService modification){
        this.modificationService = modification;
    }


    @GetMapping("/modifications")
    public ResponseEntity<?> getModification(@RequestParam Long modificationId){
        Optional<Modification> optionalMod = modificationService.getModificationById(modificationId);

        if(optionalMod.isPresent()){
            Modification modification = optionalMod.get();
            return ResponseEntity.ok().body(modification);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modification with the ID" + modificationId +
                    " is not found");
        }
    }




   @PostMapping("/medical-visit")
   public ResponseEntity<?> createMedicalVisitModification(@RequestBody MedicalVisitRequest visitRequest){
       if(visitRequest == null || visitRequest.getMedicalVisit().getVisitedEstablishment() == null ||
               visitRequest.getMedicalVisit()  == null ||
               visitRequest.getMedicalVisit().getVisitDate() == null ||
               visitRequest.getMedicalVisit().getDoctorSeen() == null ||
               visitRequest.getMedicalVisit().getDiagnosisList().isEmpty() ||
               visitRequest.getMedicalVisit().getDiagnosisList() == null){

           return ResponseEntity.badRequest().body("Failed to backup medical visit, wrong format");
       }else {
           MedicalVisit medicalVisit = visitRequest.getMedicalVisit();

           Modification modification = new Modification();
           modification.setHealthInsuranceNumber(visitRequest.getHealthInsuranceNumber());
           modification.setTimestamp(LocalDateTime.now());
           modification.setType(medicalVisit.getType());
           modification.setModifiable(medicalVisit);
           modification.setStatus(Modification.Status.UPDATE);

           final Modification response = modificationService.saveModification(modification);

           if(response != null){
               return ResponseEntity.ok().body(response);
           }else {
               return ResponseEntity.badRequest().body("Trouble Medical Visit inserting in the database");
           }
       }
   }


   @PostMapping("/medical-history")
   public ResponseEntity<?> createMedicalHistoryModification(@RequestBody MedicalHistoryRequest historyRequest){
       if(historyRequest == null || historyRequest.getHealthInsuranceNumber() == null ||
               historyRequest.getMedicalHistory()  == null ||
               historyRequest.getMedicalHistory().getDiagnosis() == null ||
               historyRequest.getMedicalHistory().getTreatment() == null ||
               historyRequest.getMedicalHistory().getIllnessList().isEmpty() ||
               historyRequest.getMedicalHistory().getIllnessList() == null){

           return ResponseEntity.badRequest().body("Failed to backup medical history, wrong format");
       }else {
           MedicalHistory medicalHistory = historyRequest.getMedicalHistory();

           Modification modification = new Modification();
           modification.setHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber());
           modification.setTimestamp(LocalDateTime.now());
           modification.setType(medicalHistory.getType());
           modification.setModifiable(medicalHistory);
           modification.setStatus(Modification.Status.UPDATE);

           final Modification response = modificationService.saveModification(modification);

           if(response != null){
               return ResponseEntity.ok().body(response);
           }else {
               return ResponseEntity.badRequest().body("Trouble Medical History inserting in the database");
           }
       }

   }

   @PostMapping("/contact-information")
    public ResponseEntity<?> createContactInfoModification(@RequestBody ContactInformationRequest contactRequest){
       if(!contactRequest.isValid()){
           return ResponseEntity.badRequest().body("Failed to backup contact information, wrong format");
       }else {

           ContactInformation contactInformation = contactRequest.getContactInformation();

           Modification modification = new Modification();
           modification.setHealthInsuranceNumber(contactRequest.getHealthInsuranceNumber());
           modification.setTimestamp(LocalDateTime.now());
           modification.setType(contactInformation.getType());
           modification.setModifiable(contactInformation);
           modification.setStatus(Modification.Status.UPDATE);

           final Modification response = modificationService.saveModification(modification);

           if(response != null){
              return ResponseEntity.ok().body(response);
           }else {
               return ResponseEntity.badRequest().body("Trouble inserting in the database");
           }
       }

   }

}