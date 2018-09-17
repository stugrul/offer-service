package com.worldpay.offer.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer {
    /**
     * Id is a primary key
     * which identifies offers
     */
    @Id
    @Column(name = "OFFER_ID")
    private Long id;

    /**
     * Unique offer name
     */
    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    /**
     * Description of the offer
     */
    @Column(unique = false, nullable = false)
    @NotNull
    private String description;

    /**
     * Price of the offer
     */
    @Column(unique = false, nullable = false)
    @NotNull
    private BigDecimal price;

    /**
     * Currency value
     */
    @Column(unique = false, nullable = false)
    @NotNull
    private String currency;

    /**
     * Expiry date. Required to follow ISO Local Date format
     * Format: yyyy-MM-dd
     */
    @Column(unique = false, nullable = false)
    @NotNull
    private String validUntil;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(final String validUntil) {
        this.validUntil = validUntil;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long idToSet) {
        id = idToSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offer other = (Offer) obj;

        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "name='" + name + '\'' +
                '}';
    }
}
