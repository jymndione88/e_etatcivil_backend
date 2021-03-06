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

import com.administration.etatcivil.entities.EtatCivils;
import com.administration.etatcivil.entities.TypeEtatcivil;
import com.administration.etatcivil.repositories.EtatCivilRepository;


//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping(value= "/api")
@RestController
public class EtatCivilController {

	@Autowired
	private EtatCivilRepository metier;
	                      
    @RequestMapping(value= "/etatcivil", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
	public ResponseEntity<List<EtatCivils>> getListEtatCivil(){
    	List<EtatCivils> con= metier.findAll();	

    	if (con == null || con.isEmpty()){
    		//erreur 204
            return new ResponseEntity<List<EtatCivils>>(HttpStatus.NOT_FOUND);
        }else{

        	return new ResponseEntity<List<EtatCivils>>(con, HttpStatus.OK);
        }

	}
	
    @RequestMapping(value= "/etatcivil", method= RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<?>  addEtatCivil(@RequestBody EtatCivils con){

    	//Si l'con n'existe pas déja
    	if(metier.findByCode(con.getCode())!= null){
    		 metier.save(con);
    		 return new ResponseEntity<>(con, HttpStatus.CREATED);
    	}else {
    		
    		return new ResponseEntity<>("EtatCivil existe déjà", HttpStatus.CONFLICT);
    	}
    	
	}
    
    @RequestMapping(value= "/etatcivil/{id}", method= RequestMethod.PUT)
    @ResponseBody
	public ResponseEntity<?> updateEtatCivil(@PathVariable("id") Long id, @RequestBody EtatCivils con){

Optional<EtatCivils> optionalart = metier.findById(id);
    	
        if (optionalart== null){
          	 return new ResponseEntity<>("EtatCivil non trouvé", HttpStatus.NOT_FOUND);
        	
        }else { 

        	EtatCivils art = optionalart.get();
        	art.setCode(con.getCode());
        	art.setLibelle(con.getLibelle());
        	art.setIdCommune(con.getIdCommune());
        	art.setIdTypeEtatCivil(con.getIdTypeEtatCivil());
        	
        	 metier.save(art);
        	 return new ResponseEntity<>(art, HttpStatus.OK);
        	
		}

	}
    
    @RequestMapping(value= "/etatcivil/{id}", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
    public ResponseEntity<?> getEtatCivilById(@PathVariable("id") Long id) {

    	Optional<EtatCivils> optionalart = metier.findById(id);

    	if (optionalart== null){
    		return new ResponseEntity<>("EtatCivil non trouvé", HttpStatus.NOT_FOUND);
            
    	}else { 
    		return new ResponseEntity<>(optionalart, HttpStatus.OK);
		}
    }
    
    @RequestMapping(value= "/etatcivil/{id}", method= RequestMethod.DELETE)
    @ResponseBody
   	public ResponseEntity<Void> deleteEtatCivil(@PathVariable("id") Long id){


    	Optional<EtatCivils> optionalart  = metier.findById(id);

        if (optionalart== null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{

        	EtatCivils art = optionalart.get();
    		metier.delete(art);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

   	}
    
}
