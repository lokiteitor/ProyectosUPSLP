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
package mx.edu.upslp.callserver.usuario;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import mx.edu.upslp.callserver.incidencia.IncidenciaEJB;

/**
 *
 * @author David Delgado Hernández
 * Bean de Entidad para la tabla Usuario en la base de datos
 */

// marca la clase como una entidad, la clase debe ser serializable
// tambien marcamos a que table pertenece
@Entity
@Table(name="USUARIO")
public class UsuarioEJB implements Serializable {

    private static final long serialVersionUID = 1L;    
    // marcar este campo como id
    // le decimos que el campo es auto_increment
    // asociamos el nombre de la columna
    private String idUsuario;
    private String nombre;
    private String apellido;
    private Date  fechaNacimiento;
    private String username;
    private String password;
    private String nacionalidad;
    private String turno;
    private boolean administrador;
    private Date createdAt;
    private Date updatedAt;   

    private Collection<IncidenciaEJB> incidencias;

    
    
    @OneToMany(targetEntity = IncidenciaEJB.class,mappedBy = "idUsuario")
    public Collection<IncidenciaEJB> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Collection<IncidenciaEJB> incidencias) {
        this.incidencias = incidencias;
    }
    
    
    
    
    @Id
    @Column(name="CORREO")
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    /**
     * @return the nombre
     */
    @Column(name="NOMBRE")
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    @Column(name="APELLIDO")
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the fechaNacimiento
     */
    @Column(name="FECHA_NACIMIENTO")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the username
     */
    @Column(name="USERNAME")
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nacionalidad
     */
    @Column(name="NACIONALIDAD")
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the turno
     */
    @Column(name="TURNO")
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * @return the administrador
     */
    @Column(name="ADMINISTRADOR")    
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the createdAt
     */
    @Column(name="created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    @Column(name="updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
 
    // implementar los metodos de la interfaz serializable
     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEJB)) {
            return false;
        }
        UsuarioEJB other = (UsuarioEJB) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.upslp.callserver.usuario.UsuarioEJB[ id=" + idUsuario + " ]";
    }    
    
    
}
