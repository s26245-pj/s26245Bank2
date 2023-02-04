package com.pjatk.s26245bank2.repository;

import com.pjatk.s26245bank2.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BankAccountRepository {

    private List<BankAccount> bankAccountList = new ArrayList<>();
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountList;
    }
    public void addAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public Optional<BankAccount> findByID(int id) {
        return bankAccountList.stream().filter(bankAccount -> id == bankAccount.getId()).findFirst();
    }

    public void removeAllAccounts() {
        bankAccountList = new ArrayList<>();
    }

}
