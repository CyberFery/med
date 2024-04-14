package uqam.team17.modificationsarchiveservice;

import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
        String address = "405 rue Sainte-Catherine";
        String phoneNumber = "5148943256";
        String email = "johnsmith@gmail.com";
        ContactInformationRequest request = getContactInformationRequest(address,phoneNumber,email);

        Modification mockModif = new Modification();

        mockModif.setHealthInsuranceNumber(request.getHealthInsuranceNumber());
        mockModif.setTimestamp(LocalDateTime.now());
        mockModif.setType(request.getContactInformation().getType());
        mockModif.setContactInformation(request.getContactInformation());
        mockModif.setStatus(Modification.Status.UPDATE);
        when(mockRepository.save(any(Modification.class))).thenReturn(mockModif);

        Modification savedMod = mockService.saveContactInformation(request);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModif, savedMod);

    }


    @Test
    public void saveMedicalHistoryTest(){
        //Date date1 = 2024-01-01;
        Date start = new Date(2023, Calendar.DECEMBER,20);
        Date end = new Date(2024, Calendar.MARCH, 30);

        MedicalHistory.Illness illness1 = new MedicalHistory.Illness("description1", start, end);
        MedicalHistory.Illness illness2 = new MedicalHistory.Illness("description2", start, end);
        MedicalHistoryRequest historyRequest = getMedicalHistoryRequest(illness1,illness2);

        Modification mockModification = new Modification();

        mockModification.setHealthInsuranceNumber(historyRequest.getHealthInsuranceNumber());
        mockModification.setTimestamp(LocalDateTime.now());
        mockModification.setType(historyRequest.getMedicalHistory().getType());
        mockModification.setMedicalHistory(historyRequest.getMedicalHistory());
        mockModification.setStatus(Modification.Status.UPDATE);
        when(mockRepository.save(any(Modification.class))).thenReturn(mockModification);

        Modification saveMod = mockService.saveMedicalHistory(historyRequest);

        verify(mockRepository,times(1)).save(any(Modification.class));

        assertEquals(mockModification,saveMod);
    }

    @Test
    public void saveMedicalVisit(){
        MedicalVisit.Diagnosis diag1 = new MedicalVisit.Diagnosis("Heart Murmur", "medication");
        MedicalVisit.Diagnosis diag2 = new MedicalVisit.Diagnosis("lack of sleep", "sleep medication");
        List<MedicalVisit.Diagnosis> listDiag = new ArrayList<>();
        Date visitDate = new Date(2023,Calendar.NOVEMBER,20);
        String hospital = "General Hospital";

        listDiag.add(diag1);
        listDiag.add(diag2);

        MedicalVisit visit = new MedicalVisit(3L,hospital,doctor2,visitDate,listDiag,
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












    private ContactInformationRequest getContactInformationRequest(String address, String phoneNumber, String email ) {
        List<ContactInformation.ResidentialAddress> addressList1 = new ArrayList<>(){{
            new ContactInformation.ResidentialAddress(address);}};
        List<ContactInformation.PhoneNumber> phoneList = new ArrayList<>(){{
            new ContactInformation.PhoneNumber(phoneNumber);
        }};
        List<ContactInformation.EmailAddress> emailList = new ArrayList<>(){{
            new ContactInformation.EmailAddress(email);
        }};

        ContactInformation contact = new ContactInformation(1L,
                addressList1, phoneList,emailList);

        ContactInformationRequest request = new ContactInformationRequest();
        request.setHealthInsuranceNumber(healthInsuranceNumber);
        request.setContactInformation(contact);
        return request;
    }

    private MedicalHistoryRequest getMedicalHistoryRequest(MedicalHistory.Illness ill1, MedicalHistory.Illness ill2){
        List<MedicalHistory.Illness> listIllness = new ArrayList<>();
        listIllness.add(ill1);
        listIllness.add(ill2);

        MedicalHistory history = new MedicalHistory(2L, "sick", "pending",
                                                    listIllness, doctor1);

        MedicalHistoryRequest request = new MedicalHistoryRequest();
        request.setHealthInsuranceNumber(healthInsuranceNumber);
        request.setMedicalHistory(history);

        return request;
    }











}
