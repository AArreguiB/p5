package edu.comillas.icai.gitt.pat.spring.p5.entity;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import jakarta.persistence.*;

/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 */
@Entity
@Table(name="APP_USER")

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public Role role;

    @Column(nullable=false)
    public String name;


    public AppUser(Long id, String email, String password, Role role, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public AppUser() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}