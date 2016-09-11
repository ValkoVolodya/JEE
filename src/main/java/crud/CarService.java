package crud;

import connectionProvider.ConnectionProviderFactory;
import exceptions.SQLRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarService {

    private ConnectionProviderFactory cpf;

    public CarService() {
        cpf = new ConnectionProviderFactory();
    }

    public void insert(String man, String model, String code){
        try{
            Statement statement = cpf.get().createStatement();
            String insert = String.format(
                    "INSERT INTO CARS (MANUFACTURER, MODEL, LICENSE_PLATE) " +
                            "VALUES ('%s', '%s', '%s');",
                    man, model, code
            );
            statement.executeUpdate(insert);
        } catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public ResultSet select(String criterium, String value){
        try{
            Statement statement = cpf.get().createStatement();
            String select = String.format(
                    "SELECT * FROM CARS WHERE %s = '%s'",
                    criterium, value
            );
            return statement.executeQuery(select);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void delete(int id){
        try{
            Statement statement = cpf.get().createStatement();
            String delete = String.format(
                    "DELETE FROM CARS WHERE CAR_ID = %d;", id
            );
            statement.execute(delete);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void delete(String criterium, String value){
        try{
            Statement statement = cpf.get().createStatement();
            String delete = String.format(
                    "DELETE FROM CARS WHERE %s = '%s'",
                    criterium, value
            );
            statement.execute(delete);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void update(int id, String criterium, String newValue){
        try{
            Statement statement = cpf.get().createStatement();
            String update = String.format(
                    "UPDATE CARS SET %s = '%s' WHERE car_id = %d",
                    criterium, newValue, id
            );
            statement.execute(update);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }
}
