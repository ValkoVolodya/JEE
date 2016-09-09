package exceptions;


import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {

    public SQLRuntimeException(SQLException e){
        super(e);
    }

    public SQLRuntimeException(String message){
        super(message);
    }

}
