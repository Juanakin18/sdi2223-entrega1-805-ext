package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.services.OffersService;
import com.uniovi.sdi2223entrega182.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {
    /**
     * Método que retorna a la vista principal del sistema.
     * @return la vista mencionada
     */

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model, Pageable pageable, @RequestParam(value = "", required = false)String searchText) {
        Page<Offer> offers = this.getPageOffers(pageable, searchText);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        double dinero = usersService.getUserByEmail(email).getMoney();

        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);
        model.addAttribute("email2", activeUser.getEmail());
        model.addAttribute("money", dinero);
        return "home";
    }
    @RequestMapping("/home/update")
    public String updateList(Model model, Pageable pageable, @RequestParam(value = "", required = false)String searchText){
        Page<Offer> offers = this.getPageOffers(pageable, searchText);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        double dinero = usersService.getUserByEmail(email).getMoney();

        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);
        model.addAttribute("email2", activeUser.getEmail());
        model.addAttribute("money", dinero);
        return "/home::tableOffers";
    }

    private Page<Offer> getPageOffers(Pageable pageable, String searchText ){
        Page<Offer> offers;
        if(searchText != null && !searchText.isEmpty()){
            offers = offersService.searchOffersByTitle(pageable, searchText);
        } else {
            offers = offersService.getOffers(pageable);
        }
        return offers;
    }

}
