package HomeProjects.RESTapiExample.Model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private LocalDateTime regDate;



    //TODO reg date, annotations, array of photos
    public Student(){}

    public Student(String firstName, String lastName, int age, LocalDateTime regDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.regDate = regDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
