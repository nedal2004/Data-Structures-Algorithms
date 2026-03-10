// إذا كان الملف خارج المجلدات لا تضع package، أو ضعه في models
public class InsufficientFundsException extends Exception { 
    public InsufficientFundsException(String message) {
        super(message);
    }
}