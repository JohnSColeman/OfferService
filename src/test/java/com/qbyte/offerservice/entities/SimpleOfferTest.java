package com.qbyte.offerservice.entities;


import com.qbyte.offerservice.TestConstants;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Tests that an OfferService implements the minimally required business rules
 * to prevent bad offers from being accepted.
 *
 * @author John Coleman
 */
public class SimpleOfferTest {

    @Test
    public void whenCreateSimpleOfferInvokedWithNullDescriptionParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(null, TestConstants.VALID_PRICE, TestConstants.VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNullPriceParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(TestConstants.VALID_DESCRIPTION, null, TestConstants.VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNullCurrencyCodeParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(TestConstants.VALID_DESCRIPTION, TestConstants.VALID_PRICE, null))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithBlankDescriptionParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create("", TestConstants.VALID_PRICE, TestConstants.VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithZeroPriceParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(TestConstants.VALID_DESCRIPTION, BigDecimal.ZERO, TestConstants.VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNegativePriceParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(TestConstants.VALID_DESCRIPTION, BigDecimal.valueOf(-1), TestConstants.VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithInvalidCurrencyCodeParamThenOfferServiceExceptionThrown() {
        Assertions.assertThatThrownBy(() -> SimpleOffer.create(TestConstants.VALID_DESCRIPTION, TestConstants.VALID_PRICE, ""))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithValidParamsThenNoExceptionsThrownAndOfferReturned() {
        try {
            SimpleOffer offer = SimpleOffer.create(TestConstants.VALID_DESCRIPTION, TestConstants.VALID_PRICE, TestConstants.VALID_CURRENCY_CODE);
            Assertions.assertThat(offer).isNotNull();
        } catch (OfferServiceException ex) {
            Assertions.fail("exception not expected");
        }
    }
}
