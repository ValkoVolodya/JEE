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

    public void insert(){
        try{
            Statement statement = cpf.get().createStatement();
            String insert = "INSERT INTO CARS" +
                    "(MANUFACTURER, MODEL, LICENSE_PLATE)" +
                    "VALUES ('Alfa Romeo', '159', 'AL 1590 FA')";
            statement.executeUpdate(insert);
        } catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public ResultSet select(String criterium, String value){
        try{
            Statement statement = cpf.get().createStatement();
            String select = "SELECT ALL FROM CARS WHERE " +
                    criterium + " = " + value;
            return statement.executeQuery(select);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void delete(int id){
        try{
            Statement statement = cpf.get().createStatement();
            String delete = "DELETE CARS WHERE CAR_ID = " + id;
            statement.execute(delete);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void delete(String criterium, String value){
        try{
            Statement statement = cpf.get().createStatement();
            String delete = "DELETE CARS WHERE " + criterium + " = " + value;
            statement.execute(delete);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }

    public void update(int id, String criterium, String newValue){
        try{
            Statement statement = cpf.get().createStatement();
            String update = "UPDATE CARS SET " + criterium + " = " +
                    newValue + " WHERE car_id = " + id;
            statement.execute(update);
        }catch (SQLException e){
            throw new SQLRuntimeException(e);
        }
    }
}
