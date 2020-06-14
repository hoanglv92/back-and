package hoanglinh.eshop.models.product;

import hoanglinh.eshop.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String image;
    @NotEmpty
    private String producer;
    @NotEmpty
    private String description;
    @NotEmpty
    private java.sql.Date productionDate;
    private int sale;
    @NotEmpty
    private int quantity;
    @NotEmpty
    private double price;
    @OneToOne
    private User user;
    private Long purchase;
    private Long like;

    public Phone() {
    }

    public Phone(Long id, @NotEmpty String name, @NotEmpty String image, @NotEmpty String producer,
                 @NotEmpty String description, @NotEmpty java.sql.Date productionDate, int sale, @NotEmpty int quantity,
                 @NotEmpty double price, Long purchase, Long like) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.producer = producer;
        this.description = description;
        this.productionDate = productionDate;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
        this.purchase = purchase;
        this.like = like;
    }

    public Phone(Long id, @NotEmpty String name, @NotEmpty String image, @NotEmpty String producer, @NotEmpty
            String description, @NotEmpty java.sql.Date productionDate, int sale, @NotEmpty int quantity, @NotEmpty double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.producer = producer;
        this.description = description;
        this.productionDate = productionDate;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
    }

    public Phone(@NotEmpty String name, @NotEmpty String image, @NotEmpty String producer, @NotEmpty String description,
                 @NotEmpty java.sql.Date productionDate, int sale, @NotEmpty int quantity, @NotEmpty double price, Long purchase,
                 Long like) {
        this.name = name;
        this.image = image;
        this.producer = producer;
        this.description = description;
        this.productionDate = productionDate;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
        this.purchase = purchase;
        this.like = like;
    }

    public Phone(@NotEmpty String name, @NotEmpty String image, @NotEmpty String producer, @NotEmpty String description, @NotEmpty java.sql.Date productionDate, int sale, @NotEmpty int quantity, @NotEmpty double price, User user) {
        this.name = name;
        this.image = image;
        this.producer = producer;
        this.description = description;
        this.productionDate = productionDate;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(java.sql.Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getPurchase() {
        return purchase;
    }

    public void setPurchase(Long purchase) {
        this.purchase = purchase;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
