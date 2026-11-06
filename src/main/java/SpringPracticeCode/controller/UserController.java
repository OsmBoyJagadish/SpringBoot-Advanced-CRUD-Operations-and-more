package SpringPracticeCode.controller;

import SpringPracticeCode.request.UserRequest;
import SpringPracticeCode.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import SpringPracticeCode.entity.User;
import SpringPracticeCode.service.UserService;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "User Related Operations", description = "Perform all the operation of the user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserRequest user) {
		logger.info("UserController - Inside createUser method");
		ApiResponse<User> response = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers (){
		logger.info("UserController - Inside getAllUsers method");
		ApiResponse<List<User>> response =  userService.getAllUsers();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable int id){
		logger.info("UserController - Inside getUserById method");
		ApiResponse<User> response = userService.getUserById(id);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@Operation(summary = "Update user by ID", description = "Updates user details using user ID")
	@PutMapping("/updateUserById/{id}")
	public ResponseEntity<ApiResponse<User>> updateUserById(@Parameter(description = "Provide user ID", example = "1") @PathVariable int id, @RequestBody UserRequest userRequest){
		logger.info("UserController - Inside updateUserById method");
		ApiResponse<User> response = userService.updateUserById(id,userRequest);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<ApiResponse<String>> deleteUserById(@PathVariable int id){
		logger.info("UserController - Inside deleteUserById method");
		ApiResponse<String> response = userService.deleteUserById(id);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}

