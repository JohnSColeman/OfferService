package com.qbyte.offerservice.services;

import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;

import java.util.Collection;

/**
 *
 * @author John
 */
public interface OfferService {
    /**
     * Add a SimpleOffer.
     *
     * @param offer the SimpleOffer to add to the system
     * @return the Offer as known to the system
     * @throws OfferServiceException when creation of a SimpleOffer fails
     */
    SimpleOffer addSimpleOffer(SimpleOffer offer) throws OfferServiceException;
    
    /**
     * Retrieves all the available offers.
     *
     * @return all available offers
     * @throws OfferServiceException when retrieval of offers fails
     */
    Collection<SimpleOffer> getOffers() throws OfferServiceException;
}
