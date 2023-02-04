package com.pjatk.s26245bank2.service;

import com.pjatk.s26245bank2.exception.AccountNotFoundException;
import com.pjatk.s26245bank2.model.BankAccount;
import com.pjatk.s26245bank2.model.Transaction;
import com.pjatk.s26245bank2.model.TransactionStatus;
import com.pjatk.s26245bank2.repository.BankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {
    private final BankAccountRepository bankAccountRepository = Mockito.mock(BankAccountRepository.class);
    private final BankAccountService bankAccountService = new BankAccountService(bankAccountRepository);
    private BankAccount firstBankAccount, secondBankAccount, thirdBankAccount;
    private List<BankAccount> bankAccounts;


    @BeforeEach
    void setUpAccounts() {
        firstBankAccount = new BankAccount(1, 1000.00, "First", "Client");
        secondBankAccount = new BankAccount(2, 2000.00, "Second", "Client");
        thirdBankAccount = new BankAccount(3, 3000.00, "Third", "Client");
        bankAccounts = List.of(firstBankAccount,secondBankAccount,thirdBankAccount);
    }

    @Test
    void shouldFindAccountById() {
        //Given
        BankAccount testBankAccount;
        int id = 1;
        //when
        Mockito.when(bankAccountRepository.findByID(1)).thenReturn(Optional.ofNullable(firstBankAccount));
        testBankAccount = bankAccountService.findById(1);
        //then
        Assertions.assertEquals(firstBankAccount, testBankAccount);
    }

    @Test
    void shouldReturnAllAccounts() {
        //when
        Mockito.when(bankAccountRepository.getAllBankAccounts()).thenReturn(bankAccounts);
        List<BankAccount> query = bankAccountService.getAllAccounts();

        //then
        Assertions.assertEquals(query, bankAccounts);
    }

    @Test
    public void shouldNotFindAccountByNotExistingId() {
        //given
        AccountNotFoundException notFoundException = assertThrows(AccountNotFoundException.class, () -> bankAccountService.findById(9999));

        //then
        assertEquals("Account don't exist", notFoundException.getMessage());
    }

    @Test
    public void successfullyRegister() {
        BankAccount bankAccount = new BankAccount(1, 100, "Kowalski", "Adam");

        assertDoesNotThrow(() -> bankAccountService.registerAccount(bankAccount));
    }

    @Test
    void shouldWithdraw500_1500ShouldStayAtTheAccount() {
        //given
        int id = 2;
        double amount = 500;
        Transaction withdrawTransaction;

        //when
        Mockito.when(bankAccountRepository.findByID(id)).thenReturn(Optional.ofNullable(firstBankAccount));
        withdrawTransaction = bankAccountService.withdraw(id, amount);

        //then
        Assertions.assertEquals(TransactionStatus.ACCEPTED, withdrawTransaction.getTransactionStatus());
        Assertions.assertEquals(1500, withdrawTransaction.getNewBalance());
    }

}