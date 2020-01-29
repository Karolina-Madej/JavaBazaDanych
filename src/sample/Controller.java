package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//@XmlSeeAlso({Employee.class, ArrayList.class})

public class Controller implements Initializable {

    public LoginModel loginModel = new LoginModel();
    private Statement statement = null;
    private Connection conn=null;


    @FXML
    private Button Button1;
    @FXML
    private Label isConnected;

    @FXML
    private TextArea textAreaEmployee;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;


    @FXML
    private TableView<Employee> dbTableView;

    @FXML
    private TableColumn<Employee, Integer> idTableView;

    @FXML
    private TableColumn<Employee, String> nameTableView;

    @FXML
    private TableColumn<Employee, String> surnameTableView;

    @FXML
    private TableColumn<Employee, Integer> agetableView;

    @FXML
    private TableColumn<Employee, String> usernametableView;
    @FXML
    private TextField passwordChangeTextField;

    @FXML
    private TableColumn<Employee, String> passwordTableView;
    private ObservableList<Employee> employeeListObservable = FXCollections.observableArrayList();
    Employee employee;
    List<Employee> employeeList = new ArrayList<>();

    int findInList(int ID){
        for(int i = 0 ;i < employeeListObservable.size();i++){
            if(employeeListObservable.get(i).getID()==ID)
                return i;
        }
        return -1;
    }
    @FXML
    void saveButtonClicked(MouseEvent event) {
        JAXBContext jaxbContext = null;

        try {
            jaxbContext = JAXBContext.newInstance(sample.Employee.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employee, new File("test6.xml"));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void upDateClicked(MouseEvent event) throws SQLException {

        Employee employee = dbTableView.getSelectionModel().getSelectedItem();

        employee.setPassword(passwordChangeTextField.getText());
        int index =findInList(employee.getID());
        employeeListObservable.get(index).setPassword(passwordChangeTextField.getText());

        String sql = "UPDATE employee SET password = '"+ passwordChangeTextField.getText()+"' WHERE ID = "+employee.getID()+"; ";

        Connection connection = SqlConnector.Connector();

        statement = connection.createStatement();

        statement.executeUpdate(sql);
        statement.close();

        //dbTableView.getItems().clear();
       // dbTableView.getItems().addAll(employeeList);
        dbTableView.refresh();

    }

    @FXML
    public void insertIntoClicked(MouseEvent event) throws SQLException {

        String sql = "INSERT INTO employee(name, surname, age, username, password) VALUES(?,?,?,?,?)";
        try (
                Connection connection = SqlConnector.Connector();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, nameTextField.getText());
            pstmt.setString(2, surnameTextField.getText());
            pstmt.setString(3, agetableView.getText());
            pstmt.setString(4, usernametableView.getText());
            pstmt.setString(5, passwordTextField.getText());
            pstmt.executeUpdate();


            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        nameTextField.clear();
        surnameTextField.clear();
        ageTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();

        dbTableView.getItems().clear();
        dbTableView.getItems().addAll(employeeList);
        dbTableView.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = SqlConnector.Connector();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from employee");

            while (resultSet.next()){
                employee =new Employee(resultSet.getInt("ID"), resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getInt("age"),resultSet.getString("username"),resultSet.getString("password"));

                employeeListObservable.add(employee);
                employeeList.add(employee);
                employee.insertEmployee(employee);
            }
        }
        catch (SQLException e) {

            System.out.println("blad");


        }        idTableView.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameTableView.setCellValueFactory(new PropertyValueFactory<>("name"));

        surnameTableView.setCellValueFactory(new PropertyValueFactory<>("surname"));

        agetableView.setCellValueFactory(new PropertyValueFactory<>("age"));

        usernametableView.setCellValueFactory(new PropertyValueFactory<>("username"));

        passwordTableView.setCellValueFactory(new PropertyValueFactory<>("password"));

        dbTableView.setEditable(true);
        dbTableView.getSelectionModel().cellSelectionEnabledProperty().set(true);


        dbTableView.setItems(employeeListObservable);




    }
}

