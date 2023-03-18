package com.uniovi.sdi2223entrega182.controllers;

import com.uniovi.sdi2223entrega182.entities.Log;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.services.LogService;
import com.uniovi.sdi2223entrega182.services.RolesService;
import com.uniovi.sdi2223entrega182.services.SecurityService;
import com.uniovi.sdi2223entrega182.services.UsersService;
import com.uniovi.sdi2223entrega182.validators.SignUpFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@Controller
public class UsersController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;
    @Autowired
    private UsersService usersService;
    @Autowired
    private LogService logService;
    @Autowired
    private RolesService rolesService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    /**
     * Método que permite registrar un usuario en el sistema.
     *
     * @param user   que contiene los datos del nuevo usuario a registrar
     * @param result para comprobar si hay errores de validación en el formulario
     * @return la vista para registrarte si hay errores y la vista del home con
     *         ofertas destacadas si no hay errores en el formulario y hay ofertas
     *         destacada
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        }
        user.setRole(rolesService.getRoles()[UsersService.USER]);
        user.setMoney(UsersService.INITIAL_MONEY);
        usersService.addUser(user);

        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        Log log = new Log("ALTA","USER CONTROLLER SIGNUP", new Date());
        logService.addLog(log);
        return "redirect:home";
    }
    /**
     * Método que devuelve la vista con el formulario para registrarse
     *
     * @param model donde se guardará un nuevo usuario
     * @return la vista para cubrir el formulario de registro
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        logger.info(String.format("Acceso a SIGNUP"));
        Log log = new Log("PET","USER CONTROLLER SIGNUP", new Date());
        logService.addLog(log);
        return "signup";
    }

    /**
     * Método que devuelve la vista con el formulario para hacer log in en el
     * sistema
     *
     * @return la vista para cubrir el formulario de log in
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info(String.format("Acceso a LOGIN"));
        Log log = new Log("PET","USER CONTROLLER LOGIN", new Date());
        logService.addLog(log);
        return "login";
    }

}
