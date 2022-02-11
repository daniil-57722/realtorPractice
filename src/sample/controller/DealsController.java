package sample.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.Constants;
import sample.DBHandler;
import sample.Realtor;
import sample.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DealsController {
Realtor realtor;
User user;
private DBHandler dbHandler;
private ObservableList<ObservableList> data;

    @FXML
    private TableView<ObservableList> dealTable;

    @FXML
    private Button exitBtn;

    @FXML
    private Label nameField;

    public void initData(Realtor realtor) {
        this.realtor = realtor;
        nameField.setText(realtor.getName());
        String querry = "select id, realty, price, offerer, needer, realtor from "+ Constants.DEAL_TABLE+ " where realtorid = " + realtor.getId();
        try{
            fill(querry, dealTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void initData(User user) {
        this.user = user;
        nameField.setText(user.getName());
        String querry = "select id, realty, price, offerer, needer, realtor from "+ Constants.DEAL_TABLE+
                " where needer = '" + user.getName() + " " + user.getPhone() + "' OR offerer = '" + user.getName() +" "+ user.getPhone() + "'";
        System.out.println(querry);
        try{
            fill(querry, dealTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fill(String querry, TableView<ObservableList> table) throws SQLException {
        table.getColumns().clear();
        data = FXCollections.observableArrayList();
        dbHandler = new DBHandler();
        ResultSet resultSet = dbHandler.querry(querry);
        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
            col.setPrefWidth(599 / resultSet.getMetaData().getColumnCount());
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            table.getColumns().addAll(col);
        }
        while (resultSet.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.add(resultSet.getString(i));
            }
            data.add(row);
        }
        table.setItems(data);
    }
}
