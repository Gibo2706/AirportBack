package com.airport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceOauth extends DefaultOAuth2UserService {

	@Autowired
	AuthService as;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		UserDetails ud = null;
		try {
			ud = as.loadUserByUsername((String) user.getAttributes().get("login"));
		} catch (Exception e) {
			as.saveUser(user);
		}
		CustOAuth2User cUser = new CustOAuth2User(as.loadUserByUsernameOAuth((String) user.getAttributes().get("login")), user, ud);
		return cUser;
	}

}
