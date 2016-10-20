package com.qbyte.offerservice.dao;

import com.qbyte.offerservice.entities.SimpleOffer;
import org.junit.Before;
import org.junit.Test;

import static com.qbyte.offerservice.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Test functionality of InMemoryOfferDAO.
 * 
 * @author John Coleman
 */
public class InMemoryOfferDAOTest {
 
    private OfferDAO dao;
    
    @Before
    public void setupTest() {
        dao = new InMemoryOfferDAO();
    }

    @Test
    public void whenStoreOffer_ThenGetOffersReturnsOffer() {
        try {
            SimpleOffer offer = SimpleOffer.create(VALID_DESCRIPTION,
                    VALID_PRICE, VALID_CURRENCY_CODE);
            dao.store(offer);
            assertThat(dao.getOffers()).containsOnly(offer);
        } catch (Exception ex) {
            fail("exception not expected");
        }
    }

    @Test
    public void whenStoreOffer_ThenOfferIdIsNotNull() {
        try {
            SimpleOffer offer = SimpleOffer.create(VALID_DESCRIPTION,
                    VALID_PRICE, VALID_CURRENCY_CODE);
            dao.store(offer);
            assertThat(offer.getOfferId()).isNotNull();
        } catch (Exception ex) {
            fail("exception not expected");
        }
    }
}
