/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.User;
import vn.webapp.model.Users;

public interface UserService {

	public Users loadUserById(final int userId) throws UsernameNotFoundException;
	
    public User loadUserByUsername(final String username) throws UsernameNotFoundException;
    
    public Users loadUserByUserCode(final String username) throws UsernameNotFoundException;
    
    public Users loadUserByUsernameAndId(final String username, int id) throws UsernameNotFoundException;

    public DataPage<Users> list();
    
    public HashMap<String, String> viewDetail(int id);
    
    public int removeUser(String id);
    
    public int saveAUser(String username, String password, String salt, String email, String role, int activated);
    
    public void editAnUser(int userId, String username, String password, String email, String role, int activated, int userRoleId);
    
    public void saveSettings(String username, String password);
}
