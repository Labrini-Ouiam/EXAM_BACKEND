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
            creditDTO = new CreditPersonnelDTO();
            BeanUtils.copyProperties(credit, creditDTO);
            ((CreditPersonnelDTO) creditDTO).setMotif(((CreditPersonnel) credit).getMotif());
        }
        else if (credit instanceof CreditImmobilier) {
            creditDTO = new CreditImmobilierDTO();
            BeanUtils.copyProperties(credit, creditDTO);
            ((CreditImmobilierDTO) creditDTO).setTypeBien(((CreditImmobilier) credit).getTypeBien());
        }
        else if (credit instanceof CreditProfessionnel) {
            creditDTO = new CreditProfessionnelDTO();
            BeanUtils.copyProperties(credit, creditDTO);
            ((CreditProfessionnelDTO) creditDTO).setMotif(((CreditProfessionnel) credit).getMotif());
            ((CreditProfessionnelDTO) creditDTO).setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
        }
        else {
            creditDTO = new CreditDTO();
            BeanUtils.copyProperties(credit, creditDTO);
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
            credit = new CreditPersonnel();
            BeanUtils.copyProperties(creditDTO, credit);
            ((CreditPersonnel) credit).setMotif(((CreditPersonnelDTO) creditDTO).getMotif());
        }
        else if (creditDTO instanceof CreditImmobilierDTO) {
            credit = new CreditImmobilier();
            BeanUtils.copyProperties(creditDTO, credit);
            ((CreditImmobilier) credit).setTypeBien(((CreditImmobilierDTO) creditDTO).getTypeBien());
        }
        else if (creditDTO instanceof CreditProfessionnelDTO) {
            credit = new CreditProfessionnel();
            BeanUtils.copyProperties(creditDTO, credit);
            ((CreditProfessionnel) credit).setMotif(((CreditProfessionnelDTO) creditDTO).getMotif());
            ((CreditProfessionnel) credit).setRaisonSociale(((CreditProfessionnelDTO) creditDTO).getRaisonSociale());
        }
        else {
            credit = new Credit();
            BeanUtils.copyProperties(creditDTO, credit);
        }

        return credit;
    }
}