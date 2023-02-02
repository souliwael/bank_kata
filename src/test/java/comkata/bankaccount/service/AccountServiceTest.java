package comkata.bankaccount.service;

import comkata.bankaccount.exceptions.AccountNotFoundException;
import comkata.bankaccount.exceptions.InvalidAmountException;
import comkata.bankaccount.model.dto.TransactionRequest;
import comkata.bankaccount.model.entity.Account;
import comkata.bankaccount.model.enums.OperationType;
import comkata.bankaccount.model.entity.Transaction;
import comkata.bankaccount.repository.AccountRepository;
import comkata.bankaccount.repository.TransactionRepository;
import comkata.bankaccount.service.impl.AccountServiceImpl;
import comkata.bankaccount.utils.TestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testDeposit() throws InvalidAmountException, AccountNotFoundException {
        // Arrange
        TransactionRequest transactionRequest =
                TestDataBuilder.buildTransactionRequest(1L, BigDecimal.valueOf(100));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Account(1L, new ArrayList<>(), BigDecimal.valueOf(100))));

        // Act
        accountService.deposit(transactionRequest);

        // Assert
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testWithdrawal() throws InvalidAmountException, AccountNotFoundException {
        // Arrange
        TransactionRequest transactionRequest =
                TestDataBuilder.buildTransactionRequest(1L, BigDecimal.valueOf(100));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Account(1L, new ArrayList<>(), BigDecimal.valueOf(100))));

        // Act
        accountService.withdraw(transactionRequest);

        // Assert
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testGetStatement() throws AccountNotFoundException {
        // Arrange
        List<Transaction> transactions =
                Arrays.asList(new Transaction(LocalDateTime.now(), BigDecimal.valueOf(100), BigDecimal.valueOf(100), OperationType.DEPOSIT,null));
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Account(1L, transactions, BigDecimal.valueOf(100))));

        // Act
        List<Transaction> result = accountService.getStatements(1L);

        // Assert
        assertEquals("Check first transaction", transactions.get(0), result.get(0));
    }
}