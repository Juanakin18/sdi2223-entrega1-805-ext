package com.uniovi.sdi2223entrega182.services;

import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Service
public class UsersService {
    public static double INITIAL_MONEY = 100d;
    public static int USER = 1;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UsersRepository usersRepository;
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    @PostConstruct
    public void init() {
    }



    // ------------------ operaciones --------------------

    /**
     * Método que añade un usuario en el sistema
     *
     * @param user que se quiere añadir
     */
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    /**
     * Método que actualzia un usuario en la bbdd
     *
     * @param user que se quiere actualizar
     */
    public void updateUser(User user) {
        usersRepository.save(user);
    }

    /**
     * Método que elimina un usuario buscado por un email suministrado
     *
     * @param email del usuario que se quiere eliminar
     */
    public void deleteUser(String email) {
        User user = usersRepository.findByEmail(email);
        usersRepository.deleteById(user.getId());
    }

    // ------------------ consultas --------------------

    /**
     * Método que devuelve la lista de usuarios del sistema salvo el administrador
     *
     * @return la lista mencionada
     */
    public List<User> getNotAdminUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findNotAdminUsers().forEach(users::add);
        return users;
    }

    /**
     * Método que busca un usuario por id
     *
     * @param id del usuario que se busca
     * @return el usuario
     */
    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    /**
     * Método que busca un usuario por email en el sistema
     *
     * @param email del usuario que se quiere buscar
     * @return el usuario buscado
     */
    public User getUserByEmail(String email) {
        User user = usersRepository.findByEmail(email);
        if (user != null){
            httpSession.setAttribute("money",user.getMoney());
            httpSession.setAttribute("email", user.getEmail());
        }
        return user;
    }
}
