package labrini.ouiam.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}
