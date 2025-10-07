package Exceptions;

public class ExceptionUsuarioDuplicado extends RuntimeException {
    public String getCampoDulicado;

    public ExceptionUsuarioDuplicado(String message) {
        super(message);
    }


}
