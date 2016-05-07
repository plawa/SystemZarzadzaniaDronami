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
@Table(name = "logi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logi.findAll", query = "SELECT l FROM Logi l"),
    @NamedQuery(name = "Logi.findById", query = "SELECT l FROM Logi l WHERE l.id = :id"),
    @NamedQuery(name = "Logi.findByTimestamp", query = "SELECT l FROM Logi l WHERE l.timestamp = :timestamp"),
    @NamedQuery(name = "Logi.findByPoziomWiadomosci", query = "SELECT l FROM Logi l WHERE l.poziomWiadomosci = :poziomWiadomosci"),
    @NamedQuery(name = "Logi.findByWiadomosc", query = "SELECT l FROM Logi l WHERE l.wiadomosc = :wiadomosc")})
public class Logi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "poziom_wiadomosci")
    private Integer poziomWiadomosci;
    @Column(name = "Wiadomosc")
    private String wiadomosc;

    public Logi() {
    }

    public Logi(Integer id) {
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

    public Integer getPoziomWiadomosci() {
        return poziomWiadomosci;
    }

    public void setPoziomWiadomosci(Integer poziomWiadomosci) {
        this.poziomWiadomosci = poziomWiadomosci;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
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
        if (!(object instanceof Logi)) {
            return false;
        }
        Logi other = (Logi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Logi[ id=" + id + " ]";
    }
    
}
