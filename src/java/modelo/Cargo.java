/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodigocargo", query = "SELECT c FROM Cargo c WHERE c.codigocargo = :codigocargo")
    , @NamedQuery(name = "Cargo.findByNombrecargo", query = "SELECT c FROM Cargo c WHERE c.nombrecargo = :nombrecargo")
    , @NamedQuery(name = "Cargo.findByValorhoracargo", query = "SELECT c FROM Cargo c WHERE c.valorhoracargo = :valorhoracargo")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigocargo")
    private Integer codigocargo;
    @Size(max = 50)
    @Column(name = "nombrecargo")
    private String nombrecargo;
    @Column(name = "valorhoracargo")
    private Integer valorhoracargo;
    @OneToMany(mappedBy = "codigocargo")
    private List<Empleado> empleadoList;

    public Cargo() {
    }

    public Cargo(Integer codigocargo) {
        this.codigocargo = codigocargo;
    }

    public Integer getCodigocargo() {
        return codigocargo;
    }

    public void setCodigocargo(Integer codigocargo) {
        this.codigocargo = codigocargo;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public Integer getValorhoracargo() {
        return valorhoracargo;
    }

    public void setValorhoracargo(Integer valorhoracargo) {
        this.valorhoracargo = valorhoracargo;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigocargo != null ? codigocargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codigocargo == null && other.codigocargo != null) || (this.codigocargo != null && !this.codigocargo.equals(other.codigocargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cargo[ codigocargo=" + codigocargo + " ]";
    }
    
}
