package com.qbyte.offerservice.rest;

import com.qbyte.offerservice.entities.SimpleOffer;
import com.qbyte.offerservice.services.OfferService;
import com.qbyte.offerservice.services.exceptions.OfferServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
            Collection<SimpleOffer> offers = offerService.getOffers();
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (OfferServiceException ex) {
            ex.printStackTrace();
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
    public final ResponseEntity<?> createOffer(@RequestBody final SimpleOffer offer) {
        try {
            SimpleOffer createdOffer = offerService.addSimpleOffer(offer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdOffer);
        } catch (OfferServiceException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
