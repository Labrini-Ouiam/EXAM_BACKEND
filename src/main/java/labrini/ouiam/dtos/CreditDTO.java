package labrini.ouiam.dtos;

import labrini.ouiam.enums.CreditStatut;
import lombok.Data;
import java.time.LocalDate;

@Data
public abstract class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private CreditStatut statut;
    private LocalDate dateAcceptation;
    private Long clientId;
}
