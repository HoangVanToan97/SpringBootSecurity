package vn.hust.security.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hust.security.entity.UserEntity;
import vn.hust.security.service.UserService;

@RestController
@RequestMapping("api/v1/web")
public class UserApi {
	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public void addUser(@RequestBody UserEntity userEntity) {
		userEntity.setRole("ROLE_USER");
		userEntity.setPassword(BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));
		userEntity.setEnabled(1);
		userService.addUser(userEntity);
	}

	@PostMapping("/search")
	public Optional<UserEntity> searchById(@RequestBody UserEntity userEntity) {
		return userService.searchById(userEntity.getId());
	}

}
