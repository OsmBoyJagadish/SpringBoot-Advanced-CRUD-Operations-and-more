package SpringPracticeCode.service;

import SpringPracticeCode.entity.User;
import SpringPracticeCode.request.UserRequest;
import SpringPracticeCode.response.ApiResponse;

import java.util.List;

public interface UserService {
	
	public ApiResponse<User> createUser(UserRequest user);
	public ApiResponse<List<User>> getAllUsers();
	public ApiResponse<User> getUserById(int id);
	public ApiResponse<User> updateUserById(int id, UserRequest userRequest);
	public ApiResponse<String> deleteUserById(int id);

}

