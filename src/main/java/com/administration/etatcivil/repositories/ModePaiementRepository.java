package com.administration.etatcivil.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.administration.etatcivil.entities.ModePaiements;

public interface ModePaiementRepository extends JpaRepository<ModePaiements, Long> {

	@Query("SELECT b FROM ModePaiements b WHERE b.mode = ?1")
	Optional<ModePaiements> findByMode(String mode);
	
	//Optional<ModePaiements> findByNumero(String numero);
	
	 // @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2") 
	 //@Query("SELECT b FROM Bien b WHERE b.bien.id = ?1")
	 //List<Arrondissements> findByTypeBien(Integer type_bien_id);
}
