package br.com.raquel.datahubprototype.repositories;

import br.com.raquel.datahubprototype.model.ClientInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface ClientInformationRepository extends JpaRepository<ClientInformation, Long> {

    @Query(
            value = "select count(*)>0 from client where cpf_client = lower(:cpfClient)",
            nativeQuery = true)

    Boolean existsCpfClient (String cpfClient);
}
