package com.integrador.hemoSoft.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
public class User implements UserDetails {

	public User() {
		this.dataCriacao = Calendar.getInstance().getTime();
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	@Size(min = 4, message = "USERNAME dev ter pelo menos 4 letras")
	private String username;

	@NotNull
	@NotEmpty(message = "Password não pode ser vazio.")
	@Size(min = 6, message = "Password deve ser no mínimo 6 caracter.")
	private String password;

	@NotNull
	@NotEmpty(message = "E-mail não pode ser vazio")
	@Email(message = "E-mail inválido")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcesso;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getRoleString() {
		String txt="";
		if(role!=null) {
			for(Role r: role) {
				txt+= r.getNome() + ", "; 
			}
		}
		return txt;
	}

}
