
package id.co.bsi.hello_spring.service;

import id.co.bsi.hello_spring.model.TransactionModel;
import id.co.bsi.hello_spring.model.User;
import id.co.bsi.hello_spring.repository.TransactionRepository;
import id.co.bsi.hello_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<TransactionModel> getTransactions(String token, String accountnum,
                                                  String keyword, int page, int size, String sortBy, String direction) {
        Optional<User> userOpt = userRepository.findByToken(token);
        if (userOpt.isEmpty() || !userOpt.get().getAccountnum().equals(accountnum)) {
            return Page.empty(); // unauthorized or mismatch
        }

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (keyword != null && !keyword.isEmpty()) {
            return transactionRepository.findByAccountnumAndDescriptionContainingIgnoreCase(accountnum, keyword, pageable);
        } else {
            return transactionRepository.findByAccountnum(accountnum, pageable);
        }
    }

    public TransactionModel saveTransaction(String token, TransactionModel transaction) {
        Optional<User> userOpt = userRepository.findByToken(token);
        if (userOpt.isEmpty() || !userOpt.get().getAccountnum().equals(transaction.getAccountnum())) {
            return null;
        }
        return transactionRepository.save(transaction);
    }
}
