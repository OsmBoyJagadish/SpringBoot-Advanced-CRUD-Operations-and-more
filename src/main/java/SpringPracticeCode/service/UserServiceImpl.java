package SpringPracticeCode.service;

import SpringPracticeCode.exception.UserNotFoundException;
import SpringPracticeCode.request.UserRequest;
import SpringPracticeCode.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import SpringPracticeCode.entity.User;
import SpringPracticeCode.repository.UserRepository;
import java.util.List;

import static SpringPracticeCode.constant.GeneralMsgConstant.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;

	@Override
	public ApiResponse<User> createUser(UserRequest userRequest) {
		logger.info("UserController - Inside createUser method");
		User user = User.builder()
				.name(userRequest.getName())
				.city(userRequest.getCity())
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.build();

		User savedUser = userRepository.save(user);

		return ApiResponse.<User>builder()
				.message(USER_CREATED_SUCCESSFULLY)
				.status(HttpStatus.CREATED)
				.data(savedUser)
				.build();
	}

	@Override
	public ApiResponse<List<User>> getAllUsers() {
		logger.info("UserController - Inside getAllUsers method");
		List<User> users = userRepository.findAll();
		return ApiResponse.<List<User>>builder()
				.message(USER_FETCHED_SUCCESSFULLY)
				.status(HttpStatus.OK)
				.data(users)
				.build();
	}

	@Cacheable("userCache")
	@Override
	public ApiResponse<User> getUserById(int id) {
		logger.info("UserController - Inside getUserById method");
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :" + id));
		return ApiResponse.<User>builder()
				.message(USER_FETCHED_SUCCESSFULLY)
				.status(HttpStatus.OK)
				.data(user)
				.build();
	}

	@Override
	public ApiResponse<User> updateUserById(int id, UserRequest userRequest) {
		logger.info("UserController - Inside updateUserById method");
		User existingUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id : " + id));
		existingUser.setEmail(userRequest.getEmail());
		existingUser.setCity(userRequest.getCity());
		existingUser.setName(userRequest.getName());
		existingUser.setPassword(userRequest.getPassword());
		User updatedUser = userRepository.save(existingUser);
		return ApiResponse.<User>builder()
				.message(USER_UPDATED_SUCCESSFULLY)
				.status(HttpStatus.OK)
				.data(updatedUser)
				.build();
	}

	@Override
	public ApiResponse<String> deleteUserById(int id) {
		logger.info("UserController - Inside deleteUserById method");
		User existingUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id : " + id));
		userRepository.delete(existingUser);
		return ApiResponse.<String>builder()
				.message(USER_DELETED_SUCCESSFULLY)
				.status(HttpStatus.OK)
				.data("Deleted")
				.build();
	}

}
