package spring.demo.rest;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.demo.repo.UserRepo;
import spring.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/demo")
public class DemoController{

    @Autowired()
    private UserRepo userRepo;


//    @Autowired()
//    @Lazy
//    private BCryptPasswordEncoder passwordEncoder;

//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @PostMapping("/add")
    public @ResponseBody User post(@RequestParam String name, @RequestParam String email, @RequestParam String password){

        User user = new User();
        user.setName(name);
        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);
        userRepo.save(user);

        return user;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> get(){
        Iterable<User> users = userRepo.findAll();

        return users;
    }

    @GetMapping("/user/{id}")
    public @ResponseBody Optional<User> get(@PathVariable Integer id){
        System.out.println("id : "+id);
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return user;
    }

    @PostMapping("/login")
    public @ResponseBody User get(@RequestParam String email, @RequestParam String password){
        System.out.println("user email for login"+email);
        Optional<User> user = userRepo.findByEmail(email);

//        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
//        }
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return user.get();
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id){
        System.out.println("id : " + id);

        // Check if the user exists
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) {
            // User not found, throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        userRepo.deleteById(id);
    }


//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 7, 3, 14, 4, 10);
//        long count  = numbers.stream().count();
//        System.out.println("count"+count);
//
//        List<Integer> numGreaterThan5 = numbers.stream().filter((element) -> element > 5).toList();
//        System.out.println("numGreaterThan5"+numGreaterThan5);
//
//        List<Integer> mapOneElement = numbers.stream().map((element) -> element + 1).toList();
//        System.out.println("mapOneElement"+mapOneElement);
//
//        List<Integer> lastElement = numbers.stream().map((element) -> element + 1).limit(5).sorted().toList();
//        System.out.println("last element"+lastElement);
//
//        Integer mapOneElement2 = numbers.stream().reduce((result, element) -> element + 1).get();
//        System.out.println("mapOneElement2"+mapOneElement2);

}

// TODO:
// 1. Add Auth
// 2. Add Kafka Consumer and Create a another app with publisher
// 3. Add microservices
// 4.