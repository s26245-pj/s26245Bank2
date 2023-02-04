package com.pjatk.s26245bank2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount {
    private int id;
    private double balance;
    private String holderName;
    private String holderSurname;
}
