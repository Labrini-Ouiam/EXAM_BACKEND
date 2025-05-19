package labrini.ouiam.mappers;
import labrini.ouiam.Entities.Remboursement;
import labrini.ouiam.dtos.RemboursementDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    public RemboursementDTO toDto(Remboursement remboursement) {
        if (remboursement == null) {
            return null;
        }

        RemboursementDTO remboursementDTO = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, remboursementDTO);

        // Propriétés spécifiques
        if (remboursement.getCredit() != null) {
            remboursementDTO.setCreditId(remboursement.getCredit().getId());
        }

        return remboursementDTO;
    }

    public Remboursement toEntity(RemboursementDTO remboursementDTO) {
        if (remboursementDTO == null) {
            return null;
        }

        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(remboursementDTO, remboursement);

        return remboursement;
    }
}
