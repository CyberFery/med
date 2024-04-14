package uqam.team17.modificationsarchiveservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uqam.team17.modificationsarchiveservice.controller.ModificationArchiveController;
import uqam.team17.modificationsarchiveservice.model.*;
import uqam.team17.modificationsarchiveservice.service.ModificationService;


@ExtendWith(MockitoExtension.class)
public class ModificationControllerTest {

    @Mock
    private ModificationService mockService;

    @InjectMocks
    private ModificationArchiveController mockController;




}
