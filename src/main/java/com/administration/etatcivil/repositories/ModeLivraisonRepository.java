package com.administration.etatcivil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.administration.etatcivil.entities.ModeLivraison;
import com.administration.etatcivil.entities.ModePaiements;

public interface ModeLivraisonRepository extends JpaRepository<ModeLivraison, Long> {

	@Query("SELECT b FROM ModeLivraison b WHERE b.mode = ?1")
	Optional<ModeLivraison> findByMode(String mode);
	
	//Optional<ModeLivraison> findByNumero(String numero);
	 // @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2") 
	 //@Query("SELECT b FROM Bien b WHERE b.bien.id = ?1")
	 //List<Arrondissements> findByTypeBien(Integer type_bien_id);
}
