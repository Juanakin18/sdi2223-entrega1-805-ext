package com.uniovi.sdi2223entrega182.services;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OffersService {

    @Autowired
    private OffersRepository offersRepository;

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
    }
    public void deleteOffer(Long id) {
        offersRepository.deleteById(id);
    }
}
