/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cpservice;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.servlet.ModelAndView;

import vn.webapp.controller.BaseRest;
/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.dto.DataPage;
import vn.webapp.dto.Response;
import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.User;
import vn.webapp.service.JournalService;
import vn.webapp.service.PaperCategoryService;
import vn.webapp.service.PaperService;
import vn.webapp.service.StaffService;
import vn.webapp.validation.PaperValidation;
import vn.webapp.validation.UserValidation;

@Controller("cpServicePaper")
@RequestMapping(value = {"/cpservice"})
public class PaperController extends BaseRest {

	@Autowired
    private StaffService staffService;
    

    @ResponseBody
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public Response generatePaperXls() {
    	//TODO
        int res = 1;
        if (res > 0) {
            return new Response(true, "Add successfully.", res);
        } else {
            return new Response(false, "Add unsuccessfully. Please try again.", res);
        }
    }

}
