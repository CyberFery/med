package uqam.team17.medicalrecordsservice;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import uqam.team17.medicalrecordsservice.entity.*;
import uqam.team17.medicalrecordsservice.ExceptionHandler.*;
import uqam.team17.medicalrecordsservice.repository.MedicalRecordsRepository;
import uqam.team17.medicalrecordsservice.service.MedicalRecordsService;

import java.util.ArrayList;
import java.util.Date;
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
        medicalVisit.add(new MedicalVisit(1L, "Hopital thérèse",doctor, new Date(2024, 9, 9), diagnosis, "summary", "notes" ));

        List<MedicalHistory.Illness> illness = new ArrayList<>();
        illness.add(new MedicalHistory.Illness("cancer", new Date(2023, 1, 1), new Date(2024, 3, 9)));

        List<MedicalHistory> medicalHistory = new ArrayList<>();
        medicalHistory.add(new MedicalHistory(1L, "cancer", "treatement cancer", illness, doctor));

        return new MedicalRecord(1L, patient, medicalVisit, medicalHistory);
    }

    @NotNull
    private static Patient getPatient(String healthInsuranceNumber) {
        List<Patient.Person> knownParentList = new ArrayList<>();
        knownParentList.add(new Patient.Person("Jeanne", "Johnson"));

        Patient.ContactInformation contactInformation = getContactInformation();

        Patient patient = new Patient(healthInsuranceNumber, "David", "Johnson",
                Patient.Genre.MALE, new Date(1970, 9, 9), "cancer",
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
        String healthInsuranceNumber = "ABCD123456789";
        MedicalRecord medicalRecord = new MedicalRecord();
        when(medicalRecordsRepository.save(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord savedMedicalRecord = medicalRecordsService.saveMedicalRecord(medicalRecord);

        assertEquals(medicalRecord, savedMedicalRecord);
    }
}
