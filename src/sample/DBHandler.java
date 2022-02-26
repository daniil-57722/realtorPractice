package sample;

import sample.objects.*;

import java.sql.*;

public class DBHandler extends Config {
    Connection dbConnection;
    Statement statement;
    ResultSet result = null;

    //Установка соединения с бд
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    //Метод, поверяющий есть ли такой логин и пароль в бд
    public ResultSet checkLoginAndPass(String login, String password, String table) {
        String request = "SELECT * FROM " + table + " WHERE login = '" + login + "' AND password = '" + password + "'";
        try {
            statement = getDbConnection().createStatement();
            result = statement.executeQuery(request);
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    //Зегистрация пользователя
    public void signUpUser(User user) {
        String request = "INSERT INTO " + Constants.USER_TABLE +
                "(" + Constants.USER_NAME + "," + Constants.USER_PHONE +
                "," + Constants.USER_EMAIL + "," + Constants.USER_LOGIN +
                "," + Constants.USER_PASSWORD + ")VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Создание предложения
    public void addOffer(Offer offer) {
        String request = "INSERT INTO " + Constants.OFFER_TABLE +
                "(" + Constants.CLIENTID + "," + Constants.PHONE +
                "," + Constants.REALTY + "," + Constants.PRICE +
                "," + Constants.REALTORID + ","+ Constants.ADRESS
                + ")VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, offer.getClient());
            preparedStatement.setString(2, offer.getPhone());
            preparedStatement.setString(3, offer.getType());
            preparedStatement.setInt(4, offer.getPrice());
            preparedStatement.setInt(5, offer.getRealtorid());
            preparedStatement.setString(6, offer.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Создание потребности
    public void addNeed(Need need) {
        String request = "INSERT INTO " + Constants.NEED_TABLE +
                "(" + Constants.CLIENTID + "," + Constants.PHONE +
                "," + Constants.REALTY + "," + Constants.MINPRICE +
                "," + Constants.MAXPRICE + "," + Constants.REALTORID +
                "," + Constants.ADRESS + ")VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, need.getClient());
            preparedStatement.setString(2, need.getPhone());
            preparedStatement.setString(3, need.getType());
            preparedStatement.setInt(4, need.getMinPrice());
            preparedStatement.setInt(5, need.getMaxPrice());
            preparedStatement.setInt(6, need.getRealtorid());
            preparedStatement.setString(7, need.getAdress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Создание сделки
    public void addDeal(Deal deal) throws SQLException, ClassNotFoundException {
        String request = "INSERT INTO " + Constants.DEAL_TABLE +
                "(" + Constants.OFFER_ID + "," + Constants.NEED_ID +
                "," + Constants.REALTY + "," + Constants.PRICE +
                "," + Constants.OFFERER + "," + Constants.NEEDER +
                "," + Constants.REALTORID + "," + Constants.REALTOR +
                ")VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, deal.getIdOffer());
            preparedStatement.setString(2, deal.getIdNeed());
            preparedStatement.setString(3, deal.getRealty());
            preparedStatement.setInt(4, Integer.parseInt(deal.getPrice()));
            preparedStatement.setString(5, deal.getOfferer());
            preparedStatement.setString(6, deal.getNeeder());
            preparedStatement.setInt(7, deal.getRealtorId());
            preparedStatement.setString(8, deal.getRealtorName());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // удаление по id
    public void deleteRow(String table, String id) {
        try {
            statement = getDbConnection().createStatement();
            statement.executeUpdate("DELETE FROM " + table + " WHERE id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Выполнение несложных запросов
    public ResultSet querry(String querry) {
        try {
            statement = getDbConnection().createStatement();
            result = statement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void newRealtor(Realtor realtor) {
        String request = "INSERT INTO " + Constants.REALTOR +
                "(" + Constants.FIRSTNAME + "," + Constants.LASTNAME +
                "," + Constants.PATRONYMIC + "," + Constants.COMISSION +
                "," + Constants.USER_LOGIN + "," + Constants.USER_PASSWORD +
                ")VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setString(1, realtor.getName());
            preparedStatement.setString(2, realtor.getLastname());
            preparedStatement.setString(3, realtor.getPatronymic());
            preparedStatement.setString(4, realtor.getComission());
            preparedStatement.setString(5, realtor.getLogin());
            preparedStatement.setString(6, realtor.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
