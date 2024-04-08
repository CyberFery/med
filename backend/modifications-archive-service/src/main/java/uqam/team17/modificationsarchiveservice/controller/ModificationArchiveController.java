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

    @GetMapping("/get-modif")
    public ResponseEntity<?> getModification(@RequestBody CancelModificationRequest cancelRequest){
        try {
            Optional<Modification> response = modificationService.cancelLastModification(cancelRequest);

            if(response.isPresent()){
                Modification modification = response.get();
                return ResponseEntity.ok().body("The modification with the ID " + modification.getModificationId() + " is cancelled");
            }else{
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find the thing");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
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
            return ResponseEntity.ok(response);

        }

    }catch (Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
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

   @PostMapping("/cancel-modification")
    public ResponseEntity<?> cancelModification(@RequestBody CancelModificationRequest cancelRequest){
        try {
            Optional<Modification> response = modificationService.cancelLastModification(cancelRequest);

            if(response.isPresent()){
                Modification modification = response.get();
                return ResponseEntity.ok().body("The modification with the ID " + modification.getModificationId() + " is cancelled");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find the thing");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error happened");
        }
   }


}