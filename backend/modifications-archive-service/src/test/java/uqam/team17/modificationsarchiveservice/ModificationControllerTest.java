package uqam.team17.modificationsarchiveservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uqam.team17.modificationsarchiveservice.controller.ModificationArchiveController;

import uqam.team17.modificationsarchiveservice.service.ModificationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
public class ModificationControllerTest {

    private final String healthInsuranceNumber = "TREM123453422";

    @Mock
    private ModificationService mockService;

    @InjectMocks
    private ModificationArchiveController mockController;







}
