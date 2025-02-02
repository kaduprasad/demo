package spring.demo.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spring.demo.entity.User;
import spring.demo.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
        System.out.println("\n\nCustomUserDetailsService initialized");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("user email"+ email);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email:"+email));
        System.out.println("user"+ user);

        /**
         * withUsername(user.getEmail()) sets the username for the UserDetails.
         * .password(user.getPassword()) sets the password. Ideally, this should be an already encoded password if you are using BCryptPasswordEncoder.
         * .roles("USER") assigns a role to the user, which Spring Security uses for authorization.
         */
        // The User.withUsername method returns a UserBuilder, which allows you to specify the user’s details and build a UserDetails object, ready for Spring Security to use.
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        builder.password(user.getPassword());
        builder.roles("USER");

        UserDetails userDetails = builder.build();
        return userDetails;
    }

}
