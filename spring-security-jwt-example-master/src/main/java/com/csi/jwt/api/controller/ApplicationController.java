package com.csi.jwt.api.controller;

import com.csi.jwt.api.model.AuthRequest;
import com.csi.jwt.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to FINTECH CSI PUNE !!";
    }

    @GetMapping("/services")
    public String csiServices()
    {
        return "SOFTWARE DEVELOPMENT SERVICES | BILLING SOFTWARE";
    }

    @GetMapping("/address")
    public String address()
    {
        return "INSPIRIA MALL | PUNE";
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
