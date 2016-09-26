package com.qbyte.offerservice.dao;

import static com.qbyte.offerservice.TestConstants.VALID_CURRENCY_CODE;
import static com.qbyte.offerservice.TestConstants.VALID_DESCRIPTION;
import static com.qbyte.offerservice.TestConstants.VALID_PRICE;
import com.qbyte.offerservice.entities.SimpleOffer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
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
            SimpleOffer offer = new SimpleOffer(VALID_DESCRIPTION,
                    Money.of(VALID_PRICE, VALID_CURRENCY_CODE));
            dao.store(offer);
            assertThat(dao.getOffers()).containsOnly(offer);
        } catch (Exception ex) {
            fail("exception not expected");
        }
    }

    @Test
    public void whenStoreOffer_ThenOfferIdIsNotNull() {
        try {
            SimpleOffer offer = new SimpleOffer(VALID_DESCRIPTION,
                    Money.of(VALID_PRICE, VALID_CURRENCY_CODE));
            dao.store(offer);
            assertThat(offer.getOfferId()).isNotNull();
        } catch (Exception ex) {
            fail("exception not expected");
        }
    }

}
