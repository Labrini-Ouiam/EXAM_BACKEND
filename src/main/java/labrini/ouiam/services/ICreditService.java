package labrini.ouiam.services;

import labrini.ouiam.dtos.*;
import java.util.List;
import labrini.ouiam.enums.CreditStatut;

public interface ICreditService {
    CreditDTO demanderCredit(CreditDTO creditDTO);
    List<CreditDTO> getCreditsByClient(Long clientId);
    CreditDTO getCreditDetails(Long creditId);
    void traiterCredit(Long creditId, CreditStatut statut);
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
    RemboursementDTO effectuerRemboursement(RemboursementDTO remboursementDTO);
    List<CreditDTO> getCreditsByStatut(CreditStatut statut);
}
