package matteofurgani.u5w3d5.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super("L'id n." + id + " non è stato trovato!");
    }
}
