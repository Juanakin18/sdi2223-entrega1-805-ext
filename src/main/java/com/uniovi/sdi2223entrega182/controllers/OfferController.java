package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Log;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.services.*;
import com.uniovi.sdi2223entrega182.validators.AddOfferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.uniovi.sdi2223entrega182.services.OffersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AddOfferValidator addOfferValidator;

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @RequestMapping("/offer/list")
    public String getList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        model.addAttribute("offersList", activeUser.getOffers());
        logger.info(String.format("Acceso a OFFER LIST"));
        Log log = new Log("PET","OFFER CONTROLLER LIST", new Date());
        logService.addLog(log);
        return "/offer/list";
    }

    @RequestMapping(value = "/offer/add")
    public String getOffer(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("usersList", usersService.getUsers());
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


    @RequestMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        if (offersService.getOffer(id).getUser().getId() == activeUser.getId())
            offersService.deleteOffer(id);
        return "redirect:/offer/list";
    }

    @RequestMapping("/offer/bought")
    public String getListBought(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        model.addAttribute("offersList", activeUser.getOffersBought());
        return "/offer/bought";
    }

}
