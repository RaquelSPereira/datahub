package br.com.raquel.datahubprototype.services;

import br.com.raquel.datahubprototype.Exceptions.UnauthorizedException;
import br.com.raquel.datahubprototype.dto.ClientInformationDto;
import br.com.raquel.datahubprototype.model.ClientInformation;
import br.com.raquel.datahubprototype.repositories.ClientInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ClientInformationService {

    @Autowired
    final ClientInformationRepository clientInformationRepository;
    @Transactional
    public ClientInformation save(ClientInformationDto clientInformationDto){
        var clientInformationModel = new ClientInformation();
        BeanUtils.copyProperties(clientInformationDto, clientInformationModel );
        this.verifyIfCpfExists(clientInformationModel.getCpfClient());
        return clientInformationRepository.save(clientInformationModel);
    }

    private void verifyIfCpfExists(String cpfClient){
        if (existsCpfClient(cpfClient)) {
            throw new UnauthorizedException("CPF já existe no banco de dados");
        }
    }
    public String invertedInformationName(ClientInformation clientInformation){
        final String invertInformation = clientInformation.getNameClient();
        StringBuilder stringBuilder   = new StringBuilder(invertInformation);
        return stringBuilder.reverse().toString();
    }
    public Boolean existsCpfClient(String cpf){
      return this.clientInformationRepository.existsCpfClient(cpf);
    }


}
