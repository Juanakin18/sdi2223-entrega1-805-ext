package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Log;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.services.LogService;
import com.uniovi.sdi2223entrega182.services.OffersService;
import com.uniovi.sdi2223entrega182.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @RequestMapping("/offer/list")
    public String getList(Model model){
        model.addAttribute("offersList", offersService.getOffers());
        logger.info(String.format("Acceso a OFFER LIST"));
        Log log = new Log("PET","OFFER CONTROLLER LIST", new Date());
        logService.addLog(log);
        return "/offer/list";
    }

    @RequestMapping(value = "/offer/add")
    public String getOffer() {
        logger.info(String.format("Acceso a OFFER GET"));
        Log log = new Log("PET","OFFER CONTROLLER GET", new Date());
        logService.addLog(log);
        return "offer/add";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@ModelAttribute Offer offer) {
        offersService.addOffer(offer);
        logger.info(String.format("Acceso a OFFER ADD"));
        Log log = new Log("PET","OFFER CONTROLLER ADD", new Date());
        logService.addLog(log);
        return "redirect:/offer/list";
    }

}
