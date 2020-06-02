package com.itsarraisi.authorizationserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@FrameworkEndpoint
@RequestMapping("/api/oauth")
public class TokenController {

    @Resource
    ConsumerTokenServices tokenServices;

    @DeleteMapping("/revoke")
    public ResponseEntity<Boolean> revokeAccessToken(HttpServletRequest request){
        boolean token = false;
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            token = tokenServices.revokeToken(tokenId);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
