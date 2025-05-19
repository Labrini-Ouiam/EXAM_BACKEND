//package labrini.ouiam.web;
//
//import labrini.ouiam.dtos.RemboursementDTO;
//import labrini.ouiam.services.ICreditService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/remboursements")
//@CrossOrigin("*")
//public class RemboursementController {
//
//    private final ICreditService creditService;
//
//    public RemboursementController(ICreditService creditService) {
//        this.creditService = creditService;
//    }
//
//    @GetMapping("/credit/{creditId}")
//    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCredit(@PathVariable Long creditId) {
//        try {
//            List<RemboursementDTO> remboursements = creditService.getRemboursementsByCredit(creditId);
//            return new ResponseEntity<>(remboursements, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<RemboursementDTO> effectuerRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
//        try {
//            RemboursementDTO createdRemboursement = creditService.effectuerRemboursement(remboursementDTO);
//            return new ResponseEntity<>(createdRemboursement, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
package labrini.ouiam.web;

import labrini.ouiam.dtos.RemboursementDTO;
import labrini.ouiam.services.ICreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementController {

    private final ICreditService creditService;

    public RemboursementController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credit/{id}")
    public ResponseEntity<List<RemboursementDTO>> getCreditRepayments(@PathVariable("id") Long creditId) {
        try {
            List<RemboursementDTO> remboursements = creditService.getRemboursementsByCredit(creditId);
            return new ResponseEntity<>(remboursements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRepayment(@RequestBody RemboursementDTO remboursementDTO) {
        try {
            RemboursementDTO createdRemboursement = creditService.effectuerRemboursement(remboursementDTO);
            return new ResponseEntity<>(createdRemboursement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}