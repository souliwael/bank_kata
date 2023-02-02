package comkata.bankaccount.service;

import comkata.bankaccount.exceptions.AccountNotFoundException;
import comkata.bankaccount.exceptions.InvalidAmountException;
import comkata.bankaccount.model.dto.TransactionRequest;
import comkata.bankaccount.model.entity.Account;
import comkata.bankaccount.model.entity.Transaction;

import java.util.List;

public interface AccountService {
    Account deposit(TransactionRequest transaction) throws InvalidAmountException, AccountNotFoundException;

    Account withdraw(TransactionRequest transaction) throws InvalidAmountException, AccountNotFoundException;

    List<Transaction> getStatements(Long id) throws AccountNotFoundException;
}
