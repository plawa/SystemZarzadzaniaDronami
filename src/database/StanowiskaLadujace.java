/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Piotrek
 */
@Entity
@Table(name = "stanowiska_ladujace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StanowiskaLadujace.findAll", query = "SELECT s FROM StanowiskaLadujace s"),
    @NamedQuery(name = "StanowiskaLadujace.findById", query = "SELECT s FROM StanowiskaLadujace s WHERE s.id = :id"),
    @NamedQuery(name = "StanowiskaLadujace.findByCzyZajete", query = "SELECT s FROM StanowiskaLadujace s WHERE s.czyZajete = :czyZajete")})
public class StanowiskaLadujace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "czy_zajete")
    private Short czyZajete;
    @JoinColumn(name = "ID_stacji", referencedColumnName = "ID")
    @ManyToOne
    private Stacja iDstacji;

    public StanowiskaLadujace() {
    }

    public StanowiskaLadujace(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCzyZajete() {
        return czyZajete;
    }

    public void setCzyZajete(Short czyZajete) {
        this.czyZajete = czyZajete;
    }

    public Stacja getIDstacji() {
        return iDstacji;
    }

    public void setIDstacji(Stacja iDstacji) {
        this.iDstacji = iDstacji;
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
        if (!(object instanceof StanowiskaLadujace)) {
            return false;
        }
        StanowiskaLadujace other = (StanowiskaLadujace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.StanowiskaLadujace[ id=" + id + " ]";
    }
    
}
