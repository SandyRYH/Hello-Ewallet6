
package id.co.bsi.hello_spring.controller;

import id.co.bsi.hello_spring.model.TransactionModel;
import id.co.bsi.hello_spring.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountnum}")
    public ResponseEntity<Page<TransactionModel>> getTransactions(
            @RequestHeader("token") String token,
            @PathVariable String accountnum,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateTime") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Page<TransactionModel> result = transactionService.getTransactions(token, accountnum, search, page, size, sortBy, direction);
        if (result.isEmpty()) {
            return ResponseEntity.status(401).build(); // unauthorized
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TransactionModel> postTransaction(
            @RequestHeader("token") String token,
            @RequestBody TransactionModel transaction
    ) {
        TransactionModel saved = transactionService.saveTransaction(token, transaction);
        if (saved == null) {
            return ResponseEntity.status(401).build(); // unauthorized or mismatch
        }
        return ResponseEntity.ok(saved);
    }
}
