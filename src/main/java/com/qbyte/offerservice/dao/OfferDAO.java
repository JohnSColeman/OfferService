package com.qbyte.offerservice.dao;

import com.qbyte.offerservice.entities.SimpleOffer;
import java.util.Collection;

/**
 * Defines the required behaviours to implement an Offer persistence layer.
 *
 * @author John Coleman
 */
public interface OfferDAO {
    /**
     * Store an offer using the persistence mechanism of an implementation.
     *
     * @param offer the offer to store
     * @throws DAOException if there is a problem storing an offer
     */
    void store(SimpleOffer offer) throws DAOException;
    
    /**
     * Get a collection of all the offers in store.
     *
     * @return a collection of all the offers
     * @throws DAOException if there is a problem getting offers
     */
    Collection<SimpleOffer> getOffers()  throws DAOException;
}
