package labrini.ouiam.services;

import labrini.ouiam.dtos.ClientDTO;
import java.util.List;

public interface IClientService {
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    boolean existsByEmail(String email);
    ClientDTO getClientByEmail(String email);
}
