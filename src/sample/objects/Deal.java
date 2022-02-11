package sample.objects;

public class Deal {

    String idOffer;
    String idNeed;
    String realty;
    String price;
    String offerer;
    String needer;
    String realtorId;
    String realtorName;

    public Deal(String idOffer, String idNeed, String realty, String price,
                String offerer, String needer, String realtorId, String realtorName) {
        this.idOffer = idOffer;
        this.idNeed = idNeed;
        this.realty = realty;
        this.price = price;
        this.offerer = offerer;
        this.needer = needer;
        this.realtorId = realtorId;
        this.realtorName = realtorName;
    }

    public String getRealtorId() { return realtorId; }

    public String getRealtorName() { return realtorName; }

    public String getIdOffer() {
        return idOffer;
    }

    public String getIdNeed() {
        return idNeed;
    }

    public String getRealty() {
        return realty;
    }

    public String getPrice() {
        return price;
    }

    public String getOfferer() {
        return offerer;
    }

    public String getNeeder() {
        return needer;
    }
}
