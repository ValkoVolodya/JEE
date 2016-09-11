package crud;

import entity.Car;
import org.junit.*;
import utils.DbCreator;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for crud methods
 */
public class CarServiceTest {

    private CarService cs;
    private static Car car;
    private static Car carForUpdate;

    @BeforeClass
    public static void createDB(){
        DbCreator db = new DbCreator();
        car = new Car.Builder("Alfa Romeo", "159")
                .license_plate("AL1999FA")
                .build();
        carForUpdate = new Car.Builder("Alfa Romeo", "4C")
                .license_plate("AL1999FA")
                .build();
    }

    @Before
    public void createEntityInDB(){
        cs = new CarService();
        cs.insert(car);
    }

    @After
    public void deleteEntityInDB(){
        cs = new CarService();
        cs.delete(car);
    }


    @Test
    public void testSave(){
        cs = new CarService();
        List<Car> result = cs.select("MODEL", "159");
        assertEquals(result.get(0), car);
        cs.delete(car);
    }

    @Test
    public void testUpdate(){
        cs = new CarService();
        List<Car> result = cs.select("MODEL", "159");
        Car carWithId = result.get(0);
        Car carFU = new Car.Builder(carForUpdate.getManufacturer(),
                carForUpdate.getModel())
                .car_id(carWithId.getCar_id())
                .license_plate(carForUpdate.getLicense_plate())
                .build();
        cs.update(carFU);
        result = cs.select("MODEL", "4C");
        assertEquals(result.get(0), carFU);
        cs.delete(carFU);
    }


    @Test
    public void testDeleteByObject(){
        cs = new CarService();
        cs.delete(car);
        List<Car> list = cs.select("MODEL", "159");
        assertTrue(list.size() == 0);
    }

    @Test
    public void testDeleteById(){
        cs = new CarService();
        List<Car> result = cs.select("MODEL", "159");
        cs.delete(result.get(0).getCar_id());
        result = cs.select("MODEL", "159");
        assertTrue(result.size() == 0);
    }
}
