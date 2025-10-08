package com.rajpandith.Ewallet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajpandith.Ewallet.dto.MakeTransaction;
import com.rajpandith.Ewallet.dto.RequestAddUserWithWallet;
import com.rajpandith.Ewallet.enitity.TransactionEntity;
import com.rajpandith.Ewallet.enitity.UserEntity;
import com.rajpandith.Ewallet.enitity.WalletEntity;
import com.rajpandith.Ewallet.services.TransactionService;
import com.rajpandith.Ewallet.services.UserService;
import com.rajpandith.Ewallet.services.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userService.createUser(userEntity);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addwallet")
    public ResponseEntity<?> addWallet(@RequestBody RequestAddUserWithWallet requestUserWithWallet) {
        try {
            WalletEntity walletEntity = walletService.initializeWallet(requestUserWithWallet);
            return new ResponseEntity<>(walletEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> makeTransaction(@RequestBody MakeTransaction makeTransaction) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionService.makeTransaction(makeTransaction);
            return new ResponseEntity<>(transactionEntity, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
