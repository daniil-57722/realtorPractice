package sample.objects;

public class Need {
    String client;
    String phone;
    String type;
    String realtor;
    int minPrice;
    int maxPrice;
    String realtorid;

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

    public String getRealtorid() {
        return realtorid;
    }

    public Need(String client, String phone, String type, int minPrice, int maxPrice, String realtor, String realtorid){
        this.client = client;
        this.phone = phone;
        this.type = type;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.realtor = realtor;
        this.realtorid = realtorid;
    }
}
