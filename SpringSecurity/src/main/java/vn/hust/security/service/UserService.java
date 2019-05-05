package vn.hust.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hust.security.entity.UserEntity;
import vn.hust.security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void addUser(UserEntity userEntity) {
		userRepository.save(userEntity);
	}
	
	public Optional<UserEntity> searchById(Long id) {
		return userRepository.findById(id);
	}

}
