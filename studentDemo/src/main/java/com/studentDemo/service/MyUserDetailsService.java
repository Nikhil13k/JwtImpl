package com.studentDemo.service;

import com.studentDemo.model.Admin;
import com.studentDemo.model.UserPrinciple;
import com.studentDemo.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin adminUser = adminRepository.findByUsername(username);
        if(adminUser == null){
            throw new UsernameNotFoundException("Admin not found");
        }
        return new UserPrinciple(adminUser);
    }
}
