/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.util.ArrayList;
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




import org.springframework.web.servlet.ModelAndView;















/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.dto.DataPage;
import vn.webapp.model.AcademicYear;
import vn.webapp.model.Department;
import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.ScientificField;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.StaffCategory;
import vn.webapp.model.University;
import vn.webapp.model.User;
import vn.webapp.model.Users;
import vn.webapp.service.DepartmentService;
import vn.webapp.service.ScientificFieldService;
import vn.webapp.service.SpecializationKeywordService;
import vn.webapp.service.StaffCategoryService;
import vn.webapp.service.StaffService;
import vn.webapp.service.UniversityService;
import vn.webapp.service.UserService;
import vn.webapp.validation.PaperValidation;
import vn.webapp.validation.StaffValidation;

@Controller("cpStaff")
@RequestMapping(value = {"/cp"})
public class StaffController extends BaseWeb {

    @Autowired
    private StaffService staffService;
    
    @Autowired
    private UniversityService universityService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Autowired
    private SpecializationKeywordService specializationKeywordService;
    
    @Autowired
    private StaffCategoryService staffCategoryService;
    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/profile", method = RequestMethod.GET)
   public String staffInfo(ModelMap model, HttpSession session) {
	   String currentUserName = session.getAttribute("currentUserName").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   Staff staff = staffService.loadStaffByUserCode(userCode);
	   
	   List<Department> departmentList = departmentService.loadDepartmentList();
	   model.put("staffFormEdit", new StaffValidation());
	   if(staff != null){
		   model.put("staffEmail", staff.getStaff_Email());
		   model.put("staffName", staff.getStaff_Name());
		   model.put("staffPhone", staff.getStaff_Phone());
		   model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
		   //model.put("staffDepartementName", staff.getDepartment().getDepartment_Name());
		   model.put("staffDepartementCode", staff.getDepartment().getDepartment_Code());
	   }
	   model.put("departmentList", departmentList);
       return "cp.profile";
   }
   
   /**
    * Show list all professor
    * @param model
    * @return
    */
   @RequestMapping(value = "/professors", method = RequestMethod.GET)
   public String paperList(ModelMap model, HttpSession session) {

	   List<Staff> professorList = staffService.listStaffs();
	   
	   model.put("professorList", professorList);
	   model.put("departmentList",departmentService.loadDepartmentList());
	   return "cp.professors";
   }
   
   @RequestMapping("/professordetail/{id}")
   public String editAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
	   
	   /*
	    * Put data back to view
	    */
	   String userRole = session.getAttribute("currentUserRole").toString();
	   Staff staff = staffService.loadStaffById(userRole, professorId);
	   model.put("staffFormEdit", new StaffValidation());
	   	
	   if(staff != null)
	   {
		   List<Department> departmentList = departmentService.loadDepartmentList();
		   
		   model.put("staffEmail", staff.getStaff_Email());
		   model.put("staffName", staff.getStaff_Name());
		   model.put("staffPhone", staff.getStaff_Phone());
		   model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
		   model.put("departmentList", departmentList);
		   model.put("specializationKeywordList", specializationKeywordService.loadSpecializationKeywordList());
		   model.put("staffId", professorId);
		   model.put("universityList", universityService.loadUniversityList());
		   model.put("staffDepartementCode", staff.getDepartment().getDepartment_Code());
		   
		   
		   model.put("specializationKeywords", specializationKeywordService.loadStaffSpecializationKeywordList(staff.getStaff_Code()));
		   		   
	       return "cp.editAProfessor";
	   }
	   return "cp.notFound404";
   }
   
   @RequestMapping(value = "/add-a-professor", method = RequestMethod.GET)
   public String addProfessor(ModelMap model, HttpSession session) {
	  
	   /*
	    * Get current user name and role
	    */
	   String currentUserName = session.getAttribute("currentUserName").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   
	   /*
	    * Get paper's category
	    */
	   
	   List<Department> departmentList = departmentService.loadDepartmentList();
	   List<SpecializationKeyword> specializationKeywordList = specializationKeywordService.loadSpecializationKeywordList();
	   /*
	    * Put data back to view
	    */
	   model.put("departmentList", departmentList);
	   model.put("staffFormAdd", new StaffValidation());
	   model.put("universityList", universityService.loadUniversityList());
	   model.put("specializationKeywordList", specializationKeywordList);
	   return "cp.addAProfessor";
   }
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/save-a-professor", method = RequestMethod.POST)
  public String saveAProfessor(@Valid @ModelAttribute("staffFormAdd") StaffValidation staffFormAdd, BindingResult result,  Map model, HttpSession session) {
	 List<Department> departmentList = departmentService.loadDepartmentList();
	 List<SpecializationKeyword> specializationKeywordList = specializationKeywordService.loadSpecializationKeywordList();
	   
	 String staffEmail = staffFormAdd.getStaffEmail();
	 String staffName = staffFormAdd.getStaffName();
	 String staffPhone = staffFormAdd.getStaffPhone();
	 String staffDepartmentCode = staffFormAdd.getStaffDepartment();
	 String staffUniversityCode = staffFormAdd.getStaffUniversity();
	 
	 ArrayList<String> staffKeywords = staffFormAdd.getStaffKeywords();
	 HashSet<SpecializationKeyword> specializationKeywords = new HashSet<SpecializationKeyword>();
	 System.out.print(staffKeywords.size());
	 for(String code:staffKeywords){
		 SpecializationKeyword speKW = specializationKeywordService.getSpecializationKeywordByCode(code);
		 specializationKeywords.add(speKW);
	 }
	 
	 System.out.println(staffKeywords);
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	 model.put("departmentList", departmentList);
	 model.put("specializationKeywordList", specializationKeywordList);
	 model.put("specializationKeywords", specializationKeywords);
	 
		 
	 if(result.hasErrors()) {		
		 return "cp.addAProfessor";
     }else
     {
    	 	 String staffCatCode = "LEC";
    	 	 String userCode = session.getAttribute("currentUserCode").toString();
    	 	 String userRole = session.getAttribute("currentUserRole").toString();
    	 	 
    	 	 StaffCategory staffCategory = staffCategoryService.getByCode(staffCatCode);
    	 	 Department staffDepartment = departmentService.loadDepartmentByCode(staffDepartmentCode);
    	 	 University staffUniversity = universityService.loadAUniversityByCodes(staffUniversityCode);
    	 	     	 	 
    	 	 int temp;
    	 	 for(temp = 0; temp < staffEmail.length(); temp++){
    	 		 if(staffEmail.charAt(temp) == '@')
    	 			 break;
    	 	 }
    	 	 System.out.print(temp);
    	 	 String staffCode = staffEmail.substring(0, temp);
    		 Users user = userService.loadUserByUserCode(staffCode);
        	 
    	 	 
    	 	 staffService.saveAStaff(staffCode, staffName, staffEmail, staffPhone, staffUniversity, staffDepartment, user, userRole, staffCategory, specializationKeywords);
	    	 String status = "Bạn đã lưu thành công thông tin của giảng viên";
	    	 model.put("status", status);
    	 
    	 return "cp.addAProfessor";
     }
  }
   
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-staff", method = RequestMethod.POST)
  public String editStaffInfo(@Valid @ModelAttribute("staffFormEdit") StaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {
	 List<Department> departmentList = departmentService.loadDepartmentList();
	 int staffId = staffFormEdit.getStaffId();
	 String staffEmail = staffFormEdit.getStaffEmail();
	 String staffName = staffFormEdit.getStaffName();
	 String staffPhone = staffFormEdit.getStaffPhone();
	 String staffUniversityCode = staffFormEdit.getStaffUniversity();
	 String staffDepartmentCode = staffFormEdit.getStaffDepartment();
	 ArrayList<String> staffKeywords = staffFormEdit.getStaffKeywords();
	 HashSet<SpecializationKeyword> specializationKeywords = new HashSet<SpecializationKeyword>();
	
	 for(String code:staffKeywords){
			 SpecializationKeyword speKW = specializationKeywordService.getSpecializationKeywordByCode(code);
			 specializationKeywords.add(speKW);
	 }
	 
	 
	 System.out.println("staff Name : " + staffName);
	 model.put("staffId", staffId);
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	 model.put("departmentList", departmentList);
	 model.put("specializationKeywordList", specializationKeywordService.loadSpecializationKeywordList());
	 model.put("specializationKeywords", specializationKeywords);
	 
	 if(result.hasErrors()) {		
		 System.out.print("Failed");
		 return "cp.editAProfessor";
     }else
     {
    	 String staffCatCode = "LEC";
	 	 String userCode = session.getAttribute("currentUserCode").toString();
	 	 String userRole = session.getAttribute("currentUserRole").toString();
	 	 
	 	 StaffCategory staffCategory = staffCategoryService.getByCode(staffCatCode);
	 	 Department staffDepartment = departmentService.loadDepartmentByCode(staffDepartmentCode);
	 	 University staffUniversity = universityService.loadAUniversityByCodes(staffUniversityCode);
	 	     	 	 
	 	 int temp;
	 	 for(temp = 0; temp < staffEmail.length(); temp++){
	 		 if(staffEmail.charAt(temp) == '@')
	 			 break;
	 	 }
	 	 System.out.print(temp);
	 	 String staffCode = staffEmail.substring(0, temp);
		 Users user = userService.loadUserByUserCode(staffCode);
	 	 
	 	 staffService.editAStaff(staffId, staffCode, staffName, staffEmail, staffPhone, staffUniversity, staffDepartment, user, userRole, staffCategory, specializationKeywords);
	    	
	    	 
	     model.put("staffDepartementCode", staffDepartmentCode);
	     model.put("staffUniversityCode", staffUniversityCode);
	     String status = "Bạn đã lưu thành công thông tin của giảng viên";
	     model.put("status", status);
    	
    	 return "cp.editAProfessor";
     }
  }
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-staff-detail", method = RequestMethod.POST)
  public String saveStaffInfo(@Valid @ModelAttribute("staffFormEdit") StaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {
	 List<Department> departmentList = departmentService.loadDepartmentList();
	 String staffEmail = staffFormEdit.getStaffEmail();
	 String staffName = staffFormEdit.getStaffName();
	 String staffPhone = staffFormEdit.getStaffPhone();
	 String staffDepartment = staffFormEdit.getStaffDepartment();
	 
	 System.out.println("staff Name : " + staffName);
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	 
	 model.put("departmentList", departmentList);
	 if(result.hasErrors()) {
		 model.put("error", 1);
		 return "cp.profile";
     }else
     {
    	 String userCode = session.getAttribute("currentUserCode").toString();
    	 Staff staff = staffService.loadStaffByUserCode(userCode);
    	 if(staff != null){
	    	 String staffCatCode = "LEC";
	    	 int staffID = staff.getStaff_ID();
	    	 
	    	 
	    	 staff.setStaff_AsciiName(staffName);
	     	 staff.setStaff_Name(staffName);
	     	 staff.setStaff_Phone(staffPhone);
	     	 //staff.setStaff_User_Code(userCode);
	     	 //staff.setStaff_Category_Code(staffCatCode);
	     	 staff.setStaff_Email(staffEmail);
	     	 staff.setStaff_Code(userCode);
	     	 
	    	 //staffService.editAStaff(staff);
	    	 model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
	    	 model.put("staffDepartementCode", staffDepartment);
    	 }
    	 return "cp.profile";
     }
  }
  
  @RequestMapping(value = "/remove-a-professor/{id}", method = RequestMethod.GET)
  public String removeAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   Staff staff = staffService.loadStaffById(userRole, professorId);
	   if(staff != null){
		   staffService.removeAStaff(professorId);
		   List<Staff> professorList = staffService.listStaffs();
		   model.put("professorList", professorList);
		   String status = "Bạn đã xóa thành công giảng viên";
		   model.put("status", status);
		   return "cp.professors";
	   }
	   return "cp.notFound404";
  }

}
