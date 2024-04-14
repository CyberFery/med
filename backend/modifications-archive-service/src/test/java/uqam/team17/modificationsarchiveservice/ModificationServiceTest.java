package uqam.team17.modificationsarchiveservice;

import uqam.team17.modificationsarchiveservice.model.*;
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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ModificationServiceTest {

    private final Doctor doctor1 = new Doctor(1256L, "John", "Doe","Cardiologist" );
    private final Doctor doctor2 = new Doctor(4566L, "Marie", "Curing", "Oncologist");
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

        ContactInformationRequest request = new ContactInformationRequest();
        request.setHealthInsuranceNumber(healthInsuranceNumber);
        request.setContactInformation(contact);

        Modification mockModif = new ModificationBuilder()
                .addHealthInsuranceNumber(request.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(request.getContactInformation().getType())
                .addContact(request.getContactInformation())
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockRepository.save(any(Modification.class))).thenReturn(mockModif);

        Modification savedMod = mockService.saveContactInformation(request);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModif, savedMod);

    }


    @Test
    public void saveMedicalHistoryTest(){
        List<MedicalHistory.Illness> listIllness = new ArrayList<>();
        listIllness.add(illness1);
        listIllness.add(illness2);

        MedicalHistory history = new MedicalHistory(2L, "sick", "pending",
                listIllness, doctor1);

        MedicalHistoryRequest historyRequest = new MedicalHistoryRequest();
        historyRequest.setHealthInsuranceNumber(healthInsuranceNumber);
        historyRequest.setMedicalHistory(history);

        Modification mockModification = new ModificationBuilder()
                .addHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber())
                .addTimestamp(LocalDateTime.now())
                .addModificationType(historyRequest.getMedicalHistory().getType())
                .addHistory(historyRequest.getMedicalHistory())
                .addStatus(Modification.Status.UPDATE)
                .build();

        when(mockRepository.save(any(Modification.class))).thenReturn(mockModification);

        Modification saveMod = mockService.saveMedicalHistory(historyRequest);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModification,saveMod);
    }

    @Test
    public void saveMedicalVisit(){
        List<MedicalVisit.Diagnosis> listDiag = new ArrayList<>();
        String hospital = "General Hospital";

        listDiag.add(diag1);
        listDiag.add(diag2);

        MedicalVisit visit = new MedicalVisit(3L,hospital,doctor2,date3,listDiag,
                    "Summary", "Notes");

        MedicalVisitRequest visitRequest = new MedicalVisitRequest(healthInsuranceNumber,visit);

        Modification mockModification = new Modification();

        mockModification.setHealthInsuranceNumber(visitRequest.getHealthInsuranceNumber());
        mockModification.setTimestamp(LocalDateTime.now());
        mockModification.setType(visitRequest.getMedicalVisit().getType());
        mockModification.setMedicalVisit(visitRequest.getMedicalVisit());
        mockModification.setStatus(Modification.Status.UPDATE);
        when(mockRepository.save(any(Modification.class))).thenReturn(mockModification);

        Modification saveMod = mockService.saveMedicalVisit(visitRequest);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModification,saveMod);




    }


    @Test
    public void cancelModification_singleModification_Test(){
        List<MedicalVisit.Diagnosis> listDiag = new ArrayList<>();
        listDiag.add(diag1);
        listDiag.add(diag2);

        CancelModificationRequest cancelRequest = new CancelModificationRequest();
        cancelRequest.setHealthInsuranceNumber(healthInsuranceNumber2);
        cancelRequest.setType(ModificationType.MEDICAL_VISIT);

        //Create a MedicalVisit object for testing
        MedicalVisit testVisit = new MedicalVisit(2L, "General Hospital",
                doctor2, date2,listDiag, "Summary", "Note" );

        Modification sample = new ModificationBuilder()
                .addHealthInsuranceNumber(healthInsuranceNumber2)
                .addTimestamp(LocalDateTime.now())
                .addVisit(testVisit)
                .addModificationType(testVisit.getType())
                .addStatus(Modification.Status.UPDATE)
                .build();

        //Stub the repository method to return the cancelled object we expect to find
        when(mockRepository.findTopByHealthInsuranceNumberAndTypeAndStatusNotOrderByTimestampDesc(
                cancelRequest.getHealthInsuranceNumber(),
                cancelRequest.getType(),
                Modification.Status.CANCEL))
                .thenReturn(Optional.of(sample));

        //Call the cancel method
        Optional<Modification> cancelledModification = mockService.cancelLastModification(cancelRequest);

        //Check how many times the repository method has been called
        verify(mockRepository, times(1))
                .findTopByHealthInsuranceNumberAndTypeAndStatusNotOrderByTimestampDesc(any(), any(), any());

        assertEquals(Modification.Status.CANCEL, cancelledModification.orElseThrow().getStatus());

    }

}
