package sample.objects;

/**
 * класс для потребностей
 */
public class Need {
    String client;
    String phone;
    String type;
    String realtor;
    int minPrice;
    int maxPrice;
    int realtorid;
    String adress;

    public Need(String client, String type, int minPrice, int maxPrice, int realtorid, String adress){
        this.client = client;
        this.type = type;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.realtorid = realtorid;
        this.adress = adress;
    }
    public String getAdress() { return adress; }
    public String getClient() {
        return client;
    }
    public String getType() {
        return type;
    }
    public int getMinPrice() {
        return minPrice;
    }
    public int getMaxPrice() {
        return maxPrice;
    }
    public int getRealtorid() { return realtorid; }
}
