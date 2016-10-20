package com.qbyte.offerservice.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by johncoleman on 20/10/2016.
 */
public class SimpleOfferDeserializer extends StdDeserializer<SimpleOffer> {

    public SimpleOfferDeserializer() {
        this(null);
    }

    public SimpleOfferDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SimpleOffer deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        BigDecimal price = node.get("price").decimalValue();
        String description = node.get("desc").asText();
        String currencyCode = node.get("cc").asText();
        SimpleOffer offer;
        try {
            offer = SimpleOffer.create(description, price, currencyCode);
        } catch (OfferServiceException ex) {
            throw new IOException(ex);
        }
        return offer;
    }
}

