package labrini.ouiam.mappers;

import labrini.ouiam.Entities.Client;
import labrini.ouiam.dtos.ClientDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client client) {
        if (client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }
}
