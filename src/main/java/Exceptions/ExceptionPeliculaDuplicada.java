package Exceptions;

public class ExceptionPeliculaDuplicada extends RuntimeException {
    public String getCampoDulicado;

    public ExceptionPeliculaDuplicada(String message) {
        super(message);
    }


}
