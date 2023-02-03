package sample.objects;

/**
 * класс для сделок
 */
public class Deal {

    String idOfferer;
    String idNeeder;
    String realty;
    String price;
    int realtorId;
    String address;

    public Deal(String idOfferer, String idNeeder, String realty, String address, String price, int realtorId) {
        this.idOfferer = idOfferer;
        this.address = address;
        this.idNeeder = idNeeder;
        this.realty = realty;
        this.price = price;
        this.realtorId = realtorId;
    }
    public String getAddress() { return address; }
    public int getRealtorId() { return realtorId; }
    public String getRealty() {
        return realty;
    }
    public String getPrice() {
        return price;
    }
    public String getIdOfferer() { return idOfferer; }
    public String getIdNeeder() { return idNeeder; }
}
