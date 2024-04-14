package uqam.team17.modificationsarchiveservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uqam.team17.modificationsarchiveservice.controller.ModificationArchiveController;
import uqam.team17.modificationsarchiveservice.dto.CancelModificationRequest;
import uqam.team17.modificationsarchiveservice.dto.ContactInformationRequest;
import uqam.team17.modificationsarchiveservice.dto.MedicalHistoryRequest;
import uqam.team17.modificationsarchiveservice.dto.MedicalVisitRequest;
import uqam.team17.modificationsarchiveservice.entity.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModificationControllerTest {

    private final String healthInsuranceNumber = "TREM123453422";

    @Mock
    private ModificationService mockService;

    @InjectMocks
    private ModificationArchiveController mockController;

    @Test
    public void createContactInformationModificationTest(){
        List<ContactInformation.ResidentialAddress> addressList = List.of(
                new ContactInformation.ResidentialAddress("2342 Ontario"),
                new ContactInformation.ResidentialAddress("23 St-Laurent")
        );
        List<ContactInformation.PhoneNumber> phoneList = List.of(
                new ContactInformation.PhoneNumber("4389193345")
        );
        List<ContactInformation.EmailAddress> emailList = List.of(
                new ContactInformation.EmailAddress("mariotrembley@chum.qc.ca"),
                new ContactInformation.EmailAddress("superfly432@gmail.com")
        );

        ContactInformationRequest contactRequest = new ContactInformationRequest();
        contactRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        contactRequest.setContactInformation(new ContactInformation(1L, addressList, phoneList, emailList));

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(contactRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(contactRequest.getContactInformation().getType())
                .addContact(contactRequest.getContactInformation())
                .addStatus(Modification.Status.UPDATE)
                .build();


        when(mockService.saveContactInformation(any())).thenReturn(expected);

        ResponseEntity<?> response = mockController.createContactInfoModification(contactRequest);

        verify(mockService, times(1)).saveContactInformation(contactRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }


    @Test
    public void createMedicalHistoryModificationTest(){
        Doctor doctor = new Doctor(1256L, "John", "Doe","Cardiologist" );
        LocalDate start = LocalDate.of(2023,12,20);
        LocalDate end = LocalDate.of(2024,3,30);
        List<MedicalHistory.Illness> illnessList = List.of(
                new MedicalHistory.Illness("description1", start, end),
                new MedicalHistory.Illness("description2", start, end)
        );

        MedicalHistoryRequest historyRequest = new MedicalHistoryRequest();
        historyRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        historyRequest.setMedicalHistory(new MedicalHistory(2L, "diagnosis",
                "treatment",illnessList,doctor ));

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(historyRequest.getMedicalHistory().getType())
                .addHistory(historyRequest.getMedicalHistory())
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.saveMedicalHistory(any())).thenReturn(expected);

        ResponseEntity<?> response = mockController.createMedicalHistoryModification(historyRequest);

        verify(mockService, times(1)).saveMedicalHistory(historyRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());

    }


    @Test
    public void createMedicalVisitModification(){
        Doctor doctor = new Doctor(4566L, "Marie", "Curing", "Oncologist");
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        String hospital = "General Hospital";
        List<MedicalVisit.Diagnosis> diagnosisList = List.of(
                new MedicalVisit.Diagnosis("Heart Murmur", "medication"),
                new MedicalVisit.Diagnosis("lack of sleep", "sleep medication")
        );

        MedicalVisitRequest visitRequest = new MedicalVisitRequest();
        visitRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        visitRequest.setMedicalVisit(new MedicalVisit(3L, hospital, doctor, visitDate,
                diagnosisList, "Summary", "Note" ));

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(visitRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(visitRequest.getMedicalVisit().getType())
                .addVisit(visitRequest.getMedicalVisit())
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.saveMedicalVisit(any())).thenReturn(expected);

        ResponseEntity<?> response = mockController.createMedicalVisitModification(visitRequest);

        verify(mockService, times(1)).saveMedicalVisit(visitRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());


    }


    @Test
    public void cancelModificationSuccessTest(){
        Doctor doctor = new Doctor(4566L, "Marie", "Curing", "Oncologist");
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        String hospital = "General Hospital";
        List<MedicalVisit.Diagnosis> diagnosisList = List.of(
                new MedicalVisit.Diagnosis("Heart Murmur", "medication"),
                new MedicalVisit.Diagnosis("lack of sleep", "sleep medication")
        );

        MedicalVisit visit = new MedicalVisit(4L, hospital, doctor, visitDate,
                diagnosisList, "Summary", "Note" );

        CancelModificationRequest cancelRequest = new CancelModificationRequest();
        cancelRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        cancelRequest.setType(ModificationType.MEDICAL_VISIT);

        Modification expected = new ModificationBuilder()
                .addModificationId(5L)
                .addHealthInsuranceNumber(cancelRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(visit.getType())
                .addVisit(visit)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.cancelLastModification(any())).thenReturn(Optional.of(expected));

        ResponseEntity<?> response = mockController.cancelModification(cancelRequest);

        verify(mockService, times(1)).cancelLastModification(cancelRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The modification with the ID 5 is cancelled", response.getBody());

    }

    @Test
    public void cancelModificationFailureTest(){

        CancelModificationRequest cancelRequest = new CancelModificationRequest();
        cancelRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        cancelRequest.setType(ModificationType.CONTACT_INFORMATION);

        when(mockService.cancelLastModification(any())).thenReturn(Optional.empty());

        ResponseEntity<?> response = mockController.cancelModification(cancelRequest);

        verify(mockService, times(1)).cancelLastModification(cancelRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Couldn't find the thing", response.getBody());



    }



}
