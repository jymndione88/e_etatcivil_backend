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

import com.administration.etatcivil.entities.TypeEtatcivil;
import com.administration.etatcivil.repositories.TypeEtatCivilRepository;

@CrossOrigin("http://localhost:4200")
@RequestMapping(value= "/api")
@RestController
public class TypeEtatcivilController {

	@Autowired
	private TypeEtatCivilRepository metier;
	                      
    @RequestMapping(value= "/typeetatcivil", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
	public ResponseEntity<List<TypeEtatcivil>> getListTypeEtatcivil(){
    	List<TypeEtatcivil> typeetatcivil= metier.findAll();	

    	if (typeetatcivil == null || typeetatcivil.isEmpty()){
    		//erreur 204
            return new ResponseEntity<List<TypeEtatcivil>>(HttpStatus.NO_CONTENT);
        }else{

        	return new ResponseEntity<List<TypeEtatcivil>>(typeetatcivil, HttpStatus.OK);
        }

	}
	
    @RequestMapping(value= "/typeetatcivil", method= RequestMethod.POST)
    @ResponseBody
	public TypeEtatcivil addTypeEtatcivil(@RequestBody TypeEtatcivil typeetatcivil, HttpServletResponse response){

    	//Si l'typeetatcivil n'existe pas déja
    	//if(!metier.findByNumero(typeetatcivil.getType()).isPresent()){
    		//return metier.save(typeetatcivil);
    	//}else {
    		//throw new ResponseStatusException(HttpStatus.FORBIDDEN, "typeetatcivil existe déjà");
    	//}
    	
    	return null;
       
	}
    
    @RequestMapping(value= "/typeetatcivil/{id}", method= RequestMethod.PUT)
    @ResponseBody
	public TypeEtatcivil updateTypeEtatcivil(@PathVariable("id") Long id, @RequestBody TypeEtatcivil typeetatcivil){

Optional<TypeEtatcivil> optionalart = metier.findById(id);
    	
        if (optionalart.isPresent()){
        	
        	TypeEtatcivil art = optionalart.get();
        	art.setType(typeetatcivil.getType());
        	return metier.save(art);
        	
        }else { 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "typeetatcivil non trouvé");
		}
      
	}
    
    @RequestMapping(value= "/typeetatcivil/{id}", method= RequestMethod.GET,
    		headers={"Accept=application/json"})
    @ResponseBody
    public TypeEtatcivil getTypeEtatcivilById(@PathVariable("id") Long id) {

    	Optional<TypeEtatcivil> optionalart = metier.findById(id);

    	if (optionalart.isPresent()){
            
    		TypeEtatcivil art = optionalart.get();
    		return art;
    	}else { 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "typeetatcivil non trouvé");
		}
    	
    }
    
    @RequestMapping(value= "/typeetatcivil/{id}", method= RequestMethod.DELETE)
    @ResponseBody
   	public ResponseEntity<Void> deleteRoleOfficier(@PathVariable("id") Long id){
    	
    	Optional<TypeEtatcivil> optionalart  = metier.findById(id);

        if (!optionalart.isPresent()){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{

        	TypeEtatcivil art = optionalart.get();
    		metier.delete(art);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

   	}
    
}