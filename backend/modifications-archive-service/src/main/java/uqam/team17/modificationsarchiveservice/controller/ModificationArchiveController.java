package uqam.team17.modificationsarchiveservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uqam.team17.modificationsarchiveservice.service.*;
import uqam.team17.modificationsarchiveservice.model.ModificationRequest;

@RestController
public class ModificationArchiveController{
    public static final String ROOT_PATH = "/modifications";

    private ModificationsArchiveService modificationsService;

    public ModificationArchiveController (ModificationsArchiveService modificationsArchiveService){
        this.modificationsService = modificationsArchiveService;
    }

   @PostMapping(ROOT_PATH)
    public void createModification(@RequestBody ModificationRequest){

   }




}