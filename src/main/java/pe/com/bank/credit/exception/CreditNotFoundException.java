package pe.com.bank.credit.exception;

//test1
//reemplazar
//tesmer
//huu
public class CreditNotFoundException extends RuntimeException{

    private String message;

    public CreditNotFoundException(String message){
        super(message);
        this.message = message;
    }

}
