package uqam.team17.modificationsarchiveservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uqam.team17.modificationsarchiveservice.service.ModificationService;
import uqam.team17.modificationsarchiveservice.model.Modification;
import uqam.team17.modificationsarchiveservice.model.ModificationRequest;
import uqam.team17.modificationsarchiveservice.model.ModificationIdRequest;



@RestController
public class ModificationArchiveController{
    //public static final String ROOT_PATH = "/modifications";

    private final ModificationService modificationService;

    public ModificationArchiveController (ModificationService modification){
        this.modificationService = modification;
    }

    @GetMapping("/modifications")
    public Modification getModification(@RequestBody ModificationIdRequest idRequest){
        Long modificationId = idRequest.getModificationId();
        return modificationService.getModification(modificationId);
    }


   @PostMapping("/modifications")
    public ResponseEntity<?> createModification(@RequestBody Modification modification){
       if (modification == null || modification.getContent() == null) {
           return ResponseEntity.badRequest().body("Invalid Modification Format!");
       }

       Modification mod = modificationService.saveModification(modification);

       return ResponseEntity.ok(mod);

   }






}