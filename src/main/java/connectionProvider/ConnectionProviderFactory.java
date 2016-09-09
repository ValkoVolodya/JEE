package connectionProvider;

import java.sql.Connection;

/**
 * Created by insomniac on 9/9/16.
 */
public class ConnectionProviderFactory implements ConnectionProvider {

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String name = "postgres";
    private String pass = "postgres";

    ConnectionProviderSingleton cps;


    public ConnectionProviderFactory() {
        ConnectionProvider cp =
                new ConnectionProviderFromUrl(url, name, pass);

        cps = new ConnectionProviderSingleton(cp);
    }

    public Connection get() {
        return cps.get();
    }

    public void close() {
        cps.close();
    }
}
