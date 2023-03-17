package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.services.OffersService;
import com.uniovi.sdi2223entrega182.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uniovi.sdi2223entrega182.validators.AddOfferValidator;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AddOfferValidator addOfferValidator;

    @RequestMapping("/offer/list")
    public String getList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        model.addAttribute("offersList", activeUser.getOffers());
        return "/offer/list";
    }

    @RequestMapping(value = "/offer/add")
    public String getOffer(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("usersList", usersService.getUsers());
        return "offer/add";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@Validated Offer offer, BindingResult result) {
        addOfferValidator.validate(offer, result);
        if (result.hasErrors()) {
            return "offer/add";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        offer.setUser(activeUser);
        offersService.addOffer(offer);
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

}
