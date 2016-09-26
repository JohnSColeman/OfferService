package com.qbyte.offerservice.rest;

import com.qbyte.offerservice.Application;
import static com.qbyte.offerservice.TestConstants.VALID_CURRENCY_CODE;
import static com.qbyte.offerservice.TestConstants.VALID_DESCRIPTION;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import static com.qbyte.offerservice.TestConstants.VALID_SIMPLE_OFFER_1;
import static com.qbyte.offerservice.TestConstants.VALID_SIMPLE_OFFER_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration tests to exercise the offer service REST resources.
 *
 * @author John Coleman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class OfferServiceControllerTest {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        assertThat(this.mappingJackson2HttpMessageConverter).isNotNull();
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenPostToOffers_ThenOfferCreated() throws Exception {
        mockMvc.perform(post("/offers/")
                .content(this.json(VALID_SIMPLE_OFFER_1))
                .contentType(contentType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.offerId").isNumber())
                .andExpect(jsonPath("$.desc", is(VALID_DESCRIPTION)))
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.cc", is(VALID_CURRENCY_CODE)));
    }

    @Test
    public void whenGetOffers_ThenOfferReturnedHasIdNumber() throws Exception {
        // following tries to ensure an offer is always present
        mockMvc.perform(post("/offers/")
                .content(this.json(VALID_SIMPLE_OFFER_2))
                .contentType(contentType));
        mockMvc.perform(get("/offers/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[0].offerId").isNumber());
    }

    String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
