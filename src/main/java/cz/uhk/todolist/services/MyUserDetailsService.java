package cz.uhk.todolist.services;

import cz.uhk.todolist.model.MyUserPrincipal;
import cz.uhk.todolist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //Vrací UserDetails Springbootu, které existují na základě dat Usera z databáze (existuje User v DB? => tady jsou jeho user details!)

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println("User??");
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);   //Pro spring, který s těmito UserDetails nakládá
    }
}
