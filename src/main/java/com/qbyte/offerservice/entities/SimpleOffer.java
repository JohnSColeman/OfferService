package com.qbyte.offerservice.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qbyte.offerservice.rest.SimpleOfferDeserializer;
import com.qbyte.offerservice.rest.SimpleOfferSerializer;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

/**
 * An entity encapsulating an offer. The properties of the offer are
 * generally intended to be immutable.
 *
 * @author John Coleman
 */
@JsonSerialize(using = SimpleOfferSerializer.class)
@JsonDeserialize(using = SimpleOfferDeserializer.class)
public class SimpleOffer {

    private static final SimpleOfferBuilder BUILDER = new SimpleOfferBuilder();
    
    private Integer offerId;
    
    private String description;
    
    private MonetaryAmount price;

    public SimpleOffer() {
        this(null, null);
    }

    private SimpleOffer(String description, MonetaryAmount price) {
        this.description = description;
        this.price = price;
    }

    public static SimpleOffer create(final String description,
                                     final Number price, final String currencyCode)
            throws OfferServiceException {
        return BUILDER.build(description, price, currencyCode);
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getDescription() {
        return description;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SimpleOffer{" + "offerId=" + offerId + ", description="
                + description + ", price=" + price + '}';
    }

    static class SimpleOfferBuilder {

        public SimpleOffer build(final String description,
                                 final Number price, final String currencyCode)
                throws OfferServiceException {
            try {
                if (description == null) {
                    throw new NullPointerException("description cannot be null");
                } else if (price == null) {
                    throw new NullPointerException("price cannot be null");
                } else if (currencyCode == null) {
                    throw new NullPointerException("currency code cannot be null");
                } else if (description.equals("")) {
                    throw new IllegalArgumentException("description must be provided");
                } else if (price.equals(BigDecimal.ZERO)) {
                    throw new IllegalArgumentException("price cannot be 0");
                } else if (price.intValue() < 0) {
                    throw new IllegalArgumentException("price cannot be less than 0");
                }
                MonetaryAmount money = Money.of(price, currencyCode);
                SimpleOffer offer = new SimpleOffer(description, money);
                return offer;
            } catch (Exception ex) {
                throw new OfferServiceException(ex);
            }
        }
    }
}
