package uqam.team17.modificationsarchiveservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;



import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/modifications")
public class ModificationArchiveController{
    //public static final String ROOT_PATH = "/modifications";

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

    /**
     *    @PostMapping("/modifications")
     *     public ResponseEntity<?> createModification(@RequestBody Modification modification){
     *        if (modification == null || modification.get == null) {
     *            return ResponseEntity.badRequest().body("Invalid Modification Format!");
     *        }
     *
     *        Modification mod = modificationService.saveModification(modification);
     *
     *        return ResponseEntity.ok(mod);
     *
     *    }
     *
     *
     */

   @PostMapping("-medical-visit")
   public ResponseEntity<?> createMedicalVisitModification(@RequestBody MedicalVisitRequest visitRequest){
       if(visitRequest == null || visitRequest.getMedicalVisit().getVisitedEstablishment() == null ||
               visitRequest.getMedicalVisit().getVisitDate() == null ||
               visitRequest.getMedicalVisit().getDoctorSeen() == null ||
               visitRequest.getMedicalVisit().getDiagnosisList().isEmpty() ||
               visitRequest.getMedicalVisit().getDiagnosisList() == null){

           return ResponseEntity.badRequest().body("Failed to backup medical visit, wrong format");
       }else {
           return ResponseEntity.ok().body("PLACEHOLDER VISIT");
       }
   }


   @PostMapping("-medical-history")
   public ResponseEntity<?> createMedicalHistoryModification(@RequestBody MedicalHistoryRequest historyRequest){
       if(historyRequest == null || historyRequest.getHealthInsuranceNumber() == null ||
               historyRequest.getMedicalHistory().getDiagnosis() == null ||
               historyRequest.getMedicalHistory().getTreatment() == null ||
               historyRequest.getMedicalHistory().getIllnessList().isEmpty() ||
               historyRequest.getMedicalHistory().getIllnessList() == null){

           return ResponseEntity.badRequest().body("Failed to backup medical history, wrong format");
       }else {
            return ResponseEntity.ok().body("PLACEHOLDER Medical History");
       }

   }

   @PostMapping("-contact-information")
    public ResponseEntity<?> createContactInfoModification(@RequestBody ContactInformationRequest contactRequest){
       if(contactRequest == null || contactRequest.getHealthInsuranceNumber() == null ||
            contactRequest.getContactInformation().getEmailAddressList().isEmpty() ||
            contactRequest.getContactInformation().getEmailAddressList() == null ||
            contactRequest.getContactInformation().getPhoneNumberList().isEmpty() ||
               contactRequest.getContactInformation().getPhoneNumberList() == null ||
            contactRequest.getContactInformation().getResidentialAddressList().isEmpty() ||
               contactRequest.getContactInformation().getResidentialAddressList() == null){

           return ResponseEntity.badRequest().body("Failed to backup contact information, wrong format");
       }else {
           String serializedContactInfo = serializeToJson(contactRequest.getContactInformation());
           if(serializedContactInfo == null){
               return ResponseEntity.badRequest().body("Failed to serialized the contact information");
           }

           Modification modification = new Modification();
           modification.setHealthInsuranceNumber(contactRequest.getHealthInsuranceNumber());
           modification.setTimestamp(LocalDateTime.now());
           modification.setType(Modification.ModificationType.CONTACTINFORMATION);
           modification.setModificationData(serializedContactInfo);

           final Modification response = modificationService.saveModification(modification);

           return ResponseEntity.ok().body(response);
       }

   }


   private String serializeToJson(Object object){
       try {
           ObjectMapper mapper = new ObjectMapper();
           return mapper.writeValueAsString(object);
       } catch (JsonProcessingException e){
           return null;
       }
   }






}