package com.china.optional;

/**
 * 深度NULL判断
 * */
public class NullPointExceptionTest {
    public static void main(String[] args) {

        Person person = buildPerson();

        String insuranceName;
        String insuranceName2;
        insuranceName = getInsuranceNameByPerson(person);
        insuranceName2 = getInsuranceNameByPersonDefaultValue(person);

        System.out.println(insuranceName);
        System.out.println(insuranceName2);
    }

    private static String getInsuranceNameByPersonDefaultValue(Person person) {
        final String defaultValue = "";
        if (person == null) {
            return defaultValue;
        }
        Car car = person.getCar();
        if (car == null) {
            return defaultValue;
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return defaultValue;
        }
        return insurance.getName();
    }

    private static Person buildPerson() {
        Person person = new Person();
        person.setName("Zhang");
        Car car = new Car();
        car.setName("BMW5");
        person.setCar(car);
        Insurance insurance = new Insurance();
        insurance.setName("CA");
        car.setInsurance(insurance);
        return person;
    }

    private static String getInsuranceNameByPerson(Person person) {
        if (null == person) {
            return "";
        }else{
            Car car = person.getCar();
            if (car == null) {
                return "";
            }else{
                return car.getInsurance().getName();
            }
        }
    }
}
