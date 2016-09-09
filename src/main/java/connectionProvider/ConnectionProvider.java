package connectionProvider;

import java.sql.Connection;


/**
 * Provide jdbc Connections.
 */
public interface ConnectionProvider {
    /**
     * @return a new Connection to database
     */
    Connection get();


    /**
     * Closes a Connection.
     * Should be idempotent.
     */
    void close();
}
