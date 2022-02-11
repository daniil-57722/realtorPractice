package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.*;

import java.io.IOException;
import java.sql.SQLException;

public class Swap {
    static public void openAnotherWindow(String name) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    static public void openPersonalAreaWindow(String name, User user) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        PersonalArea personalArea = loader.getController();
        personalArea.initData(user);
        stage.show();
    }
    static public void openCreateOfferWindow(String name, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        CreateOfferController createOfferController = loader.getController();
        createOfferController.initData(user);
        stage.show();
    }
    static public void openCreateNeedWindow(String name, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        CreateNeedController createNeedController = loader.getController();
        createNeedController.initData(user);
        stage.show();
    }
    static public void openPersonalAreaWindow(String name, Realtor realtor) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        RealtorPersonalArea realtorPersonalArea = loader.getController();
        realtorPersonalArea.initData(realtor);
        stage.show();
    }
    static public void openDealsWindow(String name, Realtor realtor) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        DealsController dealsController = loader.getController();
        dealsController.initData(realtor);
        stage.show();
    }
    static public void openDealsWindow(String name, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        DealsController dealsController = loader.getController();
        dealsController.initData(user);
        stage.show();
    }
}
