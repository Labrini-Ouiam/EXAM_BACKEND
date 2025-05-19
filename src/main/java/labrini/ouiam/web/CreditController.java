//package labrini.ouiam.web;
//
//import labrini.ouiam.dtos.*;
//import labrini.ouiam.enums.CreditStatut;
//import labrini.ouiam.services.ICreditService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/credits")
//@CrossOrigin("*")
//public class CreditController {
//
//    private final ICreditService creditService;
//
//    public CreditController(ICreditService creditService) {
//        this.creditService = creditService;
//    }
//
//    @PostMapping
//    public ResponseEntity<CreditDTO> demanderCredit(@RequestBody CreditDTO creditDTO) {
//        try {
//            CreditDTO createdCredit = creditService.demanderCredit(creditDTO);
//            return new ResponseEntity<>(createdCredit, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/client/{clientId}")
//    public ResponseEntity<List<CreditDTO>> getCreditsByClient(@PathVariable Long clientId) {
//        try {
//            List<CreditDTO> credits = creditService.getCreditsByClient(clientId);
//            return new ResponseEntity<>(credits, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/{creditId}")
//    public ResponseEntity<CreditDTO> getCreditDetails(@PathVariable Long creditId) {
//        try {
//            CreditDTO credit = creditService.getCreditDetails(creditId);
//            return new ResponseEntity<>(credit, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/{creditId}/traiter")
//    public ResponseEntity<Void> traiterCredit(
//            @PathVariable Long creditId,
//            @RequestParam CreditStatut statut) {
//        try {
//            creditService.traiterCredit(creditId, statut);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/statut/{statut}")
//    public ResponseEntity<List<CreditDTO>> getCreditsByStatut(@PathVariable String statut) {
//        try {
//            CreditStatut creditStatut = CreditStatut.valueOf(statut.toUpperCase());
//            List<CreditDTO> credits = creditService.getCreditsByStatut(creditStatut);
//            return new ResponseEntity<>(credits, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
//}

package labrini.ouiam.web;

import labrini.ouiam.dtos.*;
import labrini.ouiam.enums.CreditStatut;
import labrini.ouiam.services.ICreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        try {
            CreditDTO createdCredit = creditService.demanderCredit(creditDTO);
            return new ResponseEntity<>(createdCredit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<CreditDTO>> getClientCredits(@PathVariable("id") Long clientId) {
        try {
            List<CreditDTO> credits = creditService.getCreditsByClient(clientId);
            return new ResponseEntity<>(credits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCredit(@PathVariable("id") Long creditId) {
        try {
            CreditDTO credit = creditService.getCreditDetails(creditId);
            return new ResponseEntity<>(credit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateCreditStatus(
            @PathVariable("id") Long creditId,
            @RequestParam("statut") CreditStatut statut) {
        try {
            creditService.traiterCredit(creditId, statut);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/{statut}")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatus(@PathVariable("statut") CreditStatut statut) {
        try {
            List<CreditDTO> credits = creditService.getCreditsByStatut(statut);
            return new ResponseEntity<>(credits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}