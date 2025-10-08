package com.rajpandith.Ewallet.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rajpandith.Ewallet.dto.MakeTransaction;
import com.rajpandith.Ewallet.dto.TransactionDto;
import com.rajpandith.Ewallet.enitity.TransactionEntity;
import com.rajpandith.Ewallet.enitity.UserEntity;
import com.rajpandith.Ewallet.enitity.WalletEntity;
import com.rajpandith.Ewallet.repository.jpa.TransactionJPARepository;
import com.rajpandith.Ewallet.repository.jpa.UserJPARepository;
import com.rajpandith.Ewallet.repository.jpa.WalletJPARepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionJPARepository transactionJPARepository;
    private final WalletJPARepository walletJPARepository;

    private final UserJPARepository userJPARepository;

    @Transactional
    public TransactionEntity makeTransaction(MakeTransaction makeTransaction) throws Exception {
        // find sender and sender wallet

        Optional<UserEntity> sendBox = userJPARepository.findById(makeTransaction.getSenderid());
        if (!sendBox.isPresent()) {
            throw new Exception("sender don't exits");
        }
        UserEntity sender = sendBox.get();
        WalletEntity senderWallet = sender.getWalletEntity();
        if (senderWallet == null) {
            throw new Exception("send not linked  e-wallet");
        }

        // find receiver and it's wallet
        Optional<UserEntity> receiverBox = userJPARepository.findById(makeTransaction.getReceiverid());
        if (!sendBox.isPresent()) {
            throw new Exception("receiver don't exits");
        }
        UserEntity receiver = receiverBox.get();
        WalletEntity receiverWallet = receiver.getWalletEntity();
        if (receiverWallet == null) {
            throw new Exception("Receiver noto linked e-wallet");
        }

        // make tranasaction

        double senderBalance = senderWallet.getAmount();
        double receiverBalance = receiverWallet.getAmount();
        double transferMoney = makeTransaction.getTransferAmount();
        if (senderBalance < transferMoney) {
            throw new Exception("Insufficient Balance !...");
        }

        senderWallet.setAmount(senderBalance - transferMoney);
        receiverWallet.setAmount(receiverBalance + transferMoney);

        // walletJPARepository.save(senderWallet);
        // walletJPARepository.save(receiverWallet);

        // update transaction history

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transferMoney);
        transactionEntity.setMessage(makeTransaction.getMessage());
        transactionEntity.setReceiverWallet(receiverWallet);
        transactionEntity.setSenderWallet(senderWallet);
        transactionJPARepository.save(transactionEntity);

        // to experience transaction feature uncomment below

        // if (true) {
        // throw new RuntimeException();
        // }

        // update transaction history in sender wallet
        senderWallet.getSentTransactions().add(transactionEntity);

        // update transaction history in receiver wallet
        receiverWallet.getReceivedTransactions().add(transactionEntity);

        walletJPARepository.save(senderWallet);
        walletJPARepository.save(receiverWallet);

        return transactionEntity;

    }

    public List<TransactionDto> getAllTransactionByUser(Long userid, int page, int size) throws Exception {

        Optional<UserEntity> user = userJPARepository.findById(userid);
        if (!user.isPresent()) {
            return (List<TransactionDto>) new Exception("user not found exception !..");
        }

        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size);
        List<TransactionDto> list = transactionJPARepository.findAllTransactionByUser(
                userid, pageable).getContent();
        return list;
    }

    public List<TransactionDto> findAllTransaction(int pageNo, int size) {
        Pageable page = PageRequest.of(pageNo, size);
        List<TransactionEntity> list = transactionJPARepository.findAll(page).getContent();

        List<TransactionDto> ans = new ArrayList<>();
        for (TransactionEntity te : list) {
            Timestamp timestamp = Timestamp.valueOf(te.getDate());
            ans.add(new TransactionDto(te.getAmount(), te.getSenderWallet().getId(), te.getReceiverWallet().getId(),
                    te.getMessage(), timestamp));
        }
        return ans;
    }

}
