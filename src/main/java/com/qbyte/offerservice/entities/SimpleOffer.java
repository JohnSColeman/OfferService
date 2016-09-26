package com.qbyte.offerservice.entities;

import javax.money.MonetaryAmount;

/**
 * An entity encapsulating an offer. The properties of the offer are
 * generally intended to be immutable.
 *
 * @author John Coleman
 */
public class SimpleOffer {
    
    private Integer offerId;
    
    private String description;
    
    private MonetaryAmount price;

    public SimpleOffer() {
        this(null, null);
    }

    public SimpleOffer(String description, MonetaryAmount price) {
        this.description = description;
        this.price = price;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public String getDescription() {
        return description;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    @Override
    public String toString() {
        return "SimpleOffer{" + "offerId=" + offerId + ", description="
                + description + ", price=" + price + '}';
    }
}
