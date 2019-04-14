package com.rentacar.restapi.api.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rentacar.restapi.api.entity.User;
import com.rentacar.restapi.api.repository.UserRepository;
import com.rentacar.restapi.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findById(String id) {
		return this.userRepository.findOneById(id);
	}

	@Override
	public void delete(String id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		return this.userRepository.findAll(PageRequest.of(page, count));
	}

	@Override
	public Set<User> findByIdIn(List<String> ids) {
		return this.userRepository.findByIdIn(ids);
	}

}
