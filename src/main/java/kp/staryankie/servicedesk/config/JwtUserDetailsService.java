package kp.staryankie.servicedesk.config;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("manager1".equals(username)) {
            return new User("manager1", "$2a$10$7D.TUdK7PYU1sJGae1SS3.pJ9Av.owSfYmxcsLkLFMHl.6yqlJXCq",
                    new ArrayList<>());
        } else if ("manager2".equals(username)) {
            return new User("manager2", "$2a$10$P8WAepeFGrmjGstH0kFv2umuH7KsOjFQkJi8sUw9MPDEB1WFfu4sa",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}