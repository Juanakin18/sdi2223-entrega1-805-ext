package com.uniovi.sdi2223entrega182.services;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.repositories.OffersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OffersService {

    @Autowired
    private OffersRepository offersRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public List<Offer> getOffers() {
        List<Offer> offers = new ArrayList<Offer>();
        offersRepository.findAll().forEach(offers::add);
        return offers;
    }
    public Offer getOffer(Long id) {
        return offersRepository.findById(id).get();
    }
    public void addOffer(Offer offer) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        offersRepository.save(offer);
        logger.info(String.format("Offer %s added", offer.getTitle()));
    }
    public void deleteOffer(Long id) {
        offersRepository.deleteById(id);
        logger.info(String.format("Offer %s deleted" + "offer id: ", id.toString()));
    }

    public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
        searchText ="%"+searchText+"%";
        Page<Offer> users  = offersRepository.searchByNameAndLastName(pageable, searchText);
        return users;
    }

    public Page<Offer> getOffers(Pageable pageable) {
        Page<Offer> list = offersRepository.findAll(pageable);
        return list;
    }

}
