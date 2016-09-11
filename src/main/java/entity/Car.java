package entity;

/**
 * Entity implementation
 */
public class Car {
    private final int car_id;
    private final String manufacturer;
    private final String model;
    private final String license_plate;

    public static class Builder{
        private final String manufacturer;
        private final String model;

        //Optional
        private String license_plate = "";
        //Moved to optional, for enable insert with autoincrement
        private int car_id;

        public Builder(String manufacturer, String model) {
            this.manufacturer = manufacturer;
            this.model = model;
        }

        public Builder license_plate(String val){
            if (val.length() > 8){
                throw new IllegalArgumentException();
            }
            license_plate = val;
            return this;
        }

        public Builder car_id(int val){
            car_id = val;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }

    private Car(Builder builder){
        car_id = builder.car_id;
        manufacturer = builder.manufacturer;
        model = builder.model;
        license_plate = builder.license_plate;
    }

    public int getCar_id() {
        return car_id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getLicense_plate() {
        return license_plate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return getManufacturer().equals(car.getManufacturer())
                && getModel().equals(car.getModel())
                && (getLicense_plate() != null
                ? getLicense_plate().equals(car.getLicense_plate())
                : car.getLicense_plate() == null);

    }

    @Override
    public int hashCode() {
        int result = getManufacturer().hashCode();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + (getLicense_plate() != null
                ? getLicense_plate().hashCode() : 0);
        return result;
    }
}
