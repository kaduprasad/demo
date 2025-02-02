package spring.demo.graphQL.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.demo.entity.User;
import spring.demo.repo.UserRepo;

import java.util.Optional;

@Controller()
public class GraphqlController{

    @Autowired()
    private UserRepo userRepo;

    @QueryMapping
    public Iterable<User> getAllUsers(){
        System.out.println("Inside getAllUsers");
        Iterable<User> users = userRepo.findAll();

        return users;
    }

    @QueryMapping
    public Optional<User> getUserById(@Argument Integer id){
        System.out.println("id : "+id);
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return user;
    }

    @MutationMapping
    public User createUser(@Argument String name,  @Argument String email){

        System.out.println("Input "+name+email);
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        try {
            user = userRepo.save(user);
            System.out.println("User Saved: " + user);
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User could not be saved", e);
        }

        System.out.println("User Saved");

        return user;
    }

    @MutationMapping
    public User UpdateUser(@Argument Integer id, @Argument String name,  @Argument String email){

        System.out.println("Input "+id+name+email);
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) {
            // User not found, throw exception
            throw new Error("User not found");
        }

        User user = optionalUser.get();

        // refactor this to use better approach 1. builder 2. reflection
        if (name != null) {
            user.setName(name);
        }
        if (email != null) {
            user.setEmail(email);
        }

        userRepo.save(user);
        System.out.println("User Saved");

        return user;
    }

    @MutationMapping
    public Boolean deleteUserById(@Argument Integer id){
        System.out.println("deleting user with Id: " + id);

        // Check if the user exists
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) {
            return false;
        }

        userRepo.deleteById(id);
        System.out.println("User deleted with Id: "+id);
        return true;
    }
}

