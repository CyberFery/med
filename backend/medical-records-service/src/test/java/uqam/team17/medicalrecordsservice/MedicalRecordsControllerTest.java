package uqam.team17.medicalrecordsservice;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.springframework.web.reactive.function.client.WebClient;
import uqam.team17.medicalrecordsservice.entity.MedicalHistory;
import uqam.team17.medicalrecordsservice.entity.MedicalRecord;
import uqam.team17.medicalrecordsservice.entity.MedicalVisit;
import uqam.team17.medicalrecordsservice.entity.Patient;
import uqam.team17.medicalrecordsservice.service.MedicalRecordsService;
import uqam.team17.medicalrecordsservice.controller.MedicalRecordsController;
import uqam.team17.medicalrecordsservice.entity.*;
import uqam.team17.medicalrecordsservice.exception.MedicalRecordsException;

import static org.mockito.ArgumentMatchers.any;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordsControllerTest {
    @Mock
    private MedicalRecordsService medicalRecordsService;
    @InjectMocks
    private MedicalRecordsController medicalRecordsController;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

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
    void createMedicalRecord_WithValidData_ReturnsOkResponse() {
        String healthInsuranceNumber = "ABCD123456789";
        MedicalRecord medicalRecord = setupMedicalRecord(healthInsuranceNumber);
        when(medicalRecordsService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);

        ResponseEntity<?> response = medicalRecordsController.createMedicalRecord(medicalRecord);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalRecord, response.getBody());
    }

    @Test
    void createMedicalRecord_WithInvalidData_ReturnsBadRequestResponse() {
        // Invalid medical record with missing data
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(new Patient());
        medicalRecord.setMedicalVisitList(new ArrayList<>());
        medicalRecord.setMedicalHistoryList(new ArrayList<>());

        ResponseEntity<?> response = medicalRecordsController.createMedicalRecord(medicalRecord);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMedicalRecord_WithValidHealthInsuranceNumber_ReturnsOkResponse() {
        String healthInsuranceNumber = "ABCD123456789";
        MedicalRecord medicalRecord = setupMedicalRecord(healthInsuranceNumber);
        when(medicalRecordsService.getMedicalRecord(healthInsuranceNumber)).thenReturn(medicalRecord);

        ResponseEntity<?> response = medicalRecordsController.getMedicalRecord(healthInsuranceNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalRecord, response.getBody());
    }

    @Test
    void getMedicalRecord_WithInvalidHealthInsuranceNumber_ReturnsBadRequestResponse() {
        String healthInsuranceNumber = "ABD1234589";
        ResponseEntity<?> response = medicalRecordsController.getMedicalRecord(healthInsuranceNumber);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMedicalRecord_WithNonExistentMedicalRecord_ReturnsNotFoundResponse() {
        String healthInsuranceNumber = "ABCD123456789";
        when(medicalRecordsService.getMedicalRecord(healthInsuranceNumber)).thenReturn(null);
        ResponseEntity<?> response = medicalRecordsController.getMedicalRecord(healthInsuranceNumber);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
