package com.danilo.padraodao.DAO;

/**
 *
 * @author Danilo Rocha
 */
public class ErroBancoException extends Exception{

    public ErroBancoException() {
        super("Erro na base de dados");
    }

    public ErroBancoException(String message) {
        super(message);
    }

    public ErroBancoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroBancoException(Throwable cause) {
        super(cause);
    }

    public ErroBancoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
