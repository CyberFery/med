package uqam.team17.modificationsarchiveservice;


import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.repository.ModificationArchiveRepository;
import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDateTime;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;



@ExtendWith(MockitoExtension.class)
public class ModificationServiceTest {

    private final List<ContactInformation.ResidentialAddress> addressList1 = new ArrayList<>(){{
        new ContactInformation.ResidentialAddress("405 rue Sainte-Catherine");}};
    private final List<ContactInformation.PhoneNumber> phoneList = new ArrayList<>(){{
        new ContactInformation.PhoneNumber("5148943256");
    }};
    private final List<ContactInformation.EmailAddress> emailList = new ArrayList<>(){{
        new ContactInformation.EmailAddress("johnsmith@gmail.com");
    }};

    private final ContactInformation contact = new ContactInformation(1L,
                                                addressList1, phoneList,emailList);

    private Modification contactModification = new Modification(1L,"ABCD123456789",
            LocalDateTime.now(),ModificationType.CONTACT_INFORMATION, contact, Modification.Status.UPDATE);

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
        ContactInformationRequest request = new ContactInformationRequest();
        request.setHealthInsuranceNumber("ABCD123456789");
        request.setContactInformation(contact);

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





}
