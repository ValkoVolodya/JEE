package connectionProvider;

import java.sql.Connection;

import configs.Config;

/**
 * Created by insomniac on 9/9/16.
 */
public class ConnectionProviderFactory implements ConnectionProvider {

    ConnectionProviderSingleton cps;

    public ConnectionProviderFactory() {
        ConnectionProvider cp =
                new ConnectionProviderFromUrl(
                        Config.url, Config.name, Config.pass
                );

        cps = new ConnectionProviderSingleton(cp);
    }

    public Connection get() {
        return cps.get();
    }

    public void close() {
        cps.close();
    }
}
