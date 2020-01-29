package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XML {
    private JAXBContext jaxbContext;
    private String filePath;
    private List<Employee> employeeList;

    public XML(String filePath) {
        try {
            this.jaxbContext = JAXBContext.newInstance(Employees.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.filePath = filePath;
    }

    public void writeToFile() {

        try {
            Marshaller marshaller = this.jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println(employeeList.size());
            marshaller.marshal(employeeList, new File(this.filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void readFromFile() {

        try {
            Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            unmarshaller.unmarshal(new File("test.xml"));
            System.out.println("UN:  " + unmarshaller.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public List<Employee> getBookList() {
        return employeeList;
    }

    public void setBookList(List<Employee> bookList) {
        this.employeeList = employeeList;
    }
}
