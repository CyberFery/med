package uqam.team17.modificationsarchiveservice;

import uqam.team17.modificationsarchiveservice.entity.*;
import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ModificationServiceTest {

    private final Doctor doctor1 = new Doctor(1L,1256L, "John", "Doe","Cardiologist" );
    private final Doctor doctor2 = new Doctor(2L,4566L, "Marie", "Curing", "Oncologist");
    private final String healthInsuranceNumber = "ABCD123456789";
    private final String healthInsuranceNumber2 = "ABCD123456745";
    private final LocalDate date1 = LocalDate.of(2023,12,20);
    private final LocalDate date2 = LocalDate.of(2024,3,30);
    private final LocalDate date3 = LocalDate.of(2023, 11, 15);
    private final MedicalVisit.Diagnosis diag1 = new MedicalVisit.Diagnosis("Heart Murmur", "medication");
    private final MedicalVisit.Diagnosis diag2 = new MedicalVisit.Diagnosis("lack of sleep", "sleep medication");
    private final MedicalHistory.Illness illness1 = new MedicalHistory.Illness("description1", date1, date2);
    private final MedicalHistory.Illness illness2 = new MedicalHistory.Illness("description2", date1, date2);

    @Mock
    private ModificationArchiveRepository mockRepository;

    @InjectMocks
    private ModificationService mockService;

    @BeforeAll
    public static void setUp(){
        MockitoAnnotations.openMocks(ModificationServiceTest.class);
    }

    @Test
    public void saveContactInformationTest(){
        List<ContactInformation.ResidentialAddress> addressList1 = new ArrayList<>(){{
            new ContactInformation.ResidentialAddress("405 rue Sainte-Catherine");}};
        List<ContactInformation.PhoneNumber> phoneList = new ArrayList<>(){{
            new ContactInformation.PhoneNumber("5148943256");
        }};
        List<ContactInformation.EmailAddress> emailList = new ArrayList<>(){{
            new ContactInformation.EmailAddress("johnsmith@gmail.com");
        }};

        ContactInformation contact = new ContactInformation(1L,
                addressList1, phoneList,emailList);

        Modification mockModif = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(contact.getType())
                .addContact(contact)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockRepository.save(any(Modification.class))).thenReturn(mockModif);

        Modification savedMod = mockService.saveContactInformation(healthInsuranceNumber, contact);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModif, savedMod);

    }

    @Test
    public void saveMedicalHistoryTest(){
        List<MedicalHistory.Illness> listIllness = new ArrayList<>();
        listIllness.add(illness1);
        listIllness.add(illness2);

        MedicalHistory history = new MedicalHistory(3L,245L, "sick", "pending",
                listIllness, doctor1);


        Modification mockModification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber2)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(history.getType())
                .addHistory(history)
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockRepository.save(any(Modification.class))).thenReturn(mockModification);

        Modification saveMod = mockService.saveMedicalHistory(healthInsuranceNumber2, history);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModification,saveMod);
    }

    @Test
    public void saveMedicalVisit(){
        List<MedicalVisit.Diagnosis> listDiag = new ArrayList<>();
        String hospital = "General Hospital";

        listDiag.add(diag1);
        listDiag.add(diag2);

        MedicalVisit visit = new MedicalVisit(5L,334L,hospital,doctor2,date3,listDiag,
                "Summary", "Notes");


        Modification mockModification = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber)
                .addTimestamp(LocalDateTime.now())
                .addModificationType(visit.getType())
                .addVisit(visit)
                .addStatus(Modification.Status.UPDATE)
                .build();


        when(mockRepository.save(any(Modification.class))).thenReturn(mockModification);

        Modification saveMod = mockService.saveMedicalVisit(healthInsuranceNumber, visit);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModification,saveMod);


    }



    @Test
    public void cancelVisitTest() {
        List<MedicalVisit.Diagnosis> listDiag = new ArrayList<>();
        listDiag.add(diag1);
        listDiag.add(diag2);

        MedicalVisit visitToCancel =  new MedicalVisit(7L,342L, "General Hospital",
                doctor2, date2,listDiag, "Summary", "Note" );


        Modification result = mockService.cancelVisit(healthInsuranceNumber, visitToCancel);

        ArgumentCaptor<Modification> modificationCaptor = ArgumentCaptor.forClass(Modification.class);
        verify(mockRepository).save(modificationCaptor.capture());
        Modification savedModification = modificationCaptor.getValue();


        assertEquals(healthInsuranceNumber, savedModification.getHealthInsuranceNumber());
        assertEquals(visitToCancel, savedModification.getMedicalVisit());
        assertEquals(Modification.Status.CANCEL, savedModification.getStatus());
    }

    @Test
    public void cancelHistoryTest() {
        List<MedicalHistory.Illness> listIllness = new ArrayList<>();
        listIllness.add(illness1);
        listIllness.add(illness2);


        MedicalHistory historyToCancel = new MedicalHistory(3L,245L, "sick", "pending",
                listIllness, doctor1);

        Modification result = mockService.cancelHistory(healthInsuranceNumber, historyToCancel);

        ArgumentCaptor<Modification> modificationCaptor = ArgumentCaptor.forClass(Modification.class);
        verify(mockRepository).save(modificationCaptor.capture());
        Modification savedModification = modificationCaptor.getValue();


        assertEquals(healthInsuranceNumber, savedModification.getHealthInsuranceNumber());
        assertEquals(historyToCancel, savedModification.getMedicalHistory());
        assertEquals(Modification.Status.CANCEL, savedModification.getStatus());
    }


}
