package com.rajpandith.Ewallet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajpandith.Ewallet.dto.AddMoneyDto;
import com.rajpandith.Ewallet.services.WalletService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/checkbalance/{userid}")
    public ResponseEntity<?> checkBalance(@PathVariable Long userid) {
        try {
            return new ResponseEntity<>(walletService.checkBalance(userid), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addmoney")
    public ResponseEntity<?> addMoney(@RequestBody AddMoneyDto addMoneyDto) {
        try {
            return new ResponseEntity<>(walletService.addMoney(addMoneyDto) ? "Successfully added" : "amount not added",
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
