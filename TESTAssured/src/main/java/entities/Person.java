package entities;

public class Person {
    /*

    "id": 1,
    "firstName": "Vasiliy",
    "secondName": "Rubenstein",
    "age": 42,
    "sex": "MALE",
    "money": 1000000.00
     */
    private int id;
    private String firstName;
    private String secondName;
    private int age;
    private String sex;   // Наверное енам
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Person(int id, String firstName, String secondName, int age, String sex, double money) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }
}
