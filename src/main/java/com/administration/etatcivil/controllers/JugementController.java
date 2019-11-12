package com.administration.etatcivil.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.administration.etatcivil.entities.Jugements;
import com.administration.etatcivil.entities.TypeEtatcivil;
import com.administration.etatcivil.repositories.JugementRepository;

@CrossOrigin("http://localhost:4200")
@RequestMapping(value= "/api")
@RestController
public class JugementController {

	@Autowired
	private JugementRepository metier;
	                      
    @RequestMapping(value= "/jugement", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
	public ResponseEntity<List<Jugements>> getListJugement(){
    	List<Jugements> con= metier.findAll();	

    	if (con == null || con.isEmpty()){
    		//erreur 204
            return new ResponseEntity<List<Jugements>>(HttpStatus.NO_CONTENT);
        }else{

        	return new ResponseEntity<List<Jugements>>(con, HttpStatus.OK);
        }

	}
	
    @RequestMapping(value= "/jugement", method= RequestMethod.POST)
    @ResponseBody
	public Jugements addJugement(@RequestBody Jugements con, HttpServletResponse response){

    	//Si l'con n'existe pas déja
    	//if(!metier.findByNumero(con.getNumero()).isPresent()){
    		//return metier.save(con);
    	//}else {
    		//throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Jugement existe déjà");
    	//}
    	return null;
    	
	}
    
    @RequestMapping(value= "/jugement/{id}", method= RequestMethod.PUT)
    @ResponseBody
	public Jugements updateJugement(@PathVariable("id") Long id, @RequestBody Jugements con){

Optional<Jugements> optionalart = metier.findById(id);
    	
        if (optionalart.isPresent()){
        	
        	Jugements art = optionalart.get();
        	art.setDate(con.getDate());
        	return metier.save(art);
        	
        }else { 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Jugement non trouvé");
		}

	}
    
    @RequestMapping(value= "/jugement/{id}", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
    public Jugements getJugementById(@PathVariable("id") Long id) {

    	Optional<Jugements> optionalart = metier.findById(id);

    	if (optionalart.isPresent()){
            
    		Jugements art = optionalart.get();
    		return art;
    	}else { 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Jugement non trouvé");
		}

    }
    
    @RequestMapping(value= "/jugement/{id}", method= RequestMethod.DELETE)
    @ResponseBody
   	public ResponseEntity<Void> deleteJugement(@PathVariable("id") Long id){

    	Optional<Jugements> optionalart  = metier.findById(id);

        if (!optionalart.isPresent()){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{

        	Jugements art = optionalart.get();
    		metier.delete(art);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
   	}
    
}
