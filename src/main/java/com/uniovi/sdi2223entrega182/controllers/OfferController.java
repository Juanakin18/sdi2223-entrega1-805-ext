package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;

    @RequestMapping("/offer/list")
    public String getList(Model model){
        model.addAttribute("offersList", offersService.getOffers());
        return "/offer/list";
    }

    @RequestMapping(value = "/offer/add")
    public String getOffer() {
        return "offer/add";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@ModelAttribute Offer offer) {
        offersService.addOffer(offer);
        return "redirect:/offer/list";
    }

}
