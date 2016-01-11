/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
import vn.webapp.model.MasterThesis;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.ScientificField;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.Student;
import vn.webapp.model.User;
import vn.webapp.service.DepartmentService;
import vn.webapp.service.MasterThesisService;
import vn.webapp.service.ScientificFieldService;
import vn.webapp.service.SpecializationKeywordService;
import vn.webapp.service.StaffService;
import vn.webapp.service.StudentService;
import vn.webapp.service.UserService;
import vn.webapp.validation.PaperValidation;
import vn.webapp.validation.StaffValidation;
import vn.webapp.validation.ThesisValidation;

@Controller("cpMasterThesis")
@RequestMapping(value = {"/cp"})
public class MasterThesisController extends BaseWeb {
	
	@Autowired
    private MasterThesisService masterThesisService;
	
	@Autowired
    private StaffService staffService;

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Autowired
    private SpecializationKeywordService specializationKeywordService;
    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/listStudentToAssignThesis", method = RequestMethod.GET)
   public String studentList(ModelMap model, HttpSession session) {

	   List<Student> studentList = studentService.listStudentsByStatus(0);
	   model.put("studentList", studentList);
	   return "cp.listToAssignThesis";
   }
   
   @RequestMapping("/assignthesis/{id}")
   public String AssignThesis(ModelMap model, @PathVariable("id") int studentId, HttpSession session) {
	   
	   /*
	    * Put data back to view
	    */
	   Student student = studentService.loadStudentById(studentId);
	   
	   model.put("thesisFormAssign", new ThesisValidation());
	   	
	   if(student != null)
	   {
		   model.put("student", student);
		   model.put("specializationKeywordList", specializationKeywordService.loadSpecializationKeywordList());
		   model.put("professorList", staffService.listStaffs());
		   model.put("departmentList", departmentService.loadDepartmentList());
		   
		   /*List<String> KWCodes = staffService.listKWCodeByStaff(staff.getStaff_Code());
		   List<SpecializationKeyword> specializationKeywordList = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   specializationKeywordList.add(KW);
		   }
		   model.put("specializationKeywords", specializationKeywordList);
		   
		   
		   System.out.println("SpecializationListSize: " + staff.getListSpecializationKeywords().size());
		   System.out.println("SpecializationListSize: " + specializationKeywordList.size());*/
		   
	       return "cp.assignThesis";
	   }
	   return "cp.notFound404";
   }
   
   @RequestMapping(value = "/save-a-thesis", method = RequestMethod.POST)
   public String saveAProfessor(@Valid @ModelAttribute("thesisFormAssign") ThesisValidation thesisFormAssign, 
		   							BindingResult result,  Map model, HttpSession session) {
 	 List<Department> departmentList = departmentService.loadDepartmentList();
 	 List<SpecializationKeyword> specializationKeywordList = specializationKeywordService.loadSpecializationKeywordList();
 	 
 	 String studentCode = thesisFormAssign.getStudentCode();
 	 String thesisName = thesisFormAssign.getThesisName();
 	 String supervisorCode = thesisFormAssign.getSupervisor();
 	 ArrayList<String> staffKeywords = thesisFormAssign.getStaffKeywords();
 	 HashSet<SpecializationKeyword> specializationKeywords = new HashSet<SpecializationKeyword>();
 	 for(String code:staffKeywords){
 		 SpecializationKeyword speKW = specializationKeywordService.getSpecializationKeywordByCode(code);
 		 specializationKeywords.add(speKW);
 	 }
 	 
 	 model.put("thesisName", thesisName);
 	 model.put("specializationKeywordList", specializationKeywordList);
 	 model.put("specializationKeywords", specializationKeywords);
 	 if(result.hasErrors()) {	
 		 String status = "Bạn đã lưu khong thành công thông tin đề tài";
 		 model.put("status", status);
 		 return "cp.assignThesis";
      }else
      {
    	 Student student = studentService.loadStudentByCode(studentCode);
    	 Staff supervisor = staffService.loadStaffByStaffCode(supervisorCode);
    	 String ThesisCode = studentCode + supervisorCode;
    	 
    	 if(student != null && supervisor != null){
    		 masterThesisService.saveAMasterThesis(ThesisCode, thesisName, studentCode, supervisorCode, specializationKeywords, student);
 	     	 String status = "Bạn đã lưu thành công thông tin đề tài";
 	     	 model.put("status", status);
    	 }
    	 if(supervisor == null){
    		 System.out.println("Supervisor null");
    		 System.out.println(supervisorCode);
    	 }
    	 if(student == null){
    		 System.out.println("Student null");
    	 }
     	 
       List<Student> studentList = studentService.listStudentsByStatus(0);
  	   model.put("studentList", studentList);
  	   return "cp.listToAssignThesis";
      }
   }
   
   @RequestMapping(value = "/listThesis", method = RequestMethod.GET)
   public String thesisList(ModelMap model, HttpSession session) {

	   List<Student> studentList = studentService.listStudentsByStatus(1);
	   model.put("studentList", studentList);
	   
	   return "cp.listThesis";
   }
   
   /*@RequestMapping(value = "/schedule", method = RequestMethod.GET)
   public String schedule(ModelMap model, HttpSession session) throws Exception{

	   PrintWriter fo = new PrintWriter("E:\\test.txt" , "UTF-8");
	   fo.println("juries");
	   List<MasterThesis> listMasterThesis = masterThesisService.listMasterThesis();
	   fo.println(listMasterThesis.size());
	   fo.println("StudentID SupervisorID Examiner1ID Examiner2ID PresidentID SecretaryID AdditionalMemberID Slot Room");
	   
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   Staff supervisor = staffService.loadStaffByStaffCode(masterThesis.getThesis_SupervisorCode());		   
		   String temp = student.getStudent_ID() + " " + supervisor.getStaff_ID() + " 0 0 0 0 0 0 0";  
		   fo.println(temp);		   
	   }
	   List<Staff> listInternalProfessor = staffService.listStaffsByUniversity("HUSTKHMT");
	   List<Staff> listExternalProfessor = staffService.listStaffsByUniversity("DIFFKHMT");
	   HashSet<Staff> listProfessor = new HashSet<Staff>();
	   
	   //internal professors
	   fo.println("internal professors");
	   fo.println(listInternalProfessor.size());
	   String temp = "";
	   
	   for(Staff internalProfessor:listInternalProfessor){
		   System.out.println(internalProfessor.getStaff_Name());
		   temp += internalProfessor.getStaff_ID();
		   temp += " ";
		   listProfessor.add(internalProfessor);
	   }
	   fo.println(temp);
	   
	   //external professors
	   fo.println("external professors");
	   fo.println(listExternalProfessor.size());
	   temp = "";
	   for(Staff externalProfessor:listExternalProfessor){
		   System.out.println(externalProfessor.getStaff_Name());
		   temp += externalProfessor.getStaff_ID();
		   temp += " ";
		   listProfessor.add(externalProfessor);
	   }
	   fo.println(temp);
	   
	   fo.println("subject match between professors and student");
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   //fo.println(student.getStudent_Name());
		   //fo.println(masterThesis.getThesis_Name());
		   
		   
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			  //fo.println(KW.getKW_VNName());
		   }	
		   
		   
		   for(Staff pro:listProfessor){
			   KWCodes = staffService.listKWCodeByStaff(pro.getStaff_Code());
			   List<SpecializationKeyword> speKWprofessor = new ArrayList<SpecializationKeyword>();
			   for(String KWCode:KWCodes){
				   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
				   speKWprofessor.add(KW);
			   }
			   
			   //fo.println(pro.getStaff_Name());
			   //fo.println(speKWprofessor.size());
			   for(SpecializationKeyword s:speKWprofessor){
				   fo.println(s.getKW_VNName());
			   }
			   
			   int i = 0;
			   for(SpecializationKeyword speKWs:speKWstudent){
				   for(SpecializationKeyword speKWp:speKWprofessor){
					   if(speKWs.getKW_Code().equals(speKWp.getKW_Code())){
						   i++;
					   }
				   }
			   }
			   temp = student.getStudent_ID() + " "+pro.getStaff_ID()+" "+ i ;
			   fo.println(temp);
		   }
		   
		   
		   //fo.println(temp);		   
	   }
	   
	   fo.println("subject match between students");
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   String speKWstudentlist = "";
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			   speKWstudentlist += KW.getKW_VNName()+ " ";
			  //fo.println(KW.getKW_VNName());
		   }	
		   
		   for(MasterThesis masterThesisRef:listMasterThesis){
			   if(!masterThesisRef.getThesis_Code().equals(masterThesis.getThesis_Code())){				
			   
				   Student studentRef = studentService.loadStudentByCode(masterThesisRef.getThesis_StudentCode());
			   	   KWCodes = masterThesisService.loadKWCodeByThesis(masterThesisRef.getThesis_Code());
				   List<SpecializationKeyword> speKWstudentRef = new ArrayList<SpecializationKeyword>();
				   String speKWstudentReflist = "";
				   for(String KWCode:KWCodes){
					   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
					   speKWstudentRef.add(KW);
					   speKWstudentReflist += KW.getKW_VNName() + " ";
				   }
				   //fo.println(student.getStudent_Name() +": "+speKWstudentlist+  "vs" + studentRef.getStudent_Name() +": "+speKWstudentReflist);	   
				   
			  
				   int i = 0;
				   for(SpecializationKeyword speKWs:speKWstudent){
					   for(SpecializationKeyword speKWsref:speKWstudentRef){
						   if(speKWs.getKW_Code().equals(speKWsref.getKW_Code())){
							   i++;
						   }
					   }
				   }
				   temp = student.getStudent_ID() + " "+studentRef.getStudent_ID()+" "+ i ;
				   fo.println(temp);
			   }
		   }		   
	   }
	   fo.println("number of slots & number of rooms");
	   fo.println("5 4");
	   
	   fo.println("Professor Information");
	   fo.println("ProID|ProName");
	   fo.println("SpecializationKeywords");
	   fo.println(listProfessor.size());
	   for(Staff pro:listProfessor){
		   fo.println(pro.getStaff_ID()+"|"+pro.getStaff_Name());
		   temp = "";
		   List<String> KWCodes = staffService.listKWCodeByStaff(pro.getStaff_Code());
		   List<SpecializationKeyword> speKWprofessor = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWprofessor.add(KW);
			   temp += KW.getKW_VNName()+"|";
		   }
		   fo.println(temp);
	   }
	   fo.println("Student Information");
	   fo.println("StudentID|StudentName|ThesisName");
	   fo.println("SpecializationKeywords");
	   fo.println(listMasterThesis.size());
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   fo.println(student.getStudent_ID()+"|"+student.getStudent_Name()+"|"+masterThesis.getThesis_Name());
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   temp = "";
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			   temp += KW.getKW_VNName()+"|";			  
		   }	
		   fo.println(temp);
	   }	   
	   
	   fo.close();
	   
	   return "cp.listThesis";
	   
   }*/
   
}
