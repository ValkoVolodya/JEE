package utils;


import connectionProvider.ConnectionProvider;
import connectionProvider.ConnectionProviderFactory;
import connectionProvider.ConnectionProviderFromUrl;
import exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCreator {


    public DbCreator() {
        ConnectionProviderFactory cpf =
                new ConnectionProviderFactory();

        createDb(cpf.get());
    }

    private void createDb(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String createSQL = "DROP TABLE IF EXISTS CARS; CREATE TABLE CARS("
                    + "CAR_ID SERIAL, "
                    + "MANUFACTURER VARCHAR(20) NOT NULL, "
                    + "MODEL VARCHAR(20) NOT NULL, "
                    + "LICENSE_PLATE VARCHAR(8) NOT NULL, "
                    + "PRIMARY KEY (CAR_ID) "
                    + ");";
            statement.execute(createSQL);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
