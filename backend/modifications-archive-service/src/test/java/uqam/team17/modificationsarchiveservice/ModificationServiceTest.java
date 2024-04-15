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



}
