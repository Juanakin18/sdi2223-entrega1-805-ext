package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Log;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.services.LogService;
import com.uniovi.sdi2223entrega182.services.OffersService;
import com.uniovi.sdi2223entrega182.services.SecurityService;
import com.uniovi.sdi2223entrega182.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Set;

@Controller
public class HomeController {
    /**
     * Método que retorna a la vista principal del sistema.
     * @return la vista mencionada
     */

    @Autowired
    private OffersService offersService;
    @Autowired
    private LogService logService;

    private boolean loggin = false;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping("/")
    public String index() {
        logger.info(String.format("Acceso a HOME INDEX"));
        Log log = new Log("PET","HOME CONTROLLER INDEX", new Date());
        logService.addLog(log);
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model, Pageable pageable, @RequestParam(value = "", required = false)String searchText) {
        Page<Offer> offers = this.getPageOffers(pageable, searchText);
        User activeUser = getUser();

        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);
        model.addAttribute("email2", activeUser.getEmail());
        model.addAttribute("money", activeUser.getMoney());

        logger.info(String.format("Acceso a HOME HOME"));
        Log log = new Log("PET","HOME CONTROLLER HOME", new Date());
        logService.addLog(log);

        return "home";
    }

    @RequestMapping(value = {"/home/buy/{id}"}, method = RequestMethod.GET)
    public String homeBuy(Model model, @PathVariable Long id) {
        User activeUser = getUser();
        Offer offer = offersService.getOffer(id);

        if(activeUser.getMoney()< offer.getAmount() || !offer.isAvailable() || activeUser.getOffers().contains(offer)){
            return "redirect:/home";
        }

        activeUser.setMoney(activeUser.getMoney() - offer.getAmount());
        offer.setBuyer(activeUser);
        Set<Offer> offerSet = activeUser.getOffersBought();
        offerSet.add(offer);
        activeUser.setOffersBought(offerSet);
        offer.setNotAvailable();

        usersService.addUser(activeUser);
        offersService.addOffer(offer);

        return "redirect:/home";
    }

    @RequestMapping("/log")
    public String log(Model model){
        model.addAttribute("logslist", logService.getLogs());
        if (!loggin){
            logger.info(String.format("Acceso a LOG LIST"));
            Log log = new Log("PET","LOG CONTROLLER LIST", new Date());
            logService.addLog(log);
            loggin = true;
        }
        return "log";
    }
    @RequestMapping("/log/delete/")
    public String deleteOffer(){
        logService.deleteAll();
        return "redirect:/log";
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

    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        return activeUser;
    }
}
