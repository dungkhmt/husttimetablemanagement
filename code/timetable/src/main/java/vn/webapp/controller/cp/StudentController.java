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
import vn.webapp.model.Faculty;
import vn.webapp.model.MasterClass;
import vn.webapp.model.Department;
import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.ScientificField;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.Student;
import vn.webapp.model.User;
import vn.webapp.service.FacultyService;
import vn.webapp.service.MasterClassService;
import vn.webapp.service.DepartmentService;
import vn.webapp.service.ScientificFieldService;
import vn.webapp.service.SpecializationKeywordService;
import vn.webapp.service.StaffService;
import vn.webapp.service.StudentService;
import vn.webapp.service.UserService;
import vn.webapp.validation.PaperValidation;
import vn.webapp.validation.StaffValidation;
import vn.webapp.validation.StudentValidation;

@Controller("cpStudent")
@RequestMapping(value = { "/cp" })
public class StudentController extends BaseWeb {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private UserService userService;

	@Autowired
	private MasterClassService masterClassService;

	/**
	 * Show list all student
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String studentList(ModelMap model, HttpSession session) {

		List<Student> studentList = studentService.listStudents();
		model.put("studentList", studentList);
		return "cp.students";
	}

	@RequestMapping(value = "/add-a-student", method = RequestMethod.GET)
	public String addAStudent(ModelMap model, HttpSession session) {

		/*
		 * Get current user name and role
		 */
		String currentUserName = session.getAttribute("currentUserName")
				.toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		/*
		 * Get paper's category
		 */

		List<MasterClass> classesList = masterClassService
				.loadMasterClassList();
		/*
		 * Put data back to view
		 */
		model.put("classesList", classesList);
		model.put("studentFormAdd", new StudentValidation());
		return "cp.addAStudent";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save-a-student", method = RequestMethod.POST)
	public String saveAStudent(
			@Valid @ModelAttribute("studentFormAdd") StudentValidation studentFormAdd,
			BindingResult result, Map model, HttpSession session) {
		List<MasterClass> classesList = masterClassService
				.loadMasterClassList();

		String studentEmail = studentFormAdd.getStudentEmail();
		String studentName = studentFormAdd.getStudentName();
		String studentPhone = studentFormAdd.getStudentPhone();
		String studentClassCode = studentFormAdd.getStudentClasses();
		String studentCode = studentFormAdd.getStudentCode();

		model.put("studentEmail", studentEmail);
		model.put("studentName", studentName);
		model.put("studentPhone", studentPhone);
		model.put("studentCode", studentCode);
		model.put("classesList", classesList);
		if (result.hasErrors()) {
			return "cp.addAStudent";
		} else {
			Faculty faculty = facultyService.loadAFacultyByCodes("SOICT");
			studentService.saveAStudent(studentCode, studentName, studentEmail,
					studentPhone, studentClassCode, faculty, 0);
			String status = "Bạn đã lưu thành công thông tin của học viên";
			model.put("status", status);
			List<Student> studentList = studentService.listStudents();
			model.put("studentList", studentList);

			return "cp.addAStudent";
		}
	}

	@RequestMapping(value = "/studentdetail/{id}", method = RequestMethod.GET)
	public String viewStudentDetail(ModelMap model,
			@PathVariable("id") int studentId, HttpSession session) {
		Student student = studentService.loadStudentById(studentId);
		List<MasterClass> classesList = masterClassService
				.loadMasterClassList();
		model.put("classesList", classesList);
		model.put("studentFormAdd", new StudentValidation());
		if (student != null) {
			model.put("studentClassCode", student.getStudent_ClassesCode());
			model.put("studentEmail", student.getStudent_Email());
			model.put("studentName", student.getStudent_Name());
			model.put("studentPhone", student.getStudent_Phone());
			model.put("studentCode", student.getStudent_Code());
			model.put("studentId", studentId);
			return "cp.editAStudent";
		}
		return "cp.notFound404";
	}
	
	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update-a-student", method = RequestMethod.POST)
	public String updateAStudent(
			@Valid @ModelAttribute("studentFormAdd") StudentValidation studentFormAdd,
			BindingResult result, Map model, HttpSession session) {
		List<MasterClass> classesList = masterClassService
				.loadMasterClassList();

		String studentEmail = studentFormAdd.getStudentEmail();
		String studentName = studentFormAdd.getStudentName();
		String studentPhone = studentFormAdd.getStudentPhone();
		String studentClassCode = studentFormAdd.getStudentClasses();
		String studentCode = studentFormAdd.getStudentCode();
		int studentId = studentFormAdd.getStudentId();
		model.put("studentEmail", studentEmail);
		model.put("studentName", studentName);
		model.put("studentPhone", studentPhone);
		model.put("studentCode", studentCode);
		model.put("classesList", classesList);
		model.put("studentClassCode", studentClassCode);
		
		if (result.hasErrors()) {
			return "cp.editAStudent";
		} else {
			Faculty faculty = facultyService.loadAFacultyByCodes("SOICT");
			Student student = studentService.loadStudentById(studentId);
			if(student != null)
			{
				student.setStudent_Code(studentCode);
				student.setStudent_Email(studentEmail);
				student.setStudent_Phone(studentPhone);
				student.setStudent_ClassesCode(studentClassCode);
				student.setStudent_Name(studentName);
				
				studentService.editAStudent(student);
				String status = "Bạn chỉnh sửa thành công thông tin của học viên";
				model.put("status", status);
				List<Student> studentList = studentService.listStudents();
				model.put("studentList", studentList);
			}
			return "cp.editAStudent";
		}
	}

	@RequestMapping(value = "/remove-a-student/{id}", method = RequestMethod.GET)
	public String removeAStudent(ModelMap model,
			@PathVariable("id") int studentId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		Student student = studentService.loadStudentById(studentId);
		if (student != null) {
			studentService.removeAStudent(studentId);
			List<Student> studentList = studentService.listStudents();
			model.put("studentList", studentList);
			String status = "Bạn đã xóa thành công học viên";
			model.put("status", status);
			return "cp.students";
		}
		return "cp.notFound404";
	}

}
