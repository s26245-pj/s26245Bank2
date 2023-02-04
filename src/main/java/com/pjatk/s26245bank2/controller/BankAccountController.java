package com.pjatk.s26245bank2.controller;


import com.pjatk.s26245bank2.model.BankAccount;
import com.pjatk.s26245bank2.model.Transaction;
import com.pjatk.s26245bank2.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public  BankAccount getAccountById(@PathVariable int id) {
        return bankAccountService.findById(id);
    }

    @PostMapping("/create")
    public void register(@RequestBody BankAccount bankAccount) {
        bankAccountService.registerAccount(bankAccount);
    }

    @PutMapping("/accounts/withdraw/{id}")
    public Transaction withdraw(@PathVariable int id, @RequestBody double amount){
        return bankAccountService.withdraw(id, amount);
    }

    @PutMapping("/accounts/deposit/{id}")
    public Transaction deposit(@PathVariable int id, @RequestBody double amount){
        return bankAccountService.deposit(id, amount);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAll() {
        bankAccountService.deleteAllAccounts();

        return ResponseEntity.ok().build();
    }

}
