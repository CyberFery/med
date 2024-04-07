package uqam.team17.modificationsarchiveservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

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
     try {
         if (visitRequest == null || !visitRequest.isValid()) {

             return ResponseEntity.badRequest().body("Failed to backup medical visit, wrong format");
         } else {

             final Modification response = modificationService.saveMedicalVisit(visitRequest);
             return ResponseEntity.ok().body(response);
         }
     }catch(Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
     }
   }

   @PostMapping("/medical-history")
   public ResponseEntity<?> createMedicalHistoryModification(@RequestBody MedicalHistoryRequest historyRequest){

    try {
        if (historyRequest == null || !historyRequest.isValid()) {

            return ResponseEntity.badRequest().body("Failed to backup medical history, wrong format");
        } else {

            final Modification response = modificationService.saveMedicalHistory(historyRequest);

            if(response != null){
                return ResponseEntity.ok().body(response);
            }else {
                return ResponseEntity.badRequest().body("The saving failed the response is null");
            }


        }

    }catch (NullPointerException np){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a null pointer");

    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened - f");
    }
   }

   @PostMapping("/contact-information")
    public ResponseEntity<?> createContactInfoModification(@RequestBody ContactInformationRequest contactRequest){
     try {
         if (contactRequest == null || !contactRequest.isValid()) {
             return ResponseEntity.badRequest().body("Failed to backup contact information, wrong format");
         } else {

             final Modification response = modificationService.saveContactInformation(contactRequest);
             return ResponseEntity.ok().body(response);

         }
     }catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
     }

   }


}