package br.com.raquel.datahubprototype.controllers;

import br.com.raquel.datahubprototype.dto.ClientInformationDto;
import br.com.raquel.datahubprototype.model.ClientInformation;
import br.com.raquel.datahubprototype.services.ClientInformationService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.Duration;
import java.time.Instant;

@Controller
@RequiredArgsConstructor
@RequestMapping("/datahub")
public class ClientInformationController {

    @Autowired
    private final ClientInformationService clientInformationService;

    @PostMapping("/information-client")
    public ResponseEntity<Object> clientInformationSaveAndReturn(@RequestBody @Valid ClientInformationDto clientInformationDto) {
        Instant start = Instant.now();
        final ClientInformation clientInformationSaved = clientInformationService.save(clientInformationDto);

        final String invertedName = clientInformationService.invertedInformationName(clientInformationSaved);


        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        String timeExecution = String.valueOf(duration.toMillis());

        return ResponseEntity.ok(String.format("O nome do cliente invertido é: %s e o tempo de execução: %s ms", invertedName, timeExecution));


    }

}
