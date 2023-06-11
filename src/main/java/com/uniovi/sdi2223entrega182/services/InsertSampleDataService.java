package com.uniovi.sdi2223entrega182.services;

import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        User user10 = new User("mariobalotelli2@uniovi.es", "Mario", "Balotelli");
        user10.setPassword("123456");
        user10.setRole(rolesService.getRoles()[1]);
        user10.setMoney(100);
        User user11 = new User("mariobalotelli3@uniovi.es", "Mario", "Balotelli");
        user11.setPassword("123456");
        user11.setRole(rolesService.getRoles()[1]);
        user11.setMoney(100);
        User user111 = new User("mariobalotelli4@uniovi.es", "Mario", "Balotelli");
        user111.setPassword("123456");
        user111.setRole(rolesService.getRoles()[1]);
        user111.setMoney(100);
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
        User user001 = new User("user01@email.com", "Usuario", "Administrador");
        user001.setPassword("123456");
        user001.setRole(rolesService.getRoles()[1]);
        user001.setMoney(100d);
        User user002 = new User("user02@email.com", "Usuario", "Administrador");
        user002.setPassword("123456");
        user002.setRole(rolesService.getRoles()[1]);
        user002.setMoney(100d);
        User user003 = new User("user03@email.com", "Usuario", "Administrador");
        user003.setPassword("123456");
        user003.setRole(rolesService.getRoles()[1]);
        user003.setMoney(100d);
        User user004 = new User("user04@email.com", "Usuario", "Administrador");
        user004.setPassword("123456");
        user004.setRole(rolesService.getRoles()[1]);
        user004.setMoney(100d);
        User user005 = new User("user05@email.com", "Usuario", "Administrador");
        user005.setPassword("123456");
        user005.setRole(rolesService.getRoles()[1]);
        user005.setMoney(100d);
        User user006 = new User("user06@email.com", "Usuario", "Administrador");
        user006.setPassword("123456");
        user006.setRole(rolesService.getRoles()[1]);
        user006.setMoney(100d);
        User user007 = new User("user07@email.com", "Usuario", "Administrador");
        user007.setPassword("123456");
        user007.setRole(rolesService.getRoles()[1]);
        user007.setMoney(100d);
        User user008 = new User("user08@email.com", "Usuario", "Administrador");
        user008.setPassword("123456");
        user008.setRole(rolesService.getRoles()[1]);
        user008.setMoney(100d);
        User user009 = new User("user09@email.com", "Usuario", "Administrador");
        user009.setPassword("123456");
        user009.setRole(rolesService.getRoles()[1]);
        user009.setMoney(100d);
        User user010 = new User("user10@email.com", "Usuario", "Administrador");
        user010.setPassword("123456");
        user010.setRole(rolesService.getRoles()[1]);
        user010.setMoney(100d);
        User user011 = new User("user11@email.com", "Usuario", "Administrador");
        user011.setPassword("123456");
        user011.setRole(rolesService.getRoles()[1]);
        user011.setMoney(100d);
        User user012 = new User("user12@email.com", "Usuario", "Administrador");
        user012.setPassword("123456");
        user012.setRole(rolesService.getRoles()[1]);
        user012.setMoney(100d);
        User user013 = new User("user13@email.com", "Usuario", "Administrador");
        user013.setPassword("123456");
        user013.setRole(rolesService.getRoles()[1]);
        user013.setMoney(100d);
        User user014 = new User("user14@email.com", "Usuario", "Administrador");
        user014.setPassword("123456");
        user014.setRole(rolesService.getRoles()[1]);
        user014.setMoney(100d);
        User user015 = new User("user15@email.com", "Usuario", "Administrador");
        user015.setPassword("123456");
        user015.setRole(rolesService.getRoles()[1]);
        user015.setMoney(100d);




        Set user1Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta A1", "Detalles A1", new Date(), 40, user1));
                add(new Offer("Oferta A2", "Detalles A2", new Date(), 80, user1));
                add(new Offer("Oferta A3", "Detalles A3", new Date(), 10, user1));
                add(new Offer("Oferta A4", "Detalles A4", new Date(), 100, user1));
            }
        };
        user1.setOffers(user1Offers);

        Set user2Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta B1", "Detalles B1", new Date(), 50, user2));
                add(new Offer("Oferta B2", "Detalles B2", new Date(), 70, user2));
                add(new Offer("Oferta B3", "Detalles B3", new Date(), 15, user2));
                add(new Offer("Oferta B4", "Detalles B4", new Date(), 300, user2));
            }
        };
        user2.setOffers(user2Offers);
        Set user3Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta C1", "Detalles C1", new Date(), 100, user3));
                add(new Offer("Oferta C2", "Detalles C2", new Date(), 200, user3));
                add(new Offer("Oferta C3", "Detalles C3", new Date(), 300, user3));
                add(new Offer("Oferta C4", "Detalles C4", new Date(), 400, user3));
            }
        };
        user3.setOffers(user3Offers);
        Set user4Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta D1", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta D2", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta D3", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta D4", "Detalles D4", new Date(), 40, user4));
            }
        };
        user4.setOffers(user4Offers);
        Set user01Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 001", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 002", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 003", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 004", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 005", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 006", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 007", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 008", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 009", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 010", "Detalles D2", new Date(), 20, user4));

            }
        };
        user001.setOffers(user01Offers);

        Set user02Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 011", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 023", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 013", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 014", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 015", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 016", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 017", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 018", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 019", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 020", "Detalles D2", new Date(), 20, user4));

            }
        };
        user002.setOffers(user02Offers);

        Set user03Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 021", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 022", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 023", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 024", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 025", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 026", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 027", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 028", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 029", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 030", "Detalles D2", new Date(), 20, user4));

            }
        };
        user003.setOffers(user03Offers);

        Set user04Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 041", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 042", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 043", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 044", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 045", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 046", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 047", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 048", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 049", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 050", "Detalles D2", new Date(), 20, user4));

            }
        };
        user004.setOffers(user04Offers);

        Set user05Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 051", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 052", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 053", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 054", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 055", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 056", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 057", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 058", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 059", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 060", "Detalles D2", new Date(), 20, user4));

            }
        };
        user005.setOffers(user05Offers);

        Set user06Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 061", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 062", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 063", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 064", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 065", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 066", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 067", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 068", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 069", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 070", "Detalles D2", new Date(), 20, user4));

            }
        };
        user006.setOffers(user06Offers);

        Set user07Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 071", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 072", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 073", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 074", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 075", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 076", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 077", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 078", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 079", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 080", "Detalles D2", new Date(), 20, user4));

            }
        };
        user007.setOffers(user07Offers);

        Set user08Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 081", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 082", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 083", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 084", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 085", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 086", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 087", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 088", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 089", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 090", "Detalles D2", new Date(), 20, user4));

            }
        };
        user008.setOffers(user08Offers);

        Set user09Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 091", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 092", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 093", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 094", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 095", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 096", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 097", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 098", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 099", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 100", "Detalles D2", new Date(), 20, user4));

            }
        };
        user009.setOffers(user09Offers);

        Set user10Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 101", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 102", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 103", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 104", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 105", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 106", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 107", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 108", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 109", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 110", "Detalles D2", new Date(), 20, user4));

            }
        };
        user010.setOffers(user10Offers);

        Set user11Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 111", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 112", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 113", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 114", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 115", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 116", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 117", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 118", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 119", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 120", "Detalles D2", new Date(), 20, user4));

            }
        };
        user011.setOffers(user11Offers);

        Set user12Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 121", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 122", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 123", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 124", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 125", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 126", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 127", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 128", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 129", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 130", "Detalles D2", new Date(), 20, user4));

            }
        };
        user012.setOffers(user12Offers);

        Set user13Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 131", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 132", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 133", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 134", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 135", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 136", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 137", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 138", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 139", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 140", "Detalles D2", new Date(), 20, user4));

            }
        };
        user013.setOffers(user13Offers);

        Set user14Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 141", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 142", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 143", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 144", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 145", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 146", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 147", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 148", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 149", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 150", "Detalles D2", new Date(), 20, user4));

            }
        };
        user014.setOffers(user14Offers);

        Set user15Offers = new HashSet<Offer>() {
            {
                add(new Offer("Oferta 151", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 152", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 153", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 154", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 155", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 156", "Detalles D2", new Date(), 20, user4));
                add(new Offer("Oferta 157", "Detalles D3", new Date(), 30, user4));
                add(new Offer("Oferta 158", "Detalles D4", new Date(), 40, user4));
                add(new Offer("Oferta 159", "Detalles D1", new Date(), 10, user4));
                add(new Offer("Oferta 160", "Detalles D2", new Date(), 20, user4));

            }
        };
        user015.setOffers(user15Offers);
        usersService.addUser(user1);
        usersService.addUser(user10);
        usersService.addUser(user11);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user111);
        usersService.addUser(user5);
        usersService.addUser(user6);
        usersService.addUser(user001);
        usersService.addUser(user002);
        usersService.addUser(user003);
        usersService.addUser(user004);
        usersService.addUser(user005);
        usersService.addUser(user006);
        usersService.addUser(user007);
        usersService.addUser(user008);
        usersService.addUser(user009);
        usersService.addUser(user010);
        usersService.addUser(user011);
        usersService.addUser(user012);
        usersService.addUser(user013);
        usersService.addUser(user014);
        usersService.addUser(user015);
    }
}