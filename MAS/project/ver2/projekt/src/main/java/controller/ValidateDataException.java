package controller;

/**
 * Class provides custom validation exception which should be handled in a visible way for end user
 */
public class ValidateDataException extends Exception {

    /**
     * Default constructor without message
     */
    public ValidateDataException() {
        super("Wrong data exception");
    }

    /**
     * Constructor with custom message, which can be use to display it to the user
     * @param msg the message
     */
    public ValidateDataException(String msg) {
        super(msg);
    }

}
