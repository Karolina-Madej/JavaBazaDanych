package sample;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Empoyees")
@XmlAccessorType(XmlAccessType.FIELD)


public class Employees {


    @XmlElement(name = "Employee")
    private List<Employee> employeesList = new ArrayList<Employee>();

    public Employees() {
    }

    public void insertEmployee(Employee employee) {

        employeesList.add(employee);
    }

    public void displayEmployees() {
        System.out.println("WYŚWITLAM LISTE: ");
        for (Employee employee  : employeesList) {
            System.out.println(employee.getName() + ", " + employee.getSurname() + " " );
        }
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}
