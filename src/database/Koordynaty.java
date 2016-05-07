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
@Table(name = "koordynaty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Koordynaty.findAll", query = "SELECT k FROM Koordynaty k"),
    @NamedQuery(name = "Koordynaty.findById", query = "SELECT k FROM Koordynaty k WHERE k.id = :id"),
    @NamedQuery(name = "Koordynaty.findByTimestamp", query = "SELECT k FROM Koordynaty k WHERE k.timestamp = :timestamp"),
    @NamedQuery(name = "Koordynaty.findByFixType", query = "SELECT k FROM Koordynaty k WHERE k.fixType = :fixType"),
    @NamedQuery(name = "Koordynaty.findByLatitude", query = "SELECT k FROM Koordynaty k WHERE k.latitude = :latitude"),
    @NamedQuery(name = "Koordynaty.findByLongitude", query = "SELECT k FROM Koordynaty k WHERE k.longitude = :longitude"),
    @NamedQuery(name = "Koordynaty.findByAttitude", query = "SELECT k FROM Koordynaty k WHERE k.attitude = :attitude"),
    @NamedQuery(name = "Koordynaty.findByVelocity", query = "SELECT k FROM Koordynaty k WHERE k.velocity = :velocity"),
    @NamedQuery(name = "Koordynaty.findByEph", query = "SELECT k FROM Koordynaty k WHERE k.eph = :eph"),
    @NamedQuery(name = "Koordynaty.findByEpv", query = "SELECT k FROM Koordynaty k WHERE k.epv = :epv"),
    @NamedQuery(name = "Koordynaty.findByCog", query = "SELECT k FROM Koordynaty k WHERE k.cog = :cog"),
    @NamedQuery(name = "Koordynaty.findBySatellitesVisible", query = "SELECT k FROM Koordynaty k WHERE k.satellitesVisible = :satellitesVisible")})
public class Koordynaty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "fix_type")
    private Integer fixType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "Attitude")
    private Float attitude;
    @Column(name = "Velocity")
    private Float velocity;
    @Column(name = "eph")
    private Float eph;
    @Column(name = "epv")
    private Float epv;
    @Column(name = "cog")
    private Float cog;
    @Column(name = "satellites_visible")
    private Integer satellitesVisible;
    @JoinColumn(name = "ID_drona", referencedColumnName = "ID")
    @ManyToOne
    private Drony iDdrona;

    public Koordynaty() {
    }

    public Koordynaty(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getFixType() {
        return fixType;
    }

    public void setFixType(Integer fixType) {
        this.fixType = fixType;
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

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public Float getEph() {
        return eph;
    }

    public void setEph(Float eph) {
        this.eph = eph;
    }

    public Float getEpv() {
        return epv;
    }

    public void setEpv(Float epv) {
        this.epv = epv;
    }

    public Float getCog() {
        return cog;
    }

    public void setCog(Float cog) {
        this.cog = cog;
    }

    public Integer getSatellitesVisible() {
        return satellitesVisible;
    }

    public void setSatellitesVisible(Integer satellitesVisible) {
        this.satellitesVisible = satellitesVisible;
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
        if (!(object instanceof Koordynaty)) {
            return false;
        }
        Koordynaty other = (Koordynaty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Koordynaty[ id=" + id + " ]";
    }
    
}
