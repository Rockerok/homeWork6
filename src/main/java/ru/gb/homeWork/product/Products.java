package ru.gb.homeWork.product;

import ru.gb.homeWork.sale.Sales;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "products")
    private List<Sales> sales;

    public Products() {
    }

    public Products(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Products(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() { return price;  }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Products [id = %d, title = %s, price = %d]", id, title, price);
    }
}
