package com.manipal.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;


import com.manipal.model.User;

//@FeignClient(name="user-service")
@FeignClient(name="user-service", url="localhost:8600")
public interface UserProxy {
	
	@GetMapping("user-service/showalluser")
	public List<User> showAllUser();

	@GetMapping("user-service/showuser/{userId}")
	public User getUserById(@PathVariable int userId);

	@DeleteMapping("user-service/deleteuser/{userId}")
	public String deleteUser(@PathVariable int userId);
	
	@DeleteMapping("user-service/deleteans/{qBankId}")
	public void deleteAnsAndScore(@PathVariable int qBankId);

}
