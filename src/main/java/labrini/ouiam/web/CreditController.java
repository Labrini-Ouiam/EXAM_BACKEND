package labrini.ouiam.web;

import labrini.ouiam.dtos.*;
import labrini.ouiam.enums.CreditStatut;
import labrini.ouiam.services.ICreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<CreditDTO> demanderCredit(@RequestBody CreditDTO creditDTO) {
        CreditDTO createdCredit = creditService.demanderCredit(creditDTO);
        return ResponseEntity.ok(createdCredit);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClient(@PathVariable Long clientId) {
        List<CreditDTO> credits = creditService.getCreditsByClient(clientId);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/{creditId}")
    public ResponseEntity<CreditDTO> getCreditDetails(@PathVariable Long creditId) {
        CreditDTO credit = creditService.getCreditDetails(creditId);
        return ResponseEntity.ok(credit);
    }

    @PutMapping("/{creditId}/traiter")
    public ResponseEntity<Void> traiterCredit(
            @PathVariable Long creditId,
            @RequestParam CreditStatut statut) {
        creditService.traiterCredit(creditId, statut);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatut(@PathVariable CreditStatut statut) {
        List<CreditDTO> credits = creditService.getCreditsByStatut(statut);
        return ResponseEntity.ok(credits);
    }
}