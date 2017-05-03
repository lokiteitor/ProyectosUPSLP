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
package mx.edu.upslp.callserver.movimiento;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;
import mx.edu.upslp.callserver.usuario.UsuarioEJB;

/**
 *
 * @author David Delgado Hernandez
 * Entity Bean que representa la tabla Movimiento en la bade de datos
 */
@Entity
@Table(name="MOVIMIENTO")
public class MovimientoEJB implements Serializable {

    private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_MOVIMIENTO")
    private Long idMovimiento;
    @Column(name="TIPO")
    private String Tipo;
    @Column(name="created_at")
    private Date created_at;
    @Column(name="updated_at")
    private Date updated_at;
    
    @ManyToOne(targetEntity = IncidenciaEJB.class)
    @JoinColumn(name = "ID_INCIDENCIA",referencedColumnName = "ID_INCIDENCIA")
    private IncidenciaEJB incidencia;
    @ManyToOne(targetEntity = UsuarioEJB.class)
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "CORREO")
    private UsuarioEJB usuario;
    

    /**
     * @return the idMovimiento
     */
    public Long getIdMovimiento() {
        return idMovimiento;
    }

    /**
     * @param idMovimiento the idMovimiento to set
     */
    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }


    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the created_at
     */
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
    public Date getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
             
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento!= null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoEJB)) {
            return false;
        }
        MovimientoEJB other = (MovimientoEJB) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || 
                (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.upslp.callserver.movimiento.MovimientoEJB[ id=" + idMovimiento + " ]";
    }

    /**
     * @return the incidencia
     */
    public IncidenciaEJB getIncidencia() {
        return incidencia;
    }

    /**
     * @param incidencia the incidencia to set
     */
    public void setIncidencia(IncidenciaEJB incidencia) {
        this.incidencia = incidencia;
    }

    /**
     * @return the usuario
     */
    public UsuarioEJB getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEJB usuario) {
        this.usuario = usuario;
    }
    
}
