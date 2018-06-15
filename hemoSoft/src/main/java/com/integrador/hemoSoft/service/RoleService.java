package com.integrador.hemoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Role;
import com.integrador.hemoSoft.repository.RoleRepository;


@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}
}
