package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B.Exceptions;

public class ExceptionPeliculaDuplicada extends RuntimeException {
    public String getCampoDulicado;

    public ExceptionPeliculaDuplicada(String message) {
        super(message);
    }


}
