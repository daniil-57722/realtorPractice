package sample.objects;

public class Offer {
    String client;
    String phone;
    String type;
    String realtor;
    String realtorid;
    int price;


    public String getRealtorid() {
        return realtorid;
    }

    public Offer(String client, String phone, String type, int price, String realtor, String realtorid){
        this.client = client;
        this.phone = phone;
        this.type = type;
        this.price = price;
        this.realtor = realtor;
        this.realtorid = realtorid;
    }

    public String getClient() {
        return client;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getRealtor() { return realtor;}
}
