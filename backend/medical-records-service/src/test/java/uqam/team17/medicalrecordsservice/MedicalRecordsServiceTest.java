package uqam.team17.medicalrecordsservice;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import uqam.team17.medicalrecordsservice.entity.*;
import uqam.team17.medicalrecordsservice.exception.*;
import uqam.team17.medicalrecordsservice.repository.MedicalRecordsRepository;
import uqam.team17.medicalrecordsservice.service.MedicalRecordsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsServiceTest {
    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;

    @InjectMocks
    private MedicalRecordsService medicalRecordsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(MedicalRecordsServiceTest.class);
    }

    private MedicalRecord setupMedicalRecord(String healthInsuranceNumber){
        Patient patient = getPatient(healthInsuranceNumber);
        Doctor doctor = new Doctor(1l, "Jean", "Doe", "Cancérologue");

        List<MedicalVisit.Diagnosis> diagnosis = new ArrayList<>();
        diagnosis.add(new MedicalVisit.Diagnosis("fever, headache", "Advil"));

        List<MedicalVisit> medicalVisit = new ArrayList<>();
        medicalVisit.add(new MedicalVisit(1L, "Hopital thérèse",doctor, LocalDate.parse("2023-01-08"), diagnosis, "summary", "notes" ));

        List<MedicalHistory.Illness> illness = new ArrayList<>();
        illness.add(new MedicalHistory.Illness("cancer", LocalDate.parse("2023-01-08"), LocalDate.parse("2024-01-08")));

        List<MedicalHistory> medicalHistory = new ArrayList<>();
        medicalHistory.add(new MedicalHistory(1L, "cancer", "treatment cancer", illness, doctor));

        return new MedicalRecord(1L, patient, medicalVisit, medicalHistory);
    }

    @NotNull
    private static Patient getPatient(String healthInsuranceNumber) {
        List<Patient.Person> knownParentList = new ArrayList<>();
        knownParentList.add(new Patient.Person("Jeanne", "Johnson"));

        Patient.ContactInformation contactInformation = getContactInformation();

        Patient patient = new Patient(healthInsuranceNumber, "David", "Johnson",
                Patient.Genre.MALE, LocalDate.parse("1980-01-08"), "cancer",
                false, false);

        patient.setKnownParentList(knownParentList);
        patient.setContactInformation(contactInformation);
        return patient;
    }

    @NotNull
    private static Patient.ContactInformation getContactInformation() {
        List<Patient.ResidentialAddress> residentialAddress = new ArrayList<>();
        residentialAddress.add(new Patient.ResidentialAddress("444 rue poirier montreal"));

        List<Patient.PhoneNumber> phoneNumber = new ArrayList<>();
        phoneNumber.add(new Patient.PhoneNumber("514-544-6182"));

        List<Patient.EmailAddress> emailAddress = new ArrayList<>();
        emailAddress.add(new Patient.EmailAddress("john@gmail.com"));

        return new Patient.ContactInformation(residentialAddress, phoneNumber, emailAddress);
    }

    @Test
    public void testGetMedicalRecord() {
        String healthInsuranceNumber = "ABCD123456789";

        MedicalRecord expectedMedicalRecord = setupMedicalRecord(healthInsuranceNumber);

        when(medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber)).thenReturn(expectedMedicalRecord);

        MedicalRecord actualMedicalRecord = medicalRecordsService.getMedicalRecord(healthInsuranceNumber);

        assertEquals(expectedMedicalRecord, actualMedicalRecord);
    }

    @Test
    public void testUpdateContactInformation() throws MedicalRecordsException {
        String healthInsuranceNumber = "ABCD123456789";
        Patient.ContactInformation contactInformation = new Patient.ContactInformation();
        Patient patient = new Patient();
        patient.setContactInformation(contactInformation);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(patient);
        when(medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber)).thenReturn(medicalRecord);

        Patient.ContactInformation updatedContactInfo = medicalRecordsService.updateContactInformation(healthInsuranceNumber, contactInformation);

        assertEquals(contactInformation, updatedContactInfo);
        verify(medicalRecordsRepository, times(1)).save(medicalRecord);
    }

    @Test
    public void testUpdateMedicalVisit() throws MedicalRecordsException {
        String healthInsuranceNumber = "ABCD123456789";
        MedicalVisit medicalVisit = new MedicalVisit();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalVisit> medicalVisitList = new ArrayList<>();
        medicalRecord.setMedicalVisitList(medicalVisitList);
        when(medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber)).thenReturn(medicalRecord);

        MedicalVisit updatedMedicalVisit = medicalRecordsService.updateMedicalVisit(healthInsuranceNumber, medicalVisit);

        assertEquals(medicalVisit, updatedMedicalVisit);
        assertEquals(1, medicalRecord.getMedicalVisitList().size());
        verify(medicalRecordsRepository, times(1)).save(medicalRecord);
    }

    @Test
    public void testUpdateMedicalHistory() throws MedicalRecordsException {
        String healthInsuranceNumber = "ABCD123456789";
        MedicalHistory medicalHistory = new MedicalHistory();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        medicalRecord.setMedicalHistoryList(medicalHistoryList);
        when(medicalRecordsRepository.findByPatientHealthInsuranceNumber(healthInsuranceNumber)).thenReturn(medicalRecord);

        MedicalHistory updatedMedicalHistory = medicalRecordsService.updateMedicalHistory(healthInsuranceNumber, medicalHistory);

        assertEquals(medicalHistory, updatedMedicalHistory);
        assertEquals(1, medicalRecord.getMedicalHistoryList().size());
        verify(medicalRecordsRepository, times(1)).save(medicalRecord);
    }

    @Test
    public void testSaveMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        when(medicalRecordsRepository.save(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord savedMedicalRecord = medicalRecordsService.saveMedicalRecord(medicalRecord);

        assertEquals(medicalRecord, savedMedicalRecord);
    }
}
