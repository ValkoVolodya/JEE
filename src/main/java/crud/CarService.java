package crud;

import connectionProvider.ConnectionProviderFactory;
import entity.Car;
import exceptions.SQLRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private ConnectionProviderFactory cpf;

    /**
     * Initializes ConnectionProviderFactory
     */
    public CarService() {
        cpf = new ConnectionProviderFactory();
    }

    /**
     * Insert entity in db
     *
     * @param car - entity to be deleted
     */
    public void insert(Car car) {
        try {
            Statement statement = cpf.get().createStatement();
            String insert = String.format(
                    "INSERT INTO CARS (MANUFACTURER, MODEL, LICENSE_PLATE) " +
                            "VALUES ('%s', '%s', '%s')",
                    car.getManufacturer(),
                    car.getModel(),
                    car.getLicense_plate()
            );
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * Select entities by given criterium
     * @param criterium - field for search
     * @param value - value of field
     * @return List of results
     */
    public List<Car> select(String criterium, String value) {
        try {
            Statement statement = cpf.get().createStatement();
            String select = String.format(
                    "SELECT * FROM CARS WHERE %s = '%s'",
                    criterium, value
            );
            ResultSet rs = statement.executeQuery(select);
            List<Car> list = new ArrayList<Car>();
            while (rs.next()) {
                list.add(new Car.Builder(
                                rs.getString("MANUFACTURER"),
                                rs.getString("MODEL")
                        )
                                .car_id(rs.getInt("CAR_ID"))
                                .license_plate(rs.getString("LICENSE_PLATE"))
                                .build()
                );
            }
            return list;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * Delete entity from db
     *
     * @param car - entity for deleting
     */
    public void delete(Car car) {
        try {
            Statement statement = cpf.get().createStatement();
            String delete = String.format(
                    "DELETE FROM CARS WHERE " +
                            "MANUFACTURER = '%s' AND " +
                            "MODEL = '%s' AND LICENSE_PLATE = '%s'",
                    car.getManufacturer(),
                    car.getModel(),
                    car.getLicense_plate()
            );
            statement.execute(delete);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * Delete row wih given id
     *
     * @param id - id of row to be deleted
     */
    public void delete(int id) {
        try {
            Statement statement = cpf.get().createStatement();
            String delete = String.format(
                    "DELETE FROM CARS WHERE CAR_ID = '%d'",
                    id
            );
            statement.execute(delete);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * Update row with same id.
     *
     * @param car entity with updated fields
     */
    public void update(Car car) {
        try {
            Statement statement = cpf.get().createStatement();
            String update = String.format(
                    "UPDATE CARS SET " +
                            "MANUFACTURER = '%s' , " +
                            "MODEL = '%s' , LICENSE_PLATE = '%s' " +
                            "WHERE CAR_ID = %d",
                    car.getManufacturer(),
                    car.getModel(),
                    car.getLicense_plate(),
                    car.getCar_id()
            );
            statement.execute(update);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
