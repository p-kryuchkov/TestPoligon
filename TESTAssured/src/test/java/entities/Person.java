package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

@Entity
public class Person {
    @Id
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column (name = "second_name")
    private String secondName;
    private int age;
    @Column(name = "sex")
    private Boolean isMale;
    @Transient
    private String sex;
    @Column(columnDefinition = "numeric(19,2)")
    private Float money;
    @Column(name = "house_id")
    private Long houseId;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean isMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        this.isMale = male;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person(String firstName, String secondName, int age, String sex, Float money) {
        if (sex.equals("MALE")) this.isMale = true;
        if (sex.equals("FEMALE")) this.isMale = false;
        else fail("Некорректно передан пол");
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    public Person(String firstName, String secondName, int age, Boolean isMale, Float money) {
        if (isMale.equals(true)) this.sex = "MALE";
        if (isMale.equals(false)) this.sex = "FEMALE";
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.isMale = isMale;
        this.money = money;
    }

    public boolean equalsWithotId(Person person) {

        if (firstName.equals(person.getFirstName()) &&
                secondName.equals(person.getSecondName()) &&
                age == person.getAge() &&
                isMale.equals(person.isMale) &&
                money.equals(person.getMoney())) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", sex='" + sex + '\'' +
                ", money=" + money +
                ", houseId=" + houseId +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName) &&
               // Objects.equals(isMale, person.isMale) &&
                Objects.equals(money, person.money) &&
                Objects.equals(houseId, person.houseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, age, isMale, sex, money, houseId);
    }
}
