package comkata.bankaccount.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message){
        super(message);
    }
}
