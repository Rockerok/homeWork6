package ru.gb.homeWork.sale;

import ru.gb.homeWork.product.Products;
import ru.gb.homeWork.user.Users;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "date_sales")
    private Date date_sales;

    @ManyToOne
    @JoinColumn (name = "users")
    private Users users;

    @ManyToOne
    @JoinColumn (name = "products")
    private Products products;

    @Column (name = "price_prod")
    private int price_prod;

    public Sales() {
    }

    public Sales(Date date_sales, Users users, Products products,int price_prod) {
        this.date_sales = date_sales;
        this.users = users;
        this.products = products;
        this.price_prod = price_prod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_sales() {
        return date_sales;
    }

    public void setDate_sales(Date date_sales) {
        this.date_sales = date_sales;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getPrice_prod() {
        return price_prod;
    }

    public void setPrice_prod(int price_prod) {
        this.price_prod = price_prod;
    }

}

