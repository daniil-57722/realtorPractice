package sample.objects;

/**
 * класс предложений
 */
public class Offer {
    String client;
    String type;
    int realtorid;
    String address;
    int price;

    public Offer(String client, String type,
                 int price, int realtorid, String address){
        this.client = client;
        this.type = type;
        this.price = price;
        this.realtorid = realtorid;
        this.address = address;
    }
    public String getAddress() { return address; }
    public int getRealtorid() {
        return realtorid;
    }
    public String getClient() {
        return client;
    }
    public String getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
}
