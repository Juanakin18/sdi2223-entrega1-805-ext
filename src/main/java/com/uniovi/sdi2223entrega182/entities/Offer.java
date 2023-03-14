package com.uniovi.sdi2223entrega182.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Offer {
    /*
    Un usuario identificado con perfil de Usuario Estándar, debe poder crear una oferta proporcionando:
    título descriptivo de la oferta, detalle textual de la oferta, fecha de alta de la oferta (puede ser la del
    sistema) y cantidad solicitada en euros
     */
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String details;
    private Date offerDate;
    private double amount;

    public Offer(){    }

    public Offer(Long id, String title, String details, Date offerDate, double amount) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.offerDate = offerDate;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
