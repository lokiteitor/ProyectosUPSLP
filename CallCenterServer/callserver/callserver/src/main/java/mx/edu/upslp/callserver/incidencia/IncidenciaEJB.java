/*
 * The MIT License
 *
 * Copyright 2017 David Delgado Hernandez 150205@upslp.edu.mx.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mx.edu.upslp.callserver.incidencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import mx.edu.upslp.callserver.cliente.ClienteEJB;
import mx.edu.upslp.callserver.movimiento.MovimientoEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez 150205@upslp.edu.mx Programacion III Miercoles Horario: 2:00 - 4:00
 */
@Entity
@Table(name = "INCIDENCIA")
public class IncidenciaEJB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long idIncidencia;
    
    private String tipo;
    private String importancia;
    private String descripcion;

    private Date fecha;
    private Date created_at;
    private Date updated_at;    
    
    private  ClienteEJB cliente;   
    
    private  UsuarioEJB idUsuario;
    
    private Collection<MovimientoEJB> movimientos;

    /**
     * @return the movimientos
     */
    @OneToMany(targetEntity = MovimientoEJB.class,mappedBy = "incidencia")
    public Collection<MovimientoEJB> getMovimientos() {
        return movimientos;
    }

    /**
     * @param movimientos the movimientos to set
     */
    public void setMovimientos(Collection<MovimientoEJB> movimientos) {
        this.movimientos = movimientos;
    }
    
    /**
     * @return the idUsuario
     */
    @ManyToOne(targetEntity = UsuarioEJB.class)
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "CORREO")
    public UsuarioEJB getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(UsuarioEJB idUsuario) {
        this.idUsuario = idUsuario;
    }    

    /**
     * @return the cliente
     */
    @ManyToOne(targetEntity = ClienteEJB.class)
    @JoinColumn(name = "ID_CLIENTE",referencedColumnName = "CORREO")
    public ClienteEJB getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEJB cliente) {
        this.cliente = cliente;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidencia != null ? idIncidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncidenciaEJB)) {
            return false;
        }
        IncidenciaEJB other = (IncidenciaEJB) object;
        if ((this.idIncidencia == null && other.idIncidencia != null) || (this.idIncidencia != null && !this.idIncidencia.equals(other.idIncidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.upslp.callserver.incidencia.IncidenciaEJB[ id=" + idIncidencia + " ]";
    }

    /**
     * @return the idIncidencia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_INCIDENCIA")
    public Long getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * @param idIncidencia the idIncidencia to set
     */
    public void setIdIncidencia(Long idIncidencia) {
        this.idIncidencia = idIncidencia;
    }



    /**
     * @return the tipo
     */
    @Column(name="TIPO")
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the importancia
     */
    @Column(name="IMPORTANCIA")
    public String getImportancia() {
        return importancia;
    }

    /**
     * @param importancia the importancia to set
     */
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    /**
     * @return the descripcion
     */
    @Column(name="DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    /**
     * @return the fecha
     */
    @Column(name="FECHA")
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the created_at
     */
    @Column(name="created_at")
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the updated_at
     */
    @Column(name="updated_at")
    public Date getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}
