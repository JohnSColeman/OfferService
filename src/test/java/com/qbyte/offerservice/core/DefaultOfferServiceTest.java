package com.qbyte.offerservice.core;


import com.qbyte.offerservice.entities.SimpleOffer;
import static com.qbyte.offerservice.TestConstants.VALID_CURRENCY_CODE;
import static com.qbyte.offerservice.TestConstants.VALID_DESCRIPTION;
import static com.qbyte.offerservice.TestConstants.VALID_PRICE;
import com.qbyte.offerservice.services.DefaultOfferService;
import com.qbyte.offerservice.services.OfferService;
import com.qbyte.offerservice.services.OfferServiceException;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.Before;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Tests that an OfferService implements the minimally required business rules
 * to prevent bad offers from being accepted.
 *
 * @author John Coleman
 */
public class DefaultOfferServiceTest {

    private OfferService service;
    
    @Before
    public void setupTest() {
        service = new DefaultOfferService();
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNullDescriptionParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(null, VALID_PRICE, VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNullPriceParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(VALID_DESCRIPTION, null, VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNullCurrencyCodeParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(VALID_DESCRIPTION, VALID_PRICE, null))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithBlankDescriptionParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer("", VALID_PRICE, VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithZeroPriceParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(VALID_DESCRIPTION, BigDecimal.ZERO, VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithNegativePriceParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(VALID_DESCRIPTION, BigDecimal.valueOf(-1), VALID_CURRENCY_CODE))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithInvalidCurrencyCodeParamThenOfferServiceExceptionThrown() {
        assertThatThrownBy(() -> service
                .createSimpleOffer(VALID_DESCRIPTION, VALID_PRICE, ""))
                .isInstanceOf(OfferServiceException.class);
    }

    @Test
    public void whenCreateSimpleOfferInvokedWithValidParamsThenNoExceptionsThrownAndOfferReturned() {
        try {
            SimpleOffer offer = service
                    .createSimpleOffer(VALID_DESCRIPTION, VALID_PRICE, VALID_CURRENCY_CODE);
            assertThat(offer).isNotNull();
        } catch (OfferServiceException ex) {
            fail("exception not expected");
        }
    }
}
