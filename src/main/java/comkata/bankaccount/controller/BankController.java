package comkata.bankaccount.controller;

import comkata.bankaccount.exceptions.AccountNotFoundException;
import comkata.bankaccount.exceptions.InvalidAmountException;
import comkata.bankaccount.model.dto.TransactionRequest;
import comkata.bankaccount.model.entity.Account;
import comkata.bankaccount.model.entity.Transaction;
import comkata.bankaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
public class BankController {

    private AccountService accountService;

    @Autowired
    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * @param transactionRequest
     * @return
     * @throws InvalidAmountException
     * @throws AccountNotFoundException
     */
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody TransactionRequest transactionRequest) throws InvalidAmountException, AccountNotFoundException {
        return new ResponseEntity<>(accountService.deposit(transactionRequest), HttpStatus.ACCEPTED);
    }

    /**
     * @param transactionRequest
     * @return
     * @throws InvalidAmountException
     * @throws AccountNotFoundException
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody TransactionRequest transactionRequest) throws InvalidAmountException, AccountNotFoundException {
        return new ResponseEntity<>(accountService.withdraw(transactionRequest), HttpStatus.ACCEPTED);
    }

    /**
     * @param id
     * @return
     * @throws AccountNotFoundException
     */
    @GetMapping("/statement/{id}")
    public ResponseEntity<List<Transaction>> statements(@PathVariable Long id) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getStatements(id), HttpStatus.OK);
    }
}
