package com.qbyte.offerservice.services;

import com.qbyte.offerservice.entities.SimpleOffer;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author John
 */
public interface OfferService {
    /**
     * Create a SimpleOffer with the given properties.
     *
     * @param description a friendly description of the item on offer
     * @param price the price of the item on offer
     * @param currencyCode the currency code of the item on offer
     * @return the Offer with the given properties
     * @throws OfferServiceException when creation of a SimpleOffer fails
     */
    SimpleOffer createSimpleOffer(String description, Number price,
            String currencyCode) throws OfferServiceException;
    
    /**
     * Retrieves all the available offers.
     *
     * @return all available offers
     * @throws OfferServiceException when retrieval of offers fails
     */
    Collection<SimpleOffer> getOffers() throws OfferServiceException;
}
