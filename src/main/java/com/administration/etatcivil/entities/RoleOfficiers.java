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

/**
 *
 * @author dev1202
 */
@Entity
@Table(name = "role_officiers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleOfficiers.findAll", query = "SELECT r FROM RoleOfficiers r"),
    @NamedQuery(name = "RoleOfficiers.findById", query = "SELECT r FROM RoleOfficiers r WHERE r.id = :id"),
    @NamedQuery(name = "RoleOfficiers.findByRole", query = "SELECT r FROM RoleOfficiers r WHERE r.role = :role")})
public class RoleOfficiers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoleOfficier")
    private List<Officiers> officiersList;

    public RoleOfficiers() {
    }

    public RoleOfficiers(Long id) {
        this.id = id;
    }

    public RoleOfficiers(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public List<Officiers> getOfficiersList() {
        return officiersList;
    }

    public void setOfficiersList(List<Officiers> officiersList) {
        this.officiersList = officiersList;
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
        if (!(object instanceof RoleOfficiers)) {
            return false;
        }
        RoleOfficiers other = (RoleOfficiers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.administration.etatcivil.entities.RoleOfficiers[ id=" + id + " ]";
    }
    
}