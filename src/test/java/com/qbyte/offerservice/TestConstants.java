package com.qbyte.offerservice;

import com.qbyte.offerservice.rest.dto.SimpleOfferDTO;
import java.math.BigDecimal;

/**
 *
 * @author John
 */
public class TestConstants {

    public static final String VALID_DESCRIPTION = "This is a valid item description.";

    public static final BigDecimal VALID_PRICE = BigDecimal.TEN;

    public static final String VALID_CURRENCY_CODE = "EUR";

    public static final SimpleOfferDTO VALID_SIMPLE_OFFER_1
            = new SimpleOfferDTO(null, VALID_DESCRIPTION, VALID_PRICE, VALID_CURRENCY_CODE);
    
    public static final SimpleOfferDTO VALID_SIMPLE_OFFER_2
            = new SimpleOfferDTO(null, VALID_DESCRIPTION, VALID_PRICE, VALID_CURRENCY_CODE);
}
