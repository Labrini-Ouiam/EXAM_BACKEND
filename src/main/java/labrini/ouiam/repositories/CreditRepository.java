package labrini.ouiam.repositories;

import labrini.ouiam.Entities.Credit;
import labrini.ouiam.enums.CreditStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatut(CreditStatut statut);
}
