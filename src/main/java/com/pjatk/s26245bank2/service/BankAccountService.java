package com.pjatk.s26245bank2.service;

import com.pjatk.s26245bank2.exception.AccountNotFoundException;
import com.pjatk.s26245bank2.exception.NotEnoughFundException;
import com.pjatk.s26245bank2.model.BankAccount;
import com.pjatk.s26245bank2.model.Transaction;
import com.pjatk.s26245bank2.model.TransactionStatus;
import com.pjatk.s26245bank2.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void registerAccount(BankAccount bankAccount){
        bankAccountRepository.addAccount(bankAccount);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.getAllBankAccounts();
    }

    public BankAccount findById(int id) {
        Optional<BankAccount> accountId = bankAccountRepository.findByID(id);
        if (accountId.isPresent()) {
            return accountId.get();
        } else {
            throw new AccountNotFoundException("Account don't exist");
        }
    }

    public Transaction withdraw(int id, double amount) {
        BankAccount bankAccount = findById(id);
        try {
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            return new Transaction(TransactionStatus.ACCEPTED, bankAccount.getBalance());
        } catch (AccountNotFoundException e) {
            return new Transaction(TransactionStatus.DECLINED, 0.00);
        } catch (NotEnoughFundException e) {
            return  new Transaction(TransactionStatus.DECLINED, bankAccount.getBalance());
        }
    }

    public Transaction deposit(int id, double amount) {
        BankAccount bankAccount = findById(id);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return new Transaction(TransactionStatus.ACCEPTED, bankAccount.getBalance());
    }

    public void deleteAllAccounts() {
        bankAccountRepository.removeAllAccounts();
    }
}
