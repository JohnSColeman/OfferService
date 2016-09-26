package com.qbyte.offerservice.rest.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * This data transfer object encapsulates all the properties of a simple offer.
 *
 * @author John Coleman
 */
public class SimpleOfferDTO {
    
    private Integer offerId;
    
    @JsonProperty("desc")
    private String description;
    
    private BigDecimal price;
    
    @JsonProperty("cc")
    private String currencyCode;

    public SimpleOfferDTO() {
    }

    public SimpleOfferDTO(Integer offerId, String description, BigDecimal price, String currencyCode) {
        this.offerId = offerId;
        this.description = description;
        this.price = price;
        this.currencyCode = currencyCode;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public int hashCode() {
        return offerId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleOfferDTO other = (SimpleOfferDTO) obj;
        return Objects.equals(this.offerId, other.offerId);
    }    
    
    @Override
    public String toString() {
        return "SimpleOfferDTO{" + "offerId=" + offerId + ", description=" + description + ", price=" + price + ", currencyCode=" + currencyCode + '}';
    }
}
