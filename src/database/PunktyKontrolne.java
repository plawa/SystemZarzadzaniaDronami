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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Piotrek
 */
@Entity
@Table(name = "punkty_kontrolne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PunktyKontrolne.findAll", query = "SELECT p FROM PunktyKontrolne p"),
    @NamedQuery(name = "PunktyKontrolne.findById", query = "SELECT p FROM PunktyKontrolne p WHERE p.id = :id"),
    @NamedQuery(name = "PunktyKontrolne.findByCzasWyznaczenia", query = "SELECT p FROM PunktyKontrolne p WHERE p.czasWyznaczenia = :czasWyznaczenia"),
    @NamedQuery(name = "PunktyKontrolne.findByLatitude", query = "SELECT p FROM PunktyKontrolne p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "PunktyKontrolne.findByLongitude", query = "SELECT p FROM PunktyKontrolne p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "PunktyKontrolne.findByAttitude", query = "SELECT p FROM PunktyKontrolne p WHERE p.attitude = :attitude"),
    @NamedQuery(name = "PunktyKontrolne.findByVmax", query = "SELECT p FROM PunktyKontrolne p WHERE p.vmax = :vmax"),
    @NamedQuery(name = "PunktyKontrolne.findByCzyOsiagnieto", query = "SELECT p FROM PunktyKontrolne p WHERE p.czyOsiagnieto = :czyOsiagnieto")})
public class PunktyKontrolne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "czas_wyznaczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date czasWyznaczenia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Latitude")
    private Float latitude;
    @Column(name = "Longitude")
    private Float longitude;
    @Column(name = "Attitude")
    private Float attitude;
    @Column(name = "V_max")
    private Float vmax;
    @Column(name = "czy_osiagnieto")
    private Short czyOsiagnieto;
    @JoinColumn(name = "ID_drona", referencedColumnName = "ID")
    @ManyToOne
    private Drony iDdrona;

    public PunktyKontrolne() {
    }

    public PunktyKontrolne(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCzasWyznaczenia() {
        return czasWyznaczenia;
    }

    public void setCzasWyznaczenia(Date czasWyznaczenia) {
        this.czasWyznaczenia = czasWyznaczenia;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getAttitude() {
        return attitude;
    }

    public void setAttitude(Float attitude) {
        this.attitude = attitude;
    }

    public Float getVmax() {
        return vmax;
    }

    public void setVmax(Float vmax) {
        this.vmax = vmax;
    }

    public Short getCzyOsiagnieto() {
        return czyOsiagnieto;
    }

    public void setCzyOsiagnieto(Short czyOsiagnieto) {
        this.czyOsiagnieto = czyOsiagnieto;
    }

    public Drony getIDdrona() {
        return iDdrona;
    }

    public void setIDdrona(Drony iDdrona) {
        this.iDdrona = iDdrona;
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
        if (!(object instanceof PunktyKontrolne)) {
            return false;
        }
        PunktyKontrolne other = (PunktyKontrolne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.PunktyKontrolne[ id=" + id + " ]";
    }
    
}
