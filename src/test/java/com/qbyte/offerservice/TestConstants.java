package com.qbyte.offerservice;

import com.qbyte.offerservice.entities.SimpleOffer;

import java.math.BigDecimal;

/**
 *
 * @author John
 */
public class TestConstants {

    public static final String VALID_DESCRIPTION = "This is a valid item description.";

    public static final BigDecimal VALID_PRICE = BigDecimal.TEN;

    public static final String VALID_CURRENCY_CODE = "EUR";

    public static SimpleOffer VALID_SIMPLE_OFFER_1;

    public static SimpleOffer VALID_SIMPLE_OFFER_2;

    static {
        try {
            VALID_SIMPLE_OFFER_1
                    = SimpleOffer.create(VALID_DESCRIPTION, VALID_PRICE, VALID_CURRENCY_CODE);

            VALID_SIMPLE_OFFER_2
                    = SimpleOffer.create(VALID_DESCRIPTION, VALID_PRICE, VALID_CURRENCY_CODE);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
