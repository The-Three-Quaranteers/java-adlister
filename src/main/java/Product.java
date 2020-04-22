import java.io.Serializable;

public class Product implements Serializable {

    //id
    private long id;
    //title
    private String title;
    //priceInCents
    private long priceInCents;
    //description
    private String description;

    //1.create zero arg const.
    public Product() {
    }

    //2.getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
