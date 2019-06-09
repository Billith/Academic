package controller;

public class ValidateDataException extends Exception {

    public ValidateDataException() {
        super("Wrong data exception");
    }

    public ValidateDataException(String msg) {
        super(msg);
    }

}
