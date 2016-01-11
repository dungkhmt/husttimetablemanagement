/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.UserDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.Role;
import vn.webapp.model.User;
import vn.webapp.model.UserRoles;
import vn.webapp.model.Users;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /**
     * Get an user by id
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public Users loadUserById(final int userId) throws UsernameNotFoundException {
        try {
            return userDAO.loadUserById(userId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
        	Users users = userDAO.getByUsername(username);
            UserRoles userRole = userDAO.viewDetailUserRole(username);
            if (users != null) {
                User user = new User();
                user.setUsername(users.getUsername());
                user.setPassword(users.getPassword());
                user.setSalt(users.getSalt());
                user.setEmail(users.getEmail());
                user.setUser_Code(users.getUser_Code());
                if(userRole != null){
                	user.setUser_Role(userRole.getRole());
                }
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Users loadUserByUserCode(final String username) throws UsernameNotFoundException {
    
    	return userDAO.getByUsername(username);
    	
    }
    
    /**
     * Get an user by username and id
     * @param String
     * @param int
     * @return object
     * @throws UsernameNotFoundException
     */
    public Users loadUserByUsernameAndId(final String username, int id) throws UsernameNotFoundException {
    	Users users = null;
        try {
            users = userDAO.getByUsernameAndId(username, id);
            return users;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return users;
        }
    }
    

    /**
     * Get list all users
     * @param null
     * @return List
     */
    @Override
    public DataPage<Users> list() {
        DataPage<Users> dataUser = new DataPage<>();
        dataUser.setData(userDAO.list());
        return dataUser;
    }

    /**
     * Get an user by id
     * @param int
     * @return object
     */
    @Override
    public HashMap<String, String> viewDetail(int id) {
    	Users user = userDAO.viewDetail(id);
    	if(user != null)
    	{
    		HashMap<String, String> userInfo = new HashMap<>();
    		userInfo.put("username", user.getUsername());
    		userInfo.put("email", user.getEmail());
    		userInfo.put("userId", String.valueOf(user.getUser_ID()));
    		userInfo.put("activated", String.valueOf(user.getEnabled()));
    		
    		userInfo.put("username", user.getUsername());
    		userInfo.put("password", user.getPassword());
    		
    		UserRoles userRole = userDAO.viewDetailUserRole(user.getUsername());
    		if(userRole.getUserRole_ID() > 0)
    		{
    			userInfo.put("userRole", userRole.getRole());
    			userInfo.put("userRoleId", String.valueOf(userRole.getUserRole_ID()));
    		}
    		return userInfo;
    	}
    	return null;
    }

    /**
     * Get list all users
     * @param int
     * @return int
     */
    @Override
    public int removeUser(String id) {
        return userDAO.removeUser(id);
    }

    /**
     * Save an user
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    public int saveAUser(String username, String password, String salt, String email, String role, int enabled)
    {
        Users user = new Users();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(salt);
        user.setEnabled(enabled);
        user.setUser_Code(username);
        
        UserRoles userRole = new UserRoles();
        userRole.setRole(role);
        userRole.setUsername(username);
        
        int i_SaveUser = userDAO.saveAUser(user);
        int i_SaveUserRole = userDAO.saveAUserRole(userRole);
        return i_SaveUser & i_SaveUserRole;
    }
    
    /**
     * Edit an user
     * @param String
     * @param String
     * @param String
     * @param String
     * @return void
     */
    @Override
    public void editAnUser(int userId, String username, String password, String email, String role, int activated, int userRoleId){
    	// Update user
    	Users user = userDAO.loadUserById(userId);
        user.setEmail(email);
        user.setUsername(username);
        user.setEnabled(activated);
        user.setUser_Code(username);
        if(password != ""){
        	user.setPassword(password);
        }
        
        UserRoles userRole = userDAO.viewDetailUserRole(username);
        userRole.setUserRole_ID(userRoleId);
        userRole.setRole(role);
        userRole.setUsername(username);
        
        userDAO.editAnUser(user);
        userDAO.editAnUserRole(userRole);
    }
    
    /**
     * Edit an user
     * @param String
     * @param String
     * @return void
     */
    @Override
    public void saveSettings(String username, String password){
    	Users newSetting = new Users();
    	if(!password.equals("")){
	    	Users user = userDAO.getByUsername(username);
	    	newSetting.setUser_ID(user.getUser_ID());
	    	newSetting.setEmail(user.getEmail());
	    	newSetting.setUsername(username);
	    	newSetting.setEnabled(user.getEnabled());
	    	newSetting.setUser_Code(user.getUser_Code());
	    	newSetting.setPassword(password);
	    	newSetting.setSalt(user.getSalt());
	    	
	    	userDAO.editAnUser(newSetting);
    	}
    }
}
