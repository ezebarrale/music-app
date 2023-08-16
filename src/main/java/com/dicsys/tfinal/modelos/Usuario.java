package com.dicsys.tfinal.modelos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint( columnNames = {"username"}), @UniqueConstraint( columnNames = {"email"})})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20)
	private String nombre;
	private String username;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id"))
	private Set<Rol> roles = new HashSet<>();
}
