package crud;

import connectionProvider.ConnectionProviderFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DbCreator;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by insomniac on 9/9/16.
 */
public class CarServiceTest {

    CarService cs;

    @BeforeClass
    public static void createDB(){
        DbCreator db = new DbCreator();
    }

    @Test
    public void testSave(){
        cs = new CarService();
        cs.insert("Seat", "Leon", "9999");
        ResultSet rs = cs.select("MODEL", "Leon");
        try {
            while (rs.next()) {
                assertEquals(rs.getString("MODEL"), "Leon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        cs = new CarService();
        cs.update(0, "LICENSE_PLATE", "AS3223IS");
        ResultSet rs = cs.select("MODEL", "Leon");
        try {
            while (rs.next()) {
                assertEquals(rs.getString("LICENSE_PLATE"), "AS3223IS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteByField(){
        cs = new CarService();
        cs.delete("MODEL", "Leon");
        ResultSet rs = cs.select("MODEL", "Leon");
        try {
            assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteById(){
        cs = new CarService();
        ResultSet rs = cs.select("MODEL", "Leon");
        int id = 0;
        try {
            while (rs.next()){
                id = rs.getInt("CAR_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cs.delete(id);
        try {
            assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
