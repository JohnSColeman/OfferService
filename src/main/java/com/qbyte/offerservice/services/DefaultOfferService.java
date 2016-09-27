package com.qbyte.offerservice.services;

import com.qbyte.offerservice.services.exceptions.OfferServiceException;
import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.dao.OfferDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SimpleOffer createSimpleOffer(final String description,
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
                return new ArrayList<>();
            }
        } catch (Exception ex) {
            throw new OfferServiceException(ex);
        }
    }
}
