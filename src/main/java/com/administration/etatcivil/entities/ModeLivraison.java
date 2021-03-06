/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administration.etatcivil.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Utilisateur
 */
@Entity
@Table(name = "mode_livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModeLivraison.findAll", query = "SELECT m FROM ModeLivraison m"),
    @NamedQuery(name = "ModeLivraison.findById", query = "SELECT m FROM ModeLivraison m WHERE m.id = :id"),
    @NamedQuery(name = "ModeLivraison.findByMode", query = "SELECT m FROM ModeLivraison m WHERE m.mode = :mode")})
public class ModeLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "mode")
    private String mode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModeLivraison")
    @JsonIgnore
    private List<Livraisons> livraisonsList;

    public ModeLivraison() {
    }

    public ModeLivraison(Long id) {
        this.id = id;
    }

    public ModeLivraison(Long id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @XmlTransient
    public List<Livraisons> getLivraisonsList() {
        return livraisonsList;
    }

    public void setLivraisonsList(List<Livraisons> livraisonsList) {
        this.livraisonsList = livraisonsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModeLivraison)) {
            return false;
        }
        ModeLivraison other = (ModeLivraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.administration.etatcivil.entities.ModeLivraison[ id=" + id + " ]";
    }
    
}
