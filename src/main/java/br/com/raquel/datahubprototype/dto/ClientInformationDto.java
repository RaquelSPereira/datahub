package br.com.raquel.datahubprototype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInformationDto {
    @NotBlank
    private String nameClient;
    @NotBlank
    @Size ( max = 11)
    private String cpfClient;
}
