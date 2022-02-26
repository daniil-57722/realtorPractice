package sample.objects;

public class Need {
    String client;
    String phone;
    String type;
    String realtor;
    int minPrice;
    int maxPrice;
    int realtorid;
    String adress;

    public String getAdress() { return adress; }

    public String getClient() {
        return client;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getRealtor() {
        return realtor;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getRealtorid() { return realtorid; }

    public Need(String client, String phone, String type, int minPrice, int maxPrice, String realtor, int realtorid, String adress){
        this.client = client;
        this.phone = phone;
        this.type = type;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.realtor = realtor;
        this.realtorid = realtorid;
        this.adress = adress;
    }
}
