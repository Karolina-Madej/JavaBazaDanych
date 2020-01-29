package sample;

import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ArrayList.class})



public class Employee {

    int ID;
    String name;
    String surname;
    int age;
    String username;
    String password;
    //@XmlElement(name = "ID")
    @XmlTransient
    List<Employee> employeesList = new ArrayList<Employee>();


    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void insertEmployee(Employee employee) {

        employeesList.add(employee);
    }
    public Employee(){

    }
    public Employee(int ID, String name, String surname, int age, String username, String password){

        this.ID=ID;
        this.name=name;
        this.surname=surname;
        this.age=age;
        this.username=username;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", username=" + username +
                ", password=" + password +
                '}';
    }
}
