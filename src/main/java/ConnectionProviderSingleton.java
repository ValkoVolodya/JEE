import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provides Singleton Connection
 */
public class ConnectionProviderSingleton implements ConnectionProvider {

    private Connection connection;

    private AtomicBoolean ensureSafeSet = new AtomicBoolean(false);

    private final ConnectionProvider cp;

    public ConnectionProviderSingleton(ConnectionProvider cp) {
        this.cp = cp;
    }

    public Connection get() {
        if (ensureSafeSet.compareAndSet(false, true)){
            connection = cp.get();
        }
        return connection;
    }

    public void close() {
        cp.close();
    }
}
