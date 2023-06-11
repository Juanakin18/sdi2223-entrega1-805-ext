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
import com.uniovi.sdi2223entrega182.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.sdi2223entrega182.validators.AddOfferValidator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;

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

    @Autowired
    private HttpSession httpSession;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    /**
     * Método que renderiza la lista de ofertas
     * @param model Modelo
     * @return La vista
     */
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

    /**
     * Método que añade una oferta
     * @param model Modelo
     * @return La vista
     */
    @RequestMapping(value = "/offer/add")
    public String getOffer(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("usersList", usersService.getUsers());
        return "offer/add";
    }
    @RequestMapping(value = "/offer/details/{id}")
    public String detailsOffer(Model model, @PathVariable Long id) {
        model.addAttribute("offer",offersService.getOffer(id));
        return "offer/details";
    }


    /**
     * Método para comprar
     * @param model Model
     * @param id Artículo a comprar
     * @return La vista
     */
    @RequestMapping(value = {"/home/buy/{id}"}, method = RequestMethod.GET)
    public String homeBuy(Model model, @PathVariable Long id) {
        User activeUser = usersService.getUser();
        Offer offer = offersService.getOffer(id);

        if(activeUser.getMoney()< offer.getAmount()){
            httpSession.setAttribute("enoughMoney",false);
            return "redirect:/home";
        }
        if( !offer.isAvailable() || activeUser.getOffers().contains(offer)){
            httpSession.setAttribute("enoughMoney",true);
            return "redirect:/home";
        }
        offersService.updateOfferUser(activeUser,offer);
        usersService.addUser(activeUser);
        offersService.addOffer(offer);


        return "redirect:/home";
    }

    /**
     * Establece una oferta
     * @param offer Oferta
     * @param image Imagen
     * @param result Resultado
     * @return vista
     */

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@Validated Offer offer, @RequestParam("file") MultipartFile image, BindingResult result) {
        addOfferValidator.validate(offer, result);
        if (result.hasErrors()) {
            return "offer/add";
        }
        if (!image.isEmpty()){
            try {
                offersService.addImage(image);
                offer.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            offer.setImage("default-image.png");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        offer.setUser(activeUser);
        offersService.addOffer(offer);
        logger.info(String.format("Acceso a OFFER ADD"));
        Log log = new Log("PET","OFFER CONTROLLER ADD", new Date());
        logService.addLog(log);
        return "redirect:/offer/list";
    }


    /**
     * Borra una oferta
     * @param id La oferta a borrar
     * @return Redirige a la lista de ofertas
     */
    @RequestMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        if (offersService.getOffer(id).getUser().getId() == activeUser.getId())
            offersService.deleteOffer(id);
        return "redirect:/offer/list";
    }

    /**
     * Devuelve la lista de comprados
     * @param model Modelo
     * @return Vista de todos los artículos comprados
     */
    @RequestMapping("/offer/bought")
    public String getListBought(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        model.addAttribute("offersList", activeUser.getOffersBought());
        return "/offer/bought";
    }

}
