package com.demirev.service;

import com.demirev.repository.BusinessUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demirev.model.BusinessUser;

/**
 * This class implements the <code>UserDetailsService</code> which is part of spring security
 * @author filip
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private BusinessUserRepository businessUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		BusinessUser businessUser = businessUserRepository.findByEmail(email);
        UserDetails user = new User(email, businessUser.getPassword(), true, true, true, true, 
            AuthorityUtils.createAuthorityList(businessUser.getRole()));
        return user;
	}

}
