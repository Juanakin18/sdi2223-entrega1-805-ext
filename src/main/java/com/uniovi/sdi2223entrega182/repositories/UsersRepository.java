package com.uniovi.sdi2223entrega182.repositories;

import com.uniovi.sdi2223entrega182.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.role <> 'ROLE_ADMIN'")
    List<User> findNotAdminUsers();
}
