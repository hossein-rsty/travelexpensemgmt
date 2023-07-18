package com.travelexpensemgmt.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param user Object to be saved in DB
     * @return the saved Object
     */
    public User saveUser(User user){
        final UserDbModel savedUser = userRepository.save(createUserDbModel(user));
        return createUser(savedUser);
    }

    /**
     *
     * @return a List of all User Objects existing in DB
     */
    public List<User> getUsers(){
        List<UserDbModel> users = userRepository.findAll();
        return users
                .stream()
                .map(this::createUser)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param userId The id of the user to be returned
     * @return A User with the given ID
     */
    public User getUserById(String userId) {
        final UserDbModel userDbModel = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found by id: " + userId));
        return createUser(userDbModel);
    }
    /**
     * Delete a User by Id
     *
     * @param userId the unique Id of a User which is requiered for delete
     * @return the deleted User
     */
    public User deleteUserById(String userId){
        try {
            final User user = getUserById(userId);
            userRepository.deleteById(userId);
            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException("Delete failed with error: " + e.getMessage());
        }
    }

    /**
     *
     * @param user existing user object to be edited
     * @return the updated User Object
     */
    public User updateById(User user){
        if(userRepository.existsById(user.getUserId())){
            final UserDbModel updatedUser = userRepository.save(editUserDbModel(user));
            return createUser(updatedUser);
        }else{
            throw new IllegalArgumentException("User not found by id: " + user.getUserId());
        }
    }
    /**
     * INTERNAL
     * @param user to be converted to a DB-Model Object
     * @return the converted object
     */
    private UserDbModel createUserDbModel(User user) {
        return UserDbModel.builder()
                .id(null)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .build();
    }

    /**
     * INTERNAL
     * @param user to be converted to a DB-Model Object for editing purposes (The ID will be given too)
     * @return the converted object
     */
    private UserDbModel editUserDbModel(User user) {
        return UserDbModel.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .build();
    }

    /**
     * INTERNAL
     * @param userDbModel to be converted to a Task Object
     * @return the converted object
     */
    private User createUser(UserDbModel userDbModel) {
        return User.builder()
                .userId(userDbModel.getId())
                .firstName(userDbModel.getFirstName())
                .lastName(userDbModel.getLastName())
                .mail(userDbModel.getMail())
                .build();
    }

}
