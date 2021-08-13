package dmdev.lesson28.model;

import java.io.Serializable;
import java.time.Period;

public class User extends Person implements Serializable, Comparable<User> {

    private String name;

    @MinAge(age = 21)
    private int age;

    public User(int age, String name, int id) {
        super(id);
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(User o) {
        return 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "} " + super.toString();
    }


}
