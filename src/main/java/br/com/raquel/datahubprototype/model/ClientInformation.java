package br.com.raquel.datahubprototype.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "CLIENT")

public class ClientInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_client", nullable = false)
    private String nameClient;
    @Column (name = "cpf_client",  unique = true, nullable = false)
    private String cpfClient;

    @Override
    public String toString(){
        return "Client{" + "Name:"+ this.nameClient+ ", cpf= "+this.cpfClient;
    }

}
