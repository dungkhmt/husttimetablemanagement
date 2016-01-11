/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.webapp.controller.BaseWeb;
import vn.webapp.dto.DataPage;
import vn.webapp.model.User;
import vn.webapp.model.Users;
import vn.webapp.service.UserService;

@Controller("cpIndex")
@RequestMapping(value = {"/cp"})
public class IndexController extends BaseWeb {

    @Autowired
    private UserService userService;

    public IndexController() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
        return "cp.index";
    }
    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String home(ModelMap model, HttpSession session) {
	   String username = SecurityContextHolder.getContext().getAuthentication().getName();
	   User user = userService.loadUserByUsername(username);
	   session.setAttribute("currentUserName", username);
	   session.setAttribute("currentUserRole", user.getUser_Role());
	   session.setAttribute("currentUserCode", user.getUser_Code());
       return "cp.home";
   }

}
