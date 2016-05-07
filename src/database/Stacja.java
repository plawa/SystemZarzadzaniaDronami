/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Piotrek
 */
@Entity
@Table(name = "stacja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stacja.findAll", query = "SELECT s FROM Stacja s"),
    @NamedQuery(name = "Stacja.findById", query = "SELECT s FROM Stacja s WHERE s.id = :id"),
    @NamedQuery(name = "Stacja.findByNazwastacji", query = "SELECT s FROM Stacja s WHERE s.nazwastacji = :nazwastacji"),
    @NamedQuery(name = "Stacja.findByLatitude", query = "SELECT s FROM Stacja s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "Stacja.findByLongitude", query = "SELECT s FROM Stacja s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "Stacja.findByAttitude", query = "SELECT s FROM Stacja s WHERE s.attitude = :attitude"),
    @NamedQuery(name = "Stacja.findByWolnemiejsca", query = "SELECT s FROM Stacja s WHERE s.wolnemiejsca = :wolnemiejsca")})
public class Stacja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nazwa_stacji")
    private String nazwastacji;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Latitude")
    private Float latitude;
    @Column(name = "Longitude")
    private Float longitude;
    @Column(name = "Attitude")
    private Float attitude;
    @Column(name = "Wolne_miejsca")
    private Integer wolnemiejsca;
    @OneToMany(mappedBy = "iDstacji")
    private Collection<Drony> dronyCollection;
    @OneToMany(mappedBy = "iDstacji")
    private Collection<StanowiskaLadujace> stanowiskaLadujaceCollection;

    public Stacja() {
    }

    public Stacja(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwastacji() {
        return nazwastacji;
    }

    public void setNazwastacji(String nazwastacji) {
        this.nazwastacji = nazwastacji;
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

    public Integer getWolnemiejsca() {
        return wolnemiejsca;
    }

    public void setWolnemiejsca(Integer wolnemiejsca) {
        this.wolnemiejsca = wolnemiejsca;
    }

    @XmlTransient
    public Collection<Drony> getDronyCollection() {
        return dronyCollection;
    }

    public void setDronyCollection(Collection<Drony> dronyCollection) {
        this.dronyCollection = dronyCollection;
    }

    @XmlTransient
    public Collection<StanowiskaLadujace> getStanowiskaLadujaceCollection() {
        return stanowiskaLadujaceCollection;
    }

    public void setStanowiskaLadujaceCollection(Collection<StanowiskaLadujace> stanowiskaLadujaceCollection) {
        this.stanowiskaLadujaceCollection = stanowiskaLadujaceCollection;
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
        if (!(object instanceof Stacja)) {
            return false;
        }
        Stacja other = (Stacja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Stacja[ id=" + id + " ]";
    }
    
}
