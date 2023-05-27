package br.com.raquel.datahubprototype.Unit.controllers;
import br.com.raquel.datahubprototype.DatahubprototypeApplication;
import br.com.raquel.datahubprototype.controllers.ClientInformationController;
import br.com.raquel.datahubprototype.dto.ClientInformationDto;
import br.com.raquel.datahubprototype.model.ClientInformation;
import br.com.raquel.datahubprototype.services.ClientInformationService;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DatahubprototypeApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientInformationControllerTest {
    @MockBean
    private ClientInformationService clientInformationService;

    private MockMvc mockMvc;
    @Autowired private WebApplicationContext context;

    @BeforeAll
    void init() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    @DisplayName("Should return bad request error when necessary field was not provided")
    public void ShouldReturnError() throws Exception {
        // Mock client information
        ClientInformationDto clientInformationDto = new ClientInformationDto();
        clientInformationDto.setNameClient("John Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/datahub/information-client")
                        .contentType("application/json")
                        .content("{\"name\": \"John Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("message", is("{cpfClient=must not be blank, nameClient=must not be blank}")));
    }

    @Test
    public void testProcessIsOk() throws Exception {
        // Mock client information
        ClientInformationDto clientInformationDto = new ClientInformationDto();
        clientInformationDto.setNameClient("John Doe");
        clientInformationDto.setCpfClient("99999999999");

        ClientInformation savedClientInformation = new ClientInformation();
        savedClientInformation.setId(1L);
        savedClientInformation.setNameClient("John Doe");

        String invertedName = "eoD nhoJ";


        when(clientInformationService.save(clientInformationDto)).thenReturn(savedClientInformation);
        when(clientInformationService.invertedInformationName(savedClientInformation)).thenReturn(invertedName);


        mockMvc.perform(MockMvcRequestBuilders.post("/datahub/information-client")
                        .contentType("application/json")
                        .content("{\"nameClient\": \"John Doe\", \"cpfClient\":\"99999999999\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
