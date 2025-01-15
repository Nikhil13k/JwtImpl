package com.studentDemo.service;

import com.studentDemo.model.Admin;
import com.studentDemo.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Service
public class AdminService {


    @Autowired
    private AdminRepository adminRepository;

    public Admin addNew(Admin admin){
        admin.setPassword(new BCryptPasswordEncoder(12)
                .encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

//    public String verify(Admin admin){
//        Authentication authentication = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPassword()));
//        if(authentication.isAuthenticated()){
//            return "Success";
//        }
//        return "failed";
//    }
}
