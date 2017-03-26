package com.cjteam.mrile.manager.auth;

//import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthServiceImpl implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

	// private static final List<Role> allRoles = ImmutableList.of(new Role("payer"), new Role("manager"), new Role("admin"));
//	private static final List<AuthRole> allRoles = new ArrayList<AuthRole>();

	static Map<String, AuthAdministrator> uMap = new HashMap<String, AuthAdministrator>();
	static {
		AuthAdministrator administrator = new AuthAdministrator();
		administrator.setName("jiangchi");
		administrator.setPassword("$2a$10$o8YNwLGyJG8xiiuvmcFb3etGjvro7/fSrdP7fLc8xo8MBDxL/Ad7W");
		administrator.setRole(2);
		uMap.put("jiangchi", administrator);
		
		AuthAdministrator yue = new AuthAdministrator();
		yue.setName("jiangyue");
		yue.setPassword("$2a$10$WOZuPekiAdzj.j2g./U.mOwNFZZgBjByJvScKm8FWF0Ew7pan6Mb2");
		yue.setRole(2);
		uMap.put("jiangyue", yue);
		
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("===============================username:" + username);
		// Administrator administrator = administratorService.findByUsername(username);
		AuthAdministrator administrator = uMap.get(username);
		if (null == administrator) {
			throw new UsernameNotFoundException(username);
		}
		judgeRole(administrator);
		LOG.info("the use {} has {} roles", administrator, administrator.getAuthorities().size());
		return administrator;
	}

	private void judgeRole(AuthAdministrator admin) {
		Integer role = admin.getRole();
		if (null == role)
			return;
		List<AuthRole> authorities = new ArrayList<AuthRole>();
//		while (role-- > 0) {
//		}
		if (role.intValue() == 1) {
			authorities.add(new AuthRole("admin"));
			authorities.add(new AuthRole("manager"));
		}
		if (role.intValue() == 2) {
			authorities.add(new AuthRole("manager"));
		}
		admin.setAuthorities(authorities);
	}
}
