package labrini.ouiam.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PERSONNEL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditPersonnel extends Credit {
    private String motif;
}


