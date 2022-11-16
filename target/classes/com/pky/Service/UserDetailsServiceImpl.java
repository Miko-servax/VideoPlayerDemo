package com.pky.Service;

import com.pky.dao.UserMapper;
import com.pky.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


//设置SpringSecurity用户权限
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userMapper.getUser(username);
        if (user == null){
            return null;
        }else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            int userType = user.getUserType();
            switch (userType){
                case 1:
                    authorities.add(new SimpleGrantedAuthority("ROLE_child"));
                    return new User(user.getUserName(),passwordEncoder.encode(user.getPassword()),authorities);
                case 2:
                    authorities.add(new SimpleGrantedAuthority("ROLE_teenager"));
                    return new User(user.getUserName(),passwordEncoder.encode(user.getPassword()),authorities);
                case 3:
                    authorities.add(new SimpleGrantedAuthority("ROLE_adult"));
                    return new User(user.getUserName(),passwordEncoder.encode(user.getPassword()),authorities);
                default:
                    return null;
            }
        }
    }
}
