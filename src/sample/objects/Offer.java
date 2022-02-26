package sample.objects;

public class Offer {
    String client;
    String phone;
    String type;
    String realtor;
    int realtorid;
    String address;
    int price;

    public Offer(String client, String phone, String type, int price, String realtor, int realtorid, String address){
        this.client = client;
        this.phone = phone;
        this.type = type;
        this.price = price;
        this.realtor = realtor;
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
