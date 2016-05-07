/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Piotrek
 */
@Entity
@Table(name = "drony")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drony.findAll", query = "SELECT d FROM Drony d"),
    @NamedQuery(name = "Drony.findById", query = "SELECT d FROM Drony d WHERE d.id = :id"),
    @NamedQuery(name = "Drony.findByCzyAktywny", query = "SELECT d FROM Drony d WHERE d.czyAktywny = :czyAktywny"),
    @NamedQuery(name = "Drony.findByStanStreaminguVideo", query = "SELECT d FROM Drony d WHERE d.stanStreaminguVideo = :stanStreaminguVideo"),
    @NamedQuery(name = "Drony.findByCzyZadokowany", query = "SELECT d FROM Drony d WHERE d.czyZadokowany = :czyZadokowany"),
    @NamedQuery(name = "Drony.findByPrzebieg", query = "SELECT d FROM Drony d WHERE d.przebieg = :przebieg")})
public class Drony implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "czy_aktywny")
    private Short czyAktywny;
    @Column(name = "stan_streamingu_video")
    private Short stanStreaminguVideo;
    @Column(name = "czy_zadokowany")
    private Short czyZadokowany;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Przebieg")
    private Float przebieg;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "drony")
    private ParametryDronow parametryDronow;
    @OneToMany(mappedBy = "iDdrona")
    private Collection<PoziomyBaterii> poziomyBateriiCollection;
    @JoinColumn(name = "ID_stacji", referencedColumnName = "ID")
    @ManyToOne
    private Stacja iDstacji;
    @OneToMany(mappedBy = "iDdrona")
    private Collection<Koordynaty> koordynatyCollection;
    @OneToMany(mappedBy = "iDdrona")
    private Collection<PunktyKontrolne> punktyKontrolneCollection;

    public Drony() {
    }

    public Drony(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCzyAktywny() {
        return czyAktywny;
    }

    public void setCzyAktywny(Short czyAktywny) {
        this.czyAktywny = czyAktywny;
    }

    public Short getStanStreaminguVideo() {
        return stanStreaminguVideo;
    }

    public void setStanStreaminguVideo(Short stanStreaminguVideo) {
        this.stanStreaminguVideo = stanStreaminguVideo;
    }

    public Short getCzyZadokowany() {
        return czyZadokowany;
    }

    public void setCzyZadokowany(Short czyZadokowany) {
        this.czyZadokowany = czyZadokowany;
    }

    public Float getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(Float przebieg) {
        this.przebieg = przebieg;
    }

    public ParametryDronow getParametryDronow() {
        return parametryDronow;
    }

    public void setParametryDronow(ParametryDronow parametryDronow) {
        this.parametryDronow = parametryDronow;
    }

    @XmlTransient
    public Collection<PoziomyBaterii> getPoziomyBateriiCollection() {
        return poziomyBateriiCollection;
    }

    public void setPoziomyBateriiCollection(Collection<PoziomyBaterii> poziomyBateriiCollection) {
        this.poziomyBateriiCollection = poziomyBateriiCollection;
    }

    public Stacja getIDstacji() {
        return iDstacji;
    }

    public void setIDstacji(Stacja iDstacji) {
        this.iDstacji = iDstacji;
    }

    @XmlTransient
    public Collection<Koordynaty> getKoordynatyCollection() {
        return koordynatyCollection;
    }

    public void setKoordynatyCollection(Collection<Koordynaty> koordynatyCollection) {
        this.koordynatyCollection = koordynatyCollection;
    }

    @XmlTransient
    public Collection<PunktyKontrolne> getPunktyKontrolneCollection() {
        return punktyKontrolneCollection;
    }

    public void setPunktyKontrolneCollection(Collection<PunktyKontrolne> punktyKontrolneCollection) {
        this.punktyKontrolneCollection = punktyKontrolneCollection;
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
        if (!(object instanceof Drony)) {
            return false;
        }
        Drony other = (Drony) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Drony[ id=" + id + " ]";
    }
    
}
