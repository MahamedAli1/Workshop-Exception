package se.lexicon.exceptions.workshop.exception;

public class DuplicateNameException extends Exception {
    private final String duplicateName;

    public DuplicateNameException(String message, String duplicateName) {
        super(message + ": " + duplicateName);
        this.duplicateName = duplicateName;
    }

    public String getDuplicateName() {
        return duplicateName;
    }
}
