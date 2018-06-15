package com.integrador.hemoSoft.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Role;
import com.integrador.hemoSoft.model.User;
import com.integrador.hemoSoft.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByUsername(username);
		
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
		
	}
	
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
		Set<Role> permissoes =  ((User) usuario).getRole();
		for( Role r : permissoes ) {
			 authorities.add( new SimpleGrantedAuthority(r.getNome().toUpperCase() ) );
		}
		
		
		return authorities;
	}


	public UserDetails findByID(Long id) {
		return repository.getOne(id); 
	}
	
	
	public void salvar(User u) {
		repository.save(u);
	}

	public Object findAll() {
		return repository.findAll();
	}

}
