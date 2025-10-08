package com.rajpandith.Ewallet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajpandith.Ewallet.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{userid}")
    public ResponseEntity<?> getAllTransactionByUser(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @PathVariable Long userid) {
        try {
            return new ResponseEntity<>(transactionService.getAllTransactionByUser(userid, page,
                    size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransaction(@RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(transactionService.findAllTransaction(pageNo, size), HttpStatus.OK);
    }

}
