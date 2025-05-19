package labrini.ouiam.services;

import jakarta.transaction.Transactional;
import labrini.ouiam.Entities.*;
import labrini.ouiam.dtos.*;
import labrini.ouiam.enums.CreditStatut;
import labrini.ouiam.mappers.*;
import labrini.ouiam.repositories.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final RemboursementRepository remboursementRepository;
    private final CreditMapper creditMapper;
    private final ClientMapper clientMapper;
    private final RemboursementMapper remboursementMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
                             ClientRepository clientRepository,
                             RemboursementRepository remboursementRepository,
                             CreditMapper creditMapper,
                             ClientMapper clientMapper,
                             RemboursementMapper remboursementMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.remboursementRepository = remboursementRepository;
        this.creditMapper = creditMapper;
        this.clientMapper = clientMapper;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    public CreditDTO demanderCredit(CreditDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setClient(client);
        credit.setStatut(CreditStatut.EN_COURS);
        credit.setDateDemande(LocalDate.now());

        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        return creditRepository.findByClientId(clientId)
                .stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditDetails(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return creditMapper.toDto(credit);
    }

    @Override
    public void traiterCredit(Long creditId, CreditStatut statut) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        credit.setStatut(statut);
        if (statut == CreditStatut.ACCEPTE) {
            credit.setDateAcceptation(LocalDate.now());
        }

        creditRepository.save(credit);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findByCreditId(creditId)
                .stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO effectuerRemboursement(RemboursementDTO remboursementDTO) {
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement.setCredit(credit);
        remboursement.setDate(LocalDate.now());

        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDto(savedRemboursement);
    }

    @Override
    public List<CreditDTO> getCreditsByStatut(CreditStatut statut) {
        return creditRepository.findByStatut(statut)
                .stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }
}
