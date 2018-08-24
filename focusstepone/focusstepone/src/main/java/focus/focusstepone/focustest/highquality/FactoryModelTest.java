package focus.focusstepone.focustest.highquality;

/**
 * focus Create in 10:17 2018/8/23
 */
public class FactoryModelTest {
    public static void main(String[] args) {
        Car fordCar = CarFactory.createCar(FordCar.class);

        Car enumFordCar = EnumCarFactory.FORD_CAR.create();
    }
}

interface Car{};

class FordCar implements Car{};
class BuickCar implements Car{}

//传统工厂
class CarFactory {
    public static Car createCar(Class<? extends Car> car) {
        try {
            return (Car)car.newInstance();
        } catch (Exception e) {}
        return null;
    }
}

//枚举类工厂
enum EnumCarFactory {
    //定义能生产的产品类型
    FORD_CAR,BUICK_CAR;

    public Car create() {
        switch (this) {
            case FORD_CAR:
                return new FordCar();
            case BUICK_CAR:
                return new BuickCar();
            default:
                return null;
        }
    }
}


