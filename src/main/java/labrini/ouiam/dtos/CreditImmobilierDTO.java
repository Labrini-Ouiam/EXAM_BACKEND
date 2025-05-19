package labrini.ouiam.dtos;

import labrini.ouiam.enums.TypeBien;
import lombok.Data;

@Data
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien;
}
