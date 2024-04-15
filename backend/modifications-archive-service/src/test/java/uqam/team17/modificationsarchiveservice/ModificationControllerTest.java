package uqam.team17.modificationsarchiveservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uqam.team17.modificationsarchiveservice.controller.ModificationArchiveController;
import uqam.team17.modificationsarchiveservice.entity.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public void createContactInformationModificationTest() {
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

        ContactInformation contactInfo = new ContactInformation(1L, addressList, phoneList, emailList);

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(contactInfo.getType())
                .addContact(contactInfo)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.saveContactInformation(any(String.class), any(ContactInformation.class)))
                .thenReturn(expected);

        ResponseEntity<?> response = mockController.createContactInfoModification(healthInsuranceNumber, contactInfo);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockService).saveContactInformation(healthInsuranceNumber, contactInfo);
    }

    @Test
    public void createMedicalHistoryModificationTest() {
        Doctor doctor = new Doctor(2L,1256L, "John", "Doe","Cardiologist" );
        LocalDate start = LocalDate.of(2023,12,20);
        LocalDate end = LocalDate.of(2024,3,30);
        List<MedicalHistory.Illness> illnessList = List.of(
                new MedicalHistory.Illness("description1", start, end),
                new MedicalHistory.Illness("description2", start, end)
        );

        MedicalHistory history = new MedicalHistory(4L,4542L, "diagnosis",
                "treatment",illnessList,doctor);

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(history.getType())
                .addHistory(history)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.saveMedicalHistory(any(String.class), any(MedicalHistory.class)))
                .thenReturn(expected);

        ResponseEntity<?> response = mockController.createMedicalHistoryModification(healthInsuranceNumber, history);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockService).saveMedicalHistory(healthInsuranceNumber, history);
    }

    @Test
    public void createMedicalVisitModificationTest() {
        Doctor doctor = new Doctor(8L,4566L, "Marie", "Curing", "Oncologist");
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        String hospital = "General Hospital";
        List<MedicalVisit.Diagnosis> diagnosisList = List.of(
                new MedicalVisit.Diagnosis("Heart Murmur", "medication"),
                new MedicalVisit.Diagnosis("lack of sleep", "sleep medication")
        );

        MedicalVisit visit = new MedicalVisit(9L,45463L, hospital, doctor, visitDate,
                diagnosisList, "Summary", "Note" );

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(visit.getType())
                .addVisit(visit)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockService.saveMedicalVisit(any(String.class), any(MedicalVisit.class)))
                .thenReturn(expected);

        ResponseEntity<?> response = mockController.createMedicalVisitModification(healthInsuranceNumber, visit);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockService).saveMedicalVisit(healthInsuranceNumber, visit);
    }

    @Test
    public void cancelMedicalVisitTest() {
        Doctor doctor = new Doctor(8L,4566L, "Marie", "Curing", "Oncologist");
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        String hospital = "General Hospital";
        List<MedicalVisit.Diagnosis> diagnosisList = List.of(
                new MedicalVisit.Diagnosis("Heart Murmur", "medication"),
                new MedicalVisit.Diagnosis("lack of sleep", "sleep medication")
        );

        MedicalVisit visit = new MedicalVisit(9L,45463L, hospital, doctor, visitDate,
                diagnosisList, "Summary", "Note" );

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber("healthInsuranceNumber")
                .addTimestamp(LocalDateTime.now())
                .addModificationType(ModificationType.MEDICAL_VISIT)
                .addVisit(visit)
                .addStatus(Modification.Status.CANCEL)
                .build();

        when(mockService.cancelVisit(any(String.class), any(MedicalVisit.class)))
                .thenReturn(expected);


        ResponseEntity<?> response = mockController.cancelMedicalVisit("healthInsuranceNumber", visit);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockService).cancelVisit("healthInsuranceNumber", visit);
    }

    @Test
    public void cancelMedicalHistoryTest() {
        Doctor doctor = new Doctor(2L,1256L, "John", "Doe","Cardiologist" );
        LocalDate start = LocalDate.of(2023,12,20);
        LocalDate end = LocalDate.of(2024,3,30);
        List<MedicalHistory.Illness> illnessList = List.of(
                new MedicalHistory.Illness("description1", start, end),
                new MedicalHistory.Illness("description2", start, end)
        );

        MedicalHistory history = new MedicalHistory(4L,4542L, "diagnosis",
                "treatment",illnessList,doctor);

        Modification expected = new ModificationBuilder()
                .addHealthInsuranceNumber("healthInsuranceNumber")
                .addTimestamp(LocalDateTime.now())
                .addModificationType(history.getType())
                .addHistory(history)
                .addStatus(Modification.Status.CANCEL)
                .build();

        when(mockService.cancelHistory(any(String.class), any(MedicalHistory.class)))
                .thenReturn(expected);


        ResponseEntity<?> response = mockController.cancelMedicalHistory("healthInsuranceNumber",history);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mockService).cancelHistory("healthInsuranceNumber", history);
    }









}
