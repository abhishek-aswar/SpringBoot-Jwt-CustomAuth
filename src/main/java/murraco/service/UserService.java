package murraco.service;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import murraco.dao.UserDao;
import murraco.exception.CustomException;
import murraco.model.User;
import murraco.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserDao userDao;

	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public String signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, getUser(username).getAppUserRoles());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public User search(String username) {
		User appUser = getUser(username);
		if (appUser == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return appUser;
	}

	public User whoami(HttpServletRequest req) {
		return getUser(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String username) {
		return jwtTokenProvider.createToken(username, getUser(username).getAppUserRoles());
	}

	private murraco.model.User getUser(String username) {
		return userDao.findByUsername().stream().filter(x -> username.equalsIgnoreCase(x.getUserName())).collect(Collectors.toList()).get(0);
	}

}
