package com.qbyte.offerservice.services;

import com.qbyte.offerservice.dao.OfferDAO;
import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * This class implements a basic offer service.
 *
 * @author John Coleman
 */
@Service("offerService")
public class DefaultOfferService implements OfferService {

    private OfferDAO offerDAO;
    
    private Logger logger = LoggerFactory.getLogger(DefaultOfferService.class);

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @Override
    public SimpleOffer addSimpleOffer(SimpleOffer offer)
            throws OfferServiceException {
        try {
            if (offerDAO != null) {
                offerDAO.store(offer);
                logger.debug("STORED OFFER " + offer);
            }
            return offer;
        } catch (Exception ex) {
            throw new OfferServiceException(ex);
        }
    }

    @Override
    public Collection<SimpleOffer> getOffers() throws OfferServiceException {
        try {
            if (offerDAO != null) {
                return offerDAO.getOffers();
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception ex) {
            throw new OfferServiceException(ex);
        }
    }
}
