/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.controller.cp;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("cpAuth")
@RequestMapping(value = "/cp/auth")
public class AuthController {
    
    /**
     * 
     * @param model
     * @param request     
     * @return 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, 
            HttpServletRequest request) {                       
        return "signin";
    }
    
}