package labrini.ouiam.mappers;
import labrini.ouiam.Entities.Credit;
import labrini.ouiam.Entities.CreditImmobilier;
import labrini.ouiam.Entities.CreditPersonnel;
import labrini.ouiam.Entities.CreditProfessionnel;
import labrini.ouiam.dtos.CreditDTO;
import labrini.ouiam.dtos.CreditImmobilierDTO;
import labrini.ouiam.dtos.CreditPersonnelDTO;
import labrini.ouiam.dtos.CreditProfessionnelDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    private final ClientMapper clientMapper;
    private final RemboursementMapper remboursementMapper;

    public CreditMapper(ClientMapper clientMapper, RemboursementMapper remboursementMapper) {
        this.clientMapper = clientMapper;
        this.remboursementMapper = remboursementMapper;
    }

    public CreditDTO toDto(Credit credit) {
        if (credit == null) {
            return null;
        }

        CreditDTO creditDTO;

        // Gestion de l'héritage
        if (credit instanceof CreditPersonnel) {
            CreditPersonnelDTO personnelDTO = new CreditPersonnelDTO();
            BeanUtils.copyProperties(credit, personnelDTO);
            personnelDTO.setMotif(((CreditPersonnel) credit).getMotif());
            creditDTO = personnelDTO;
        }
        else if (credit instanceof CreditImmobilier) {
            CreditImmobilierDTO immobilierDTO = new CreditImmobilierDTO();
            BeanUtils.copyProperties(credit, immobilierDTO);
            immobilierDTO.setTypeBien(((CreditImmobilier) credit).getTypeBien());
            creditDTO = immobilierDTO;
        }
        else if (credit instanceof CreditProfessionnel) {
            CreditProfessionnelDTO professionnelDTO = new CreditProfessionnelDTO();
            BeanUtils.copyProperties(credit, professionnelDTO);
            professionnelDTO.setMotif(((CreditProfessionnel) credit).getMotif());
            professionnelDTO.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
            creditDTO = professionnelDTO;
        }
        else {
            throw new IllegalArgumentException("Type de crédit non supporté: " + credit.getClass().getName());
        }

        if (credit.getClient() != null) {
            creditDTO.setClientId(credit.getClient().getId());
        }

        return creditDTO;
    }

    public Credit toEntity(CreditDTO creditDTO) {
        if (creditDTO == null) {
            return null;
        }

        Credit credit;

        // Gestion de l'héritage
        if (creditDTO instanceof CreditPersonnelDTO) {
            CreditPersonnel personnel = new CreditPersonnel();
            BeanUtils.copyProperties(creditDTO, personnel);
            personnel.setMotif(((CreditPersonnelDTO) creditDTO).getMotif());
            credit = personnel;
        }
        else if (creditDTO instanceof CreditImmobilierDTO) {
            CreditImmobilier immobilier = new CreditImmobilier();
            BeanUtils.copyProperties(creditDTO, immobilier);
            immobilier.setTypeBien(((CreditImmobilierDTO) creditDTO).getTypeBien());
            credit = immobilier;
        }
        else if (creditDTO instanceof CreditProfessionnelDTO) {
            CreditProfessionnel professionnel = new CreditProfessionnel();
            BeanUtils.copyProperties(creditDTO, professionnel);
            professionnel.setMotif(((CreditProfessionnelDTO) creditDTO).getMotif());
            professionnel.setRaisonSociale(((CreditProfessionnelDTO) creditDTO).getRaisonSociale());
            credit = professionnel;
        }
        else {
            throw new IllegalArgumentException("Type de DTO crédit non supporté: " + creditDTO.getClass().getName());
        }

        return credit;
    }
}