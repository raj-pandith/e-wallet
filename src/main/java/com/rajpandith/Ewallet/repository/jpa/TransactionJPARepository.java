package com.rajpandith.Ewallet.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rajpandith.Ewallet.dto.TransactionDto;
import com.rajpandith.Ewallet.enitity.TransactionEntity;

@Repository
public interface TransactionJPARepository extends JpaRepository<TransactionEntity, Long> {

        @Query(value = "select t.amount as amount,t.sender_wallet_id as senderWalletId,t.receiver_wallet_id as receiverWalletId,t.message as message,t.date as transDate from transaction_table  t \n"
                        + //
                        "where sender_wallet_id=:userid or receiver_wallet_id=:userid \n" + //
                        "order by date;", nativeQuery = true)

        Page<TransactionDto> findAllTransactionByUser(
                        @Param("userid") Long userid,
                        Pageable pageable);

}
