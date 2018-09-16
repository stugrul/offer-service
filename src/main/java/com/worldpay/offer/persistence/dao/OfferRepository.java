package com.worldpay.offer.persistence.dao;

import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
