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
@Table(name = "mode_paiements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModePaiements.findAll", query = "SELECT m FROM ModePaiements m"),
    @NamedQuery(name = "ModePaiements.findById", query = "SELECT m FROM ModePaiements m WHERE m.id = :id"),
    @NamedQuery(name = "ModePaiements.findByMode", query = "SELECT m FROM ModePaiements m WHERE m.mode = :mode")})
public class ModePaiements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "mode")
    private String mode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModePaiement")
    @JsonIgnore
    private List<Paiements> paiementsList;

    public ModePaiements() {
    }

    public ModePaiements(Long id) {
        this.id = id;
    }

    public ModePaiements(Long id, String mode) {
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
    public List<Paiements> getPaiementsList() {
        return paiementsList;
    }

    public void setPaiementsList(List<Paiements> paiementsList) {
        this.paiementsList = paiementsList;
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
        if (!(object instanceof ModePaiements)) {
            return false;
        }
        ModePaiements other = (ModePaiements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.administration.etatcivil.entities.ModePaiements[ id=" + id + " ]";
    }
    
}
