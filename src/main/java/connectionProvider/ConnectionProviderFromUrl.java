package connectionProvider;

import exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides Connection from Url
 */
public class ConnectionProviderFromUrl implements ConnectionProvider {

    private final String url;

    private final String user;

    private final String password;

    /**
     * Constructor
     * @param url
     *              the JDBC url
     */
    public ConnectionProviderFromUrl(String url){
        this(url, null, null);
    }

    /**
     * Constructor
     * @param url
     *              the JDBC url
     * @param user
     *              login username
     * @param password
     *              login password
     */
    public ConnectionProviderFromUrl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection get() {
        try {
            if (user != null || password != null){
                return DriverManager.getConnection(url, user, password);
            }
            else{
                return DriverManager.getConnection(url);
            }
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void close() {
        //just wait for your time...
    }
}
