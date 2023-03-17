package com.uniovi.sdi2223entrega182.services;

import com.uniovi.sdi2223entrega182.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RolesService rolesService;

    /**
     * Inicia la base de datos con 5 usuarios, 3 ofertas para cada uno, 2 compras y
     * 1 conversación por oferta entre su ofertante y un demandante. (2 líneas por
     * interlocutor).
     */
    @PostConstruct
    public void init() {
        // Creo los usuarios

        User user1 = new User("mariobalotelli@uniovi.es", "Mario", "Balotelli");
        user1.setPassword("123456");
        user1.setRole(rolesService.getRoles()[1]);
        user1.setMoney(100d);
        User user2 = new User("leomessi@uniovi.es", "Leo", "Messi");
        user2.setPassword("123456");
        user2.setRole(rolesService.getRoles()[1]);
        user2.setMoney(100d);
        User user3 = new User("cristianoronaldo@uniovi.es", "Cristiano", "Ronaldo");
        user3.setPassword("123456");
        user3.setRole(rolesService.getRoles()[1]);
        user3.setMoney(100d);
        User user4 = new User("upamencano@uniovi.es", "Albert", "Upamencano");
        user4.setPassword("123456");
        user4.setRole(rolesService.getRoles()[1]);
        user4.setMoney(100d);
        User user5 = new User("djuka@uniovi.es", "Uros", "Djurdjevic");
        user5.setPassword("123456");
        user5.setRole(rolesService.getRoles()[1]);
        user5.setMoney(10d);
        User user6 = new User("admin@email.com", "Usuario", "Administrador");
        user6.setPassword("admin");
        user6.setRole(rolesService.getRoles()[0]);
        user6.setMoney(100d);

        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
    }
}