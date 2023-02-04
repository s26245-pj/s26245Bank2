package com.pjatk.s26245bank2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private  TransactionStatus transactionStatus;
    private double newBalance;
}
