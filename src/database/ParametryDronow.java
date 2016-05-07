/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Piotrek
 */
@Entity
@Table(name = "parametry_dronow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametryDronow.findAll", query = "SELECT p FROM ParametryDronow p"),
    @NamedQuery(name = "ParametryDronow.findById", query = "SELECT p FROM ParametryDronow p WHERE p.id = :id"),
    @NamedQuery(name = "ParametryDronow.findByModel", query = "SELECT p FROM ParametryDronow p WHERE p.model = :model"),
    @NamedQuery(name = "ParametryDronow.findByOstatniprzeglad", query = "SELECT p FROM ParametryDronow p WHERE p.ostatniprzeglad = :ostatniprzeglad")})
public class ParametryDronow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Model")
    private String model;
    @Column(name = "Ostatni_przeglad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ostatniprzeglad;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Drony drony;

    public ParametryDronow() {
    }

    public ParametryDronow(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getOstatniprzeglad() {
        return ostatniprzeglad;
    }

    public void setOstatniprzeglad(Date ostatniprzeglad) {
        this.ostatniprzeglad = ostatniprzeglad;
    }

    public Drony getDrony() {
        return drony;
    }

    public void setDrony(Drony drony) {
        this.drony = drony;
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
        if (!(object instanceof ParametryDronow)) {
            return false;
        }
        ParametryDronow other = (ParametryDronow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.ParametryDronow[ id=" + id + " ]";
    }
    
}
