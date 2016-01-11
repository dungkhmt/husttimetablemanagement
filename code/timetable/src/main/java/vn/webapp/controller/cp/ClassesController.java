/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.multipart.MultipartFile;

/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.model.AcademicYear;
import vn.webapp.model.Classes;
import vn.webapp.model.Department;
import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.Staff;
import vn.webapp.service.ClassesService;
import vn.webapp.validation.ClassesValidation;
import vn.webapp.validation.PaperValidation;
import vn.webapp.validation.StaffValidation;

@Controller("cpClasses")
@RequestMapping(value = {"/cp"})
public class ClassesController extends BaseWeb {

    @Autowired
    private ClassesService classesService;

	   /**
	    * Show list all 
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/classes", method = RequestMethod.GET)
	   public String classesList(ModelMap model, HttpSession session) {
	
		   List<Classes> classesList = classesService.getListAllClasses();
		   for(Classes cls: classesList){
			   System.out.println("ClassesController::classesList, Code = " +  cls.getClasses_Code() + ", ID = " + cls.getClasses_ID());
		   }
		   model.put("classesList", classesList);
		   return "cp.classes";
	   }
	   
	   
	   @RequestMapping(value = "/add-a-class", method = RequestMethod.GET)
	   public String addAClass(ModelMap model, HttpSession session) {
		   model.put("ClassesFormAdd", new ClassesValidation());
		   
		   return "cp.addAClass";
	   }   
   
	   /**
	   *
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/save-a-class", method = RequestMethod.POST)
	  public String saveAClass(@Valid @ModelAttribute("ClassesFormAdd") ClassesValidation classesFormAdd, BindingResult result,  Map model, HttpSession session) {
		 String className = classesFormAdd.getClassName();
		 String classCode = classesFormAdd.getClassCode();
		 String classFaculty = "SOICT";//classesFormAdd.getClassFacultyCode();
		 String classYear = classesFormAdd.getClassYear();
		 //System.out.println("ClassesController::saveAClass, year = " + classYear);
		 if(result.hasErrors()) {		
			 return "cp.addAClass";
	     }else
	     {
	    	 classesService.saveAClass(classCode,className,classFaculty,classYear);
	    	 String status = "Bạn đã thêm thành công!";
			 model.put("status", status);
		     return "cp.addAClass";
	     }
	  }
 
	  /**
	   * 
	   * @param model
	   * @param defenseSessionrId
	   * @param session
	   * @return
	   */
	   @RequestMapping("/classdetail/{id}")
	   public String editAClass(ModelMap model, @PathVariable("id") int classId, HttpSession session) {
		   
		   Classes cls = classesService.getClassById(classId);
		   if(cls != null)
		   {
			   model.put("classCode", cls.getClasses_Code());
			   model.put("className", cls.getClasses_Name());
			   model.put("classFacultyCode", cls.getClasses_FacultyCode());
			   model.put("ClassesFormAdd", new ClassesValidation());
			   model.put("classId", classId);
			   model.put("classYear", cls.getClasses_Year());
			   return "cp.editAClass";
		   }
		   return "cp.notFound404";
	   }
 
	   /**
	    * Adding a paper
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/edit-a-class", method = RequestMethod.POST)
	   public String updateAClass(HttpServletRequest request, @Valid @ModelAttribute(
			   "ClassesFormAdd") ClassesValidation classesFormAdd, BindingResult result, 
			   Map model, HttpSession session) {

		   /*
		    * Put data back to view
		    */
		   String sClassCode = classesFormAdd.getClassCode();
		   String sClassName = classesFormAdd.getClassName();
		   int classId = classesFormAdd.getClassId();
		   String sClassFacultyCode = "SOICT";//classesFormAdd.getClassCode();
		   String sClassYear = classesFormAdd.getClassYear();
		   model.put("sClassCode", sClassCode);
		   model.put("sClassName", sClassName);
		   model.put("classId", classId);
		   // Add the saved validationForm to the model  
	      if (result.hasErrors()) {
	           return "cp.editADefenseSession";
	      }else
	      {
	   	   		classesService.editAClass(classId, sClassCode, sClassName, sClassName, sClassFacultyCode, sClassYear);
	   	   		
	   	   		model.put("status", "Chỉnh sửa thành công.");
		   	 
		   	   return "cp.editAClass";
	      }
	   }
	   
	  @RequestMapping(value = "/remove-a-class/{id}", method = RequestMethod.GET)
	  public String removeAClass(ModelMap model, @PathVariable("id") int classId, HttpSession session) {
		  System.out.println("ClassesController::removeAClass, classId = " + classId); 
		  Classes cls = classesService.getClassById(classId);
		   if(cls != null){
			   classesService.removeAClass(classId);
			   String status = "Xóa thành công.";
			   List<Classes> classesList = classesService.getListAllClasses();
			   model.put("classesList", classesList);
			   model.put("status", status);
			   return "cp.classes";
		   }
		   return "cp.notFound404";
	  }
   
}
