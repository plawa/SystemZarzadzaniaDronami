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
@Table(name = "poziomy_baterii")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoziomyBaterii.findAll", query = "SELECT p FROM PoziomyBaterii p"),
    @NamedQuery(name = "PoziomyBaterii.findById", query = "SELECT p FROM PoziomyBaterii p WHERE p.id = :id"),
    @NamedQuery(name = "PoziomyBaterii.findByPoziomBaterii", query = "SELECT p FROM PoziomyBaterii p WHERE p.poziomBaterii = :poziomBaterii"),
    @NamedQuery(name = "PoziomyBaterii.findByTimestamp", query = "SELECT p FROM PoziomyBaterii p WHERE p.timestamp = :timestamp")})
public class PoziomyBaterii implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "poziom_baterii")
    private Float poziomBaterii;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "ID_drona", referencedColumnName = "ID")
    @ManyToOne
    private Drony iDdrona;

    public PoziomyBaterii() {
    }

    public PoziomyBaterii(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPoziomBaterii() {
        return poziomBaterii;
    }

    public void setPoziomBaterii(Float poziomBaterii) {
        this.poziomBaterii = poziomBaterii;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof PoziomyBaterii)) {
            return false;
        }
        PoziomyBaterii other = (PoziomyBaterii) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.PoziomyBaterii[ id=" + id + " ]";
    }
    
}
