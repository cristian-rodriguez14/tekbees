package org.o7planning.springmvcshoppingcart.authentication;

import java.util.ArrayList;
import java.util.List;

import org.o7planning.springmvcshoppingcart.dao.ClienteDAO;
import org.o7planning.springmvcshoppingcart.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
	
	@Autowired
    private ClienteDAO clienteDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente account = clienteDAO.findCliente(username);
        System.out.println("Account= " + account);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }


        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();



        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(account.getClienteEmail(), //
                "", grantList);

        return userDetails;
    }

}
