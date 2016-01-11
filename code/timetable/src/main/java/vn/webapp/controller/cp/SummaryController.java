/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;

/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.model.AcademicYear;
import vn.webapp.model.Department;
import vn.webapp.model.Papers;
import vn.webapp.model.Patents;
import vn.webapp.model.Staff;
import vn.webapp.model.Topics;
import vn.webapp.service.AcademicYearService;
import vn.webapp.service.DepartmentService;
import vn.webapp.service.JournalService;
import vn.webapp.service.PaperService;
import vn.webapp.service.PatentService;
import vn.webapp.service.StaffService;
import vn.webapp.service.TopicCategoryService;
import vn.webapp.service.TopicService;
import vn.webapp.validation.SummaryExcelValidation;
import vn.webapp.validation.TopicExcellValidation;

@Controller("cpSummary")
@RequestMapping(value = {"/cp"})
public class SummaryController extends BaseWeb {

    @Autowired
    private TopicService topicService;
    
    @Autowired
    private TopicCategoryService topicCategoryService;
    
    @Autowired
    private JournalService journalService;
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private PatentService patentService;
    
    @Autowired
    private AcademicYearService academicYearService;
    
    @Autowired
    private PaperService paperService;
    
    static final String status = "summary";
    
   
   	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/summaryExcel", method = RequestMethod.POST)
	public ModelAndView downloadSummaryExcel(@Valid @ModelAttribute("summaryExcelForm") SummaryExcelValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<Department> departmentList = departmentService.loadDepartmentList();
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			String departmentCode = summaryValidExcell.getDepartment();
			
			Department department = departmentService.loadADepartmentByCodes(departmentCode, "SOICT");
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    
		    List<Staff> staffs = staffService.listStaffsByDepartment(departmentCode);
		    List<Topics> topicsList = topicService.loadTopicSummaryListByYear(yearForGenerating);
		    List<Papers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    int iTotalPaperConvertedHours = 0;
    		int iTotalPaperJournal = 0;
    		int iTotalPaperConferenceJournal = 0;
    		
    		int iTotalTopicConvertedHours = 0;
    		int iTotalTopicNational = 0;
    		int iTotalTopicMinistry = 0;
    		int iTotalTopicFoundation = 0;
    		int iTotalTopicInternational = 0;
    		int iTotalTopicUniversity = 0;
    		
    		String staffName = "";
    		List<List<String>> summaryPapersList = new ArrayList<>();
    		List<List<String>> summaryProjectsList = new ArrayList<>();
    		Map<String, List<Papers>> summaryAllStaffsList = new HashMap<String, List<Papers>>(); 
    		
		    if(staffs != null && papersList != null){
		    	
		    	for(Staff staff : staffs){
		    		// Set and reset info for a staff
		    		iTotalPaperConvertedHours = 0;
		    		iTotalPaperJournal = 0;
		    		iTotalPaperConferenceJournal = 0;
		    		
		    		iTotalTopicConvertedHours = 0;
		    		iTotalTopicNational = 0;
		    		iTotalTopicMinistry = 0;
		    		iTotalTopicFoundation = 0;
		    		iTotalTopicInternational = 0;
		    		iTotalTopicUniversity = 0;
		    		
		    		staffName = staff.getStaff_Name();
		    		List<String> summaryPaper = new ArrayList<>();
		    		List<String> summaryTopic = new ArrayList<>();
		    		List<Papers> summaryStaff = new ArrayList<>();
		    		// Set data papers
		    		for(Papers paper : papersList)
		    		{
		    			if(staff.getStaff_User_Code().equals(paper.getPDECL_User_Code())){
		    				iTotalPaperConvertedHours += paper.getPDECL_AuthorConvertedHours();
		    				if(paper.getPDECL_PaperCategory_Code().equals("JINT_SCI") || paper.getPDECL_PaperCategory_Code().equals("JINT_Other") || paper.getPDECL_PaperCategory_Code().equals("JINT_SCIE"))
		    				{
		    					iTotalPaperJournal++;
		    				}else{
		    					iTotalPaperConferenceJournal++;
		    				}
		    				summaryStaff.add(paper);
		    			}
		    		}
		    		// Build a paper summary
		    		summaryPaper.add(staffName);
		    		summaryPaper.add(Integer.toString(iTotalPaperJournal));
		    		summaryPaper.add(Integer.toString(iTotalPaperConferenceJournal));
		    		summaryPaper.add(Integer.toString(iTotalPaperConvertedHours));
		    		
		    		// Build list of paper summary
		    		summaryPapersList.add(summaryPaper);
		    		
		    		// Build list of staff summary 
		    		if(summaryStaff != null){
		    			summaryAllStaffsList.put(staffName, summaryStaff);
		    		}
		    		
		    		// Set data topics
		    		for(Topics topic : topicsList){
		    			if(staff.getStaff_User_Code().equals(topic.getPROJDECL_User_Code())){
		    				iTotalTopicConvertedHours += topic.getPROJDECL_AuthorConvertedHours();
		    				if(topic.getPROJDECL_ProjCategory_Code().equals("NATIONAL")){
		    					iTotalTopicNational++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("EMINISTRY")){
		    					iTotalTopicMinistry++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("SMINISTRY")){
		    					iTotalTopicFoundation++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("INTERNATIONAL")){
		    					iTotalTopicInternational++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("UNIVERSTITY")){
		    					iTotalTopicUniversity++;
		    				}
		    			}
		    		}
		    		summaryTopic.add(staffName);
		    		summaryTopic.add(Integer.toString(iTotalTopicNational));
		    		summaryTopic.add(Integer.toString(iTotalTopicMinistry));
		    		summaryTopic.add(Integer.toString(iTotalTopicFoundation));
		    		summaryTopic.add(Integer.toString(iTotalTopicInternational));
		    		summaryTopic.add(Integer.toString(iTotalTopicUniversity));
		    		summaryTopic.add("");
		    		summaryTopic.add(Integer.toString(iTotalTopicConvertedHours));
		    		summaryProjectsList.add(summaryTopic);
		    	}
		    }
		    
		    /**
		     * Put data to view
		     */
		    model.put("summaryPapersList", summaryPapersList);
		    model.put("summaryProjectsList", summaryProjectsList);
		    model.put("summaryAllStaffsList", summaryAllStaffsList);
			model.put("yearOfPaper", yearForGenerating);
			if(department != null){
			    model.put("staffDepartementName",department.getDepartment_Name());
			}
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelSummaryView", "topicsList", topicsList);
	     }
	}
	
	/**
    * Adding a topic
    * @param model
    * @return
    */
   @RequestMapping(value = "/summary", method = RequestMethod.GET)
   public String generateASummary(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   List<Department> departmentList = departmentService.loadDepartmentList();
	   
	   /**
	    * Put back to a suitable view
	    */
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("departmentList", departmentList);
	   model.put("summaryExcelForm", new SummaryExcelValidation());
       return "cp.generateSummary";
   }

}
