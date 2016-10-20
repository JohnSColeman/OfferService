package com.qbyte.offerservice.dao;

import com.qbyte.offerservice.entities.SimpleOffer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An in memory persistence provider for storing simple offers using a map.
 * 
 * @author John Coleman
 */
@Service("offerDAO")
public class InMemoryOfferDAO implements OfferDAO {
    
    /** The nextId provides id values. */
    private final AtomicInteger nextId = new AtomicInteger();
    
    /** A map to store the simple offers. */
    private final Map<Integer, SimpleOffer> offers = new ConcurrentHashMap<>();

    @Override
    public void store(SimpleOffer offer) throws DAOException {
        if (offer.getOfferId() == null) {
            offer.setOfferId(nextId.incrementAndGet());
        }
        offers.put(offer.getOfferId(), offer);
    }

    @Override
    public Collection<SimpleOffer> getOffers() throws DAOException {
        return Collections.unmodifiableCollection(offers.values());
    }
}
