package com.qbyte.offerservice.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.qbyte.offerservice.entities.SimpleOffer;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by johncoleman on 20/10/2016.
 */
public class SimpleOfferSerializer extends StdSerializer<SimpleOffer> {

    public SimpleOfferSerializer() {
        this(null);
    }

    public SimpleOfferSerializer(Class<SimpleOffer> t) {
        super(t);
    }

    @Override
    public void serialize(SimpleOffer simpleOffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("offerId", simpleOffer.getOfferId());
        jsonGenerator.writeNumberField("price", simpleOffer.getPrice().getNumber().numberValueExact(BigDecimal.class));
        jsonGenerator.writeStringField("desc", simpleOffer.getDescription());
        jsonGenerator.writeStringField("cc", simpleOffer.getPrice().getCurrency().getCurrencyCode());
        jsonGenerator.writeEndObject();
    }
}
