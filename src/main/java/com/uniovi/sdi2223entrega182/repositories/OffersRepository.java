package com.uniovi.sdi2223entrega182.repositories;

import com.uniovi.sdi2223entrega182.entities.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OffersRepository extends CrudRepository<Offer, Long> {
    @Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1))")
    Page<Offer> searchByNameAndLastName(Pageable pageable, String searchText);

    Page<Offer> findAll(Pageable pageable);

}
