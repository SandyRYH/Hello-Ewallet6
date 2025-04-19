
package id.co.bsi.hello_spring.repository;

import id.co.bsi.hello_spring.model.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    Page<TransactionModel> findByAccountnum(String accountnum, Pageable pageable);
    Page<TransactionModel> findByAccountnumAndDescriptionContainingIgnoreCase(String accountnum, String keyword, Pageable pageable);
}
