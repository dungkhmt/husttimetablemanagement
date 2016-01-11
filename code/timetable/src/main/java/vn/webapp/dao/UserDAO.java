/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.UserRoles;
import vn.webapp.model.Users;
import vn.webapp.model.User;

public interface UserDAO {

    public Users getByUsername(String userName);
    
    public Users getByUsernameAndId(String userName, int id);

    public List<Users> list();
    
    public Users viewDetail(int id);
    
    public UserRoles viewDetailUserRole(String userName);
    
    public int removeUser(String id);
    
    public int saveAUser(Users user);
    
    public int saveAUserRole(UserRoles userRole);
    
    public void editAnUser(Users user);
    
    public void editAnUserRole(UserRoles userRole);
    
    public Users loadUserById(int userId);
    
    public int count();
    
}
