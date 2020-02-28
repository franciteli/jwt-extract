package br.com.provider.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.provider.jwt.model.User;
import br.com.provider.jwt.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class JwtController {

    @Autowired
	private UserService userService;

    @GetMapping(value= "/user")
    public ResponseEntity<?> getJwtUser(@RequestHeader("Authorization") String jwt) {
        User userDetail = userService.getUser(jwt);
        return ResponseEntity.ok().body(userDetail);
    }
}