package com.wefox.payment.repository;

import com.wefox.payment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("update Account acc set acc.lastPaymentDate = ?1 where acc.accountId = ?2")
    void updateLastPaymentDate(Timestamp lastPaymentDate, Long accountId);
}
