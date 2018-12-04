package dao;


public class DatabaseException extends Exception {

    public DatabaseException(Exception e) {
        super(e);
    }
    public DatabaseException(String message) {
        super(message);
    }
}
