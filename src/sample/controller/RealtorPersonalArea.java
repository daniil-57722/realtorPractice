package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.*;
import sample.objects.Deal;
import java.io.IOException;
import java.sql.SQLException;

public class RealtorPersonalArea {
    private Realtor realtor;
    private String needQuerry, offerQuerry;
    private DBHandler dbHandler;
    @FXML private TableView<ObservableList> NeedTable;
    @FXML private TableView<ObservableList> OfferTable;
    @FXML private Button createDealBtn;
    @FXML private Label nameField;
    @FXML private Button refreshBtn;
    @FXML private Button exitBtn;
    @FXML private Button showBtn;

    public void initData(Realtor realtor){
        this.realtor = realtor;
        nameField.setText(realtor.getName());
        needQuerry = "SELECT id, client, phone, realty, address, minprice, maxprice FROM " + Constants.NEED_TABLE +
                " WHERE " + Constants.REALTORID + " = " + realtor.getId();
        offerQuerry = "SELECT id, client, phone, realty, address, price FROM " + Constants.OFFER_TABLE +
                " WHERE " + Constants.REALTORID + " = " + realtor.getId();
        System.out.println(needQuerry);
        try {
            Main.fill(needQuerry, NeedTable);
            Main.fill(offerQuerry, OfferTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initialize(){
        createDealBtn.setOnAction(event ->{
            ObservableList selectedNeed = NeedTable.getSelectionModel().getSelectedItem();
            NeedTable.getItems().remove(selectedNeed);
            ObservableList selectedOffer = OfferTable.getSelectionModel().getSelectedItem();
            OfferTable.getItems().remove(selectedOffer);
            Deal deal = new Deal(selectedNeed.get(0).toString(),
                    selectedOffer.get(0).toString(),
                    selectedNeed.get(3).toString(),
                    selectedOffer.get(4).toString(),
                    selectedOffer.get(1).toString() + " "+ selectedOffer.get(2),
                    selectedNeed.get(1).toString() +" "+ selectedNeed.get(2),
                    realtor.getId(), realtor.getName() +" "+ realtor.getLastname());
            dbHandler = new DBHandler();
            try {
                dbHandler.addDeal(deal);
                dbHandler.deleteRow("needs", selectedNeed.get(0).toString());
                dbHandler.deleteRow("offers", selectedOffer.get(0).toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        showBtn.setOnAction(event ->{
            try {
                Swap.openDealsWindow("view/deals.fxml", realtor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitBtn.setOnAction(event ->{
            exitBtn.getScene().getWindow().hide();
            try {
                Swap.openAnotherWindow("view/authorization.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        refreshBtn.setOnAction(event ->{
            try {
                Main.fill(needQuerry, NeedTable);
                Main.fill(offerQuerry, OfferTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}