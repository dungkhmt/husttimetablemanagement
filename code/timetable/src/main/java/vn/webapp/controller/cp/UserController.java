/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;







/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.dto.DataPage;
import vn.webapp.model.User;
import vn.webapp.model.Users;
import vn.webapp.service.StaffService;
import vn.webapp.service.UserService;
import vn.webapp.validation.SettingsValidation;
import vn.webapp.validation.UserValidation;

@Controller("cpUser")
@RequestMapping(value = {"/cp"})
public class UserController extends BaseWeb {

    @Autowired
    private UserService userService;
    
    @Autowired
    private StaffService staffService;
    
    static final String status = "active";

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(ModelMap model, HttpSession session) {
    	DataPage<Users> usersData = userService.list();
    	
    	List<Users> usersList = usersData.getData();
    	model.put("listUsers", usersList);
    	model.put("users", status);
        return "cp.users";
    }
    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/add-an-user", method = RequestMethod.GET)
   public String addAnUser(ModelMap model, HttpSession session) {
	   
	   model.put("currentUserName", session.getAttribute("currentUserName"));
	   model.put("currentUserRole", session.getAttribute("currentUserRole"));
	   model.put("userFormAdd", new UserValidation());
   	   model.put("users", status);
       return "cp.addAnUser";
   }
   
   @RequestMapping(value="save-an-user", method=RequestMethod.POST)
   public String saveAnUser(@Valid @ModelAttribute("userFormAdd") UserValidation userValid, BindingResult result,  Map model, HttpSession session) {
	   model.put("users", status);
       if(result.hasErrors()) {
           return "cp.addAnUser";
       }else
       {
    	   String username = userValid.getUsername();
    	   String password = DigestUtils.md5Hex(userValid.getPassword());
    	   String email = userValid.getEmail();
    	   String role = userValid.getRole();
    	   String salt = "salt-sequence";
    	   int activated = userValid.getActivated();
    	   
    	   User o_Username = userService.loadUserByUsername(username);
    	   if(o_Username != null)
           {
               model.put("err", "The username is exists.");
               return "cp.addAnUser";
           }
    	   int i_InsertUser = userService.saveAUser(username, password, salt, email, role, activated);
    	   // Save a staff followed by an user
    	   String staffName = username;
    	   String staffEmail = email;
    	   String userCode = username;
    	   
    	   // TODO Asumption
    	   String staffCatCode = "LEC";
    	   String staffPhone = "0";
    	   String staffDepartment = "KHMT";
    	   String userRole = session.getAttribute("currentUserRole").toString();
    	   
    	   //staffService.saveAStaff(userCode, staffName, staffEmail, staffPhone, staffDepartment, userCode, userRole, staffCatCode, null);
    	   
           model.put("status", "Successfully saved user: " + username);
           return "cp.addAnUser";
       }
   }
   
   @RequestMapping("/userdetail/{id}")
   public String editAnUser(ModelMap model, @PathVariable("id") int id, HttpSession session) {
	   
	   HashMap<String, String> user = userService.viewDetail(id);
       model.put("userFormEdit", new UserValidation());
       model.put("dataUser", user);
       model.put("users", status);
	   model.put("currentUserName", session.getAttribute("currentUserName"));
	   model.put("currentUserRole", session.getAttribute("currentUserRole"));
       return "cp.editAnUser";
   }
   
   
   /**
   *
   * @param validationForm
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-user-detail", method = RequestMethod.POST)
  public String processValidationForm(@Valid @ModelAttribute("userFormEdit") UserValidation userFormEdit, BindingResult result, Map model, HttpSession session) {
      
      // Add the saved validationForm to the model  
	  model.put("users", status);
      if (result.hasErrors()) {
         return "cp.editAnUser";
      }else
      {
          String username = userFormEdit.getUsername();
          String password = (userFormEdit.getEpassword() != "") ? DigestUtils.md5Hex(userFormEdit.getEpassword()) : "";
          String role = userFormEdit.getRole();
          String email = userFormEdit.getEmail();
          int activated = userFormEdit.getActivated();
          int userId = userFormEdit.getUserId();
          int userRoleId = userFormEdit.getUserRoleId();
          
          Users o_Username = userService.loadUserByUsernameAndId(username, userId);
          if(o_Username != null)
          {
        	  model.put("err", "The username is exists.");
              return "cp.editAnUser";
          }
          userService.editAnUser(userId, username, password, email, role, activated, userRoleId);
          model.put("status", "Successfully edited user: " + username);
          return "cp.editAnUser";
      }
  }
  
  /**
   * 
   * Change password
   * @param model
   * @return String
   */
  @RequestMapping(value = "/changepass", method = RequestMethod.GET)
  public String changePass(ModelMap model, HttpSession session) {
	  
	  model.put("settingForm", new SettingsValidation());
	  model.put("users", status);
	  return "cp.changePass";
  }
  
  /**
   * 
   * Save settings
   * @param model
   * @return String
   */
  @RequestMapping(value = "/save-settings", method = RequestMethod.POST)
  public String saveSettings(@Valid @ModelAttribute("settingForm") SettingsValidation settingForm, BindingResult result, Map model, HttpSession session) {
	  model.put("users", status);
	  String currentUserName = session.getAttribute("currentUserName").toString();
	  String userRole = session.getAttribute("currentUserRole").toString();
	  if (result.hasErrors()) {
	      return "cp.changePass";
      }else
      {
    	  String password = DigestUtils.md5Hex(settingForm.getPassword());
    	  userService.saveSettings(currentUserName, password);
    	  model.put("status", "Successfully change password");
    	  return "cp.changePass";
      }
  }

}
