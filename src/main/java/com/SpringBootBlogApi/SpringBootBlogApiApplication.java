package com.SpringBootBlogApi;

import com.SpringBootBlogApi.entity.enums.Gender;
import com.SpringBootBlogApi.entity.enums.RoleType;
import com.SpringBootBlogApi.payload.request.AdminRequest;
import com.SpringBootBlogApi.service.AdminService;
import com.SpringBootBlogApi.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlogApiApplication  implements CommandLineRunner {
	private final AdminService adminService;
	private final UserRoleService userRoleService;

	public SpringBootBlogApiApplication(AdminService adminService, UserRoleService userRoleService) {
		this.adminService = adminService;
		this.userRoleService = userRoleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (userRoleService.getAllUserRole().isEmpty()){
			userRoleService.save(RoleType.ADMIN);
			userRoleService.save(RoleType.BLOGGER);
		}
		if(adminService.countAllAdmins()==0) {
			AdminRequest adminRequest = new AdminRequest();
		adminRequest.setName("Mucahit");
		adminRequest.setUsername("mchtalmc");
		adminRequest.setPassword("12345678");
		adminRequest.setEmail("mcht.almc@gmail.com");
		adminRequest.setGender(Gender.MALE);
		adminService.saveAdmin(adminRequest);

		}

	}
}
