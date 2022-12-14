package murraco.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private Integer id;

	private String userName;

	private String password;

	private String email;

	private List<AppUserRole> appUserRoles;

}
