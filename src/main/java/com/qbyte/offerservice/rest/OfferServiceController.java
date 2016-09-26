package com.qbyte.offerservice.rest;

import com.qbyte.offerservice.services.OfferService;
import com.qbyte.offerservice.services.OfferServiceException;
import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.rest.dto.SimpleOfferDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements a REST service to manage offers.
 *
 * @author John Coleman
 */
@RestController
@RequestMapping("/offers")
public class OfferServiceController {

    @Autowired
    private OfferService offerService;

    /**
     * Return all the available offers in no particular order using from the
     * offer service.
     *
     * @return a collection of all the currently available offers
     */
    @RequestMapping(method = GET)
    public final ResponseEntity<?> getOffers() {
        try {
            Collection<SimpleOfferDTO> offers = new ArrayList<>();
            offerService.getOffers().parallelStream()
                    .forEach(offer -> offers.add(getSimpleOfferDTO(offer)));
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (OfferServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Use the offer service to create a new offer and return the created offer
     * to the requester using the given offer.
     *
     * @param offer the offer to use to create a new offer
     * @return the offer created
     */
    @RequestMapping(method = POST)
    public final ResponseEntity<?> createOffer(@RequestBody final SimpleOfferDTO offer) {
        try {
            SimpleOffer createdOffer = offerService
                    .createSimpleOffer(offer.getDescription(),
                            offer.getPrice(), offer.getCurrencyCode());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(getSimpleOfferDTO(createdOffer));
        } catch (OfferServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Convert an offer entity into an offer DTO.
     *
     * @param offer the offer to convert into a DTO
     * @return the offer DTO
     */
    private static SimpleOfferDTO getSimpleOfferDTO(final SimpleOffer offer) {
        SimpleOfferDTO dto = new SimpleOfferDTO();
        dto.setOfferId(offer.getOfferId());
        dto.setDescription(offer.getDescription());
        dto.setPrice(offer.getPrice().getNumber().numberValueExact(BigDecimal.class));
        dto.setCurrencyCode(offer.getPrice().getCurrency().getCurrencyCode());
        return dto;
    }
}
