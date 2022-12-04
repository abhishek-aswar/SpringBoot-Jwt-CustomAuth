package murraco.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import murraco.model.AppUserRole;
import murraco.model.User;

@Component
public class UserDao {
	
	@Bean
	public List<User> findByUsername() {
		//return listOfUsers().stream().filter(x -> userName.equals(x.getPassword())).findFirst().get();
		return listOfUsers();
	}
	
	private List<User> listOfUsers() {
		List<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setUserName("user-name1");
		user1.setPassword("Password1");
		user1.setEmail("email-1@gmail.com");
		List<AppUserRole> appUserRoles1 = new ArrayList<AppUserRole>();
		appUserRoles1.add(AppUserRole.ROLE_USER);
		user1.setAppUserRoles(appUserRoles1);

		User user2 = new User();
		user2.setUserName("user-name2");
		user2.setPassword("Password1");
		user2.setEmail("email-2@gmail.com");
		List<AppUserRole> appUserRoles2 = new ArrayList<AppUserRole>();
		appUserRoles2.add(AppUserRole.ROLE_ADMIN);
		user2.setAppUserRoles(appUserRoles2);

		User user3 = new User();
		user3.setUserName("user-name3");
		user3.setPassword("Password3");
		user3.setEmail("email-3@gmail.com");
		List<AppUserRole> appUserRoles3 = new ArrayList<AppUserRole>();
		appUserRoles3.add(AppUserRole.ROLE_CLIENT);
		user3.setAppUserRoles(appUserRoles3);

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		return userList;
	}

}
