package labrini.ouiam.Entities;

import jakarta.persistence.*;
import labrini.ouiam.enums.CreditStatut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "credit_type")

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDemande;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;

    @Enumerated(EnumType.STRING)
    private CreditStatut statut;

    private LocalDate dateAcceptation;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;
}