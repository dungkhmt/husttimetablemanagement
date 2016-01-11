/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;








/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.controller.cp.Book;
import vn.webapp.model.AcademicYear;
import vn.webapp.model.Department;
import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.Staff;
import vn.webapp.service.AcademicYearService;
import vn.webapp.service.DepartmentService;
import vn.webapp.service.JournalService;
import vn.webapp.service.PaperCategoryService;
import vn.webapp.service.PaperService;
import vn.webapp.service.StaffService;
import vn.webapp.service.UserService;
import vn.webapp.validation.PaperExcellValidation;
import vn.webapp.validation.PaperValidation;

@Controller("cpPaper")
@RequestMapping(value = {"/cp"})
public class PaperController extends BaseWeb {

    @Autowired
    private PaperService paperService;
    
    @Autowired
    private PaperCategoryService paperCategoryService;
    
    @Autowired
    private JournalService journalService;
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private AcademicYearService academicYearService;
    
    @Autowired
    private UserService userService;
    
    static final String status = "active";
    
    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
    
    /**
    * Show list all papers
    * @param model
    * @return
    */
   @RequestMapping(value = "/papers", method = RequestMethod.GET)
   public String paperList(ModelMap model, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   List<Papers> papersList = paperService.loadPaperListByStaff(userRole, userCode);
	   model.put("papersList", papersList);
	   model.put("papers", status);
	   return "cp.papers";
   }
   
   
   /**
    * Adding a paper
    * @param model
    * @return
    */
   @RequestMapping(value = "/add-a-paper", method = RequestMethod.GET)
   public String addPaper(ModelMap model, HttpSession session) {
	  
	   /*
	    * Get current user name and role
	    */
	   String currentUserName = session.getAttribute("currentUserName").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   
	   /*
	    * Get paper's category
	    */
	   List<PaperCategory> paperCategory = paperCategoryService.list();
	   List<Journal> journalList = journalService.list();
	   String paperConvertedHours = this.setJsonByListPaperCategory(paperCategory);
	   
	   // Get list reportingYear
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   
	   /*
	    * Put data back to view
	    */
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("paperConvertedHours", paperConvertedHours);
	   model.put("paperCategory", paperCategory);
	   model.put("journalList", journalList);
	   model.put("paperFormAdd", new PaperValidation());
	   model.put("papers", status);
       return "cp.addAPaper";
   }
   
   /**
    * Convert a List of PaperCategory into a Json sequence
    * @param theListPaperCategory
    * @return
    */
   public String setJsonByListPaperCategory(List<PaperCategory> theListPaperCategory) {
	   /*
	    * Set a hashmap for holding paper list by key - value pairs
	    */
	   HashMap<String, Integer> paperConvertedHours = new HashMap<String, Integer>();
	   if(theListPaperCategory != null){
		   for(PaperCategory paperCat : theListPaperCategory){
			   paperConvertedHours.put(paperCat.getPCAT_Code(), paperCat.getPCAT_ConvertedHours());
		   }
		   
		   Gson gson = new Gson(); 
		   String jsonpaperConvertedHours = gson.toJson(paperConvertedHours); 
		   return jsonpaperConvertedHours;
	   }
	   return "";
   }
   
   /**
    * Save a paper
    * @param paperValid
    * @param result
    * @param model
    * @param session
    * @return String
    */
   @RequestMapping(value="save-a-paper", method=RequestMethod.POST)
   public String saveAnUser(HttpServletRequest request, @Valid @ModelAttribute("paperFormAdd") PaperValidation paperValid, BindingResult result,  Map model, HttpSession session) {
	   /*
	    * Get list of paper category and journalList
	    */
	   List<PaperCategory> paperCategory = paperCategoryService.list();
	   List<Journal> journalList = journalService.list();
	   String paperConvertedHours = this.setJsonByListPaperCategory(paperCategory);
	   
	   // Get list reportingYear
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   
	   /*
	    * Put data back to view
	    */
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("paperConvertedHours", paperConvertedHours);
	   model.put("paperCategory", paperCategory);
	   model.put("journalList", journalList);
	   model.put("papers", status);
	   if(result.hasErrors()) {
           return "cp.addAPaper";
       }else
       {
    	   /*
    	    * Prepare data for inserting DB
    	    */
    	   String paperCatCode = paperValid.getPaperCatCode();
    	   PaperCategory paperCate = paperCategoryService.getPaperCateByCode(paperCatCode);
    	   
    	   String paperAuthors 			= paperValid.getPaperAuthorList();
    	   String[] paperAuthorsList 	= paperAuthors.trim().split("\\,");
    	   Integer numberOfAuthors 		= paperAuthorsList.length;
    	   for(int i=0; i<paperAuthorsList.length; i++){
    	     if(paperAuthorsList[i].equals("")){
    	    	 numberOfAuthors--;
    	     }
    	   }
    	   /**
    	    * Uploading file
    	    */
    	   MultipartFile paperSourceUploadFile = paperValid.getPaperFileUpload();
    	   String fileName = paperSourceUploadFile.getOriginalFilename();
    	   String paperSourceUploadFileSrc = "";
    	   try {
    		   //Creating Date in java with today's date.
    		   Date currentDate = new Date();
    		   //change date into string yyyyMMdd format example "20110914"
    		   SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("HHmmssddMMyyyy");
    		   String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
    			   
	    	   byte[] bytes = paperSourceUploadFile.getBytes();
	    	   String path = request.getServletContext().getRealPath("uploads");
	    	   File dir = new File(path+ "/papers");
	           if (!dir.exists()){
	        	   dir.mkdirs();
	           }
	           
               // Create a file
	           String currentUserName 	= session.getAttribute("currentUserName").toString();
	           fileName = currentUserName + "_" + sCurrentDate + "_" + fileName; 
               File serverFile = new File(dir.getAbsolutePath()+ File.separator + fileName);
               if(serverFile.exists()){
            	   paperSourceUploadFileSrc = dir.getAbsolutePath()+ File.separator + fileName;
               }
               // Save data into file
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
               stream.write(bytes);
               stream.close();
               
	           /**
	            * Preparing data for adding into DB
	            */
	    	   
	    	   String paperPubName 		= paperValid.getPaperPubName();
	    	   String paperJConfName 	= paperValid.getPaperJConfName();
	    	   String paperISSN 		= paperValid.getPaperISSN();
	    	   String paperJIndexCode 	= paperCate.getPCAT_Journal();
	    	   String paperReportingAcademicDate = paperValid.getPatentReportingAcademicDate();
	    	   
	    	   int paperPubConHours 	= (!paperCate.getPCAT_ConvertedHours().equals(null)) ? paperCate.getPCAT_ConvertedHours() : paperValid.getPaperPubConHours();
	    	   int paperAutConHours 	= (!numberOfAuthors.equals(0)) ? (int) Math.round(paperPubConHours/numberOfAuthors) : 0;
	    	   int paperYear 			= paperValid.getPaperYear();
	    	   int paperVolumn 			= paperValid.getPaperVolumn();
	    	   
	    	   int i_InsertAPaper = paperService.saveAPaper(currentUserName, paperCatCode, paperPubName, paperJConfName, paperISSN, paperPubConHours, paperAutConHours, 
	    			   											paperYear, paperJIndexCode, paperVolumn, paperAuthors, paperReportingAcademicDate, paperSourceUploadFileSrc);
	    	   if(i_InsertAPaper > 0){
	    		   model.put("status", "Successfully saved a paper: ");
	    	   }
    	   }catch (Exception e) {
    		   model.put("status", "You failed to upload " + fileName + " => " + e.getMessage());
           }
           return "cp.addAPaper";
       }
   }
   
   	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
	public ModelAndView downloadExcel(@Valid @ModelAttribute("paperExcellForm") PaperExcellValidation paperValidExcell, BindingResult result, Map model, HttpSession session) {
		// Get list reportingYear
	    List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
		model.put("papers", status);
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generate");
	     }else
	     {
			String yearForGenerating = paperValidExcell.getReportingAcademicDate();
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    List<Papers> papersList = paperService.loadPaperListByYear(userRole, currentUserCode, yearForGenerating);
		    
			Staff staff = staffService.loadStaffByUserCode(currentUserCode);
			model.put("yearOfPaper", paperValidExcell.getReportingAcademicDate());
			if(staff != null){
			    List<Department> departmentList = departmentService.loadDepartmentList();
			    model.put("staffEmail", staff.getStaff_Email());
			    model.put("staffName", staff.getStaff_Name());
			    model.put("staffPhone", staff.getStaff_Phone());
			    model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
			    model.put("staffDepartementName", staff.getDepartment().getDepartment_Name());
			    model.put("staffDepartementCode", staff.getDepartment().getDepartment_Code());
			}
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelView", "papersList", papersList);
	     }
	}
	
	/**
	 * Handle request to download an Excel 2007 or later document 
	 */
	@RequestMapping(value = "/paperExcel2007", method = RequestMethod.POST)
	public ModelAndView downloadExcelXSSF(@Valid @ModelAttribute("paperExcellForm") PaperExcellValidation paperValidExcell, BindingResult result,  Map model, HttpSession session) {
		// create some sample data
		List<Book> listBooks = new ArrayList<Book>();
		listBooks.add(new Book("Effective Java", "Joshua Bloch", "0321356683", "May 28, 2008", 38.11F));
		listBooks.add(new Book("Head First Java", "Kathy Sierra & Bert Bates", "0596009208", "February 9, 2005", 30.80F));
		listBooks.add(new Book("Java Generics and Collections", "Philip Wadler", "0596527756", "Oct 24, 2006", 29.52F));
		listBooks.add(new Book("Thinking in Java", "Bruce Eckel", "0596527756", "February 20, 2006", 43.97F));
		listBooks.add(new Book("Spring in Action", "Craig Walls", "1935182358", "June 29, 2011", 31.98F));
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("excelXssfView", "listBooks", listBooks);
	}
	
	
	/**
    * Adding a paper
    * @param model
    * @return
    */
   @RequestMapping(value = "/gen-a-paper", method = RequestMethod.GET)
   public String generateAPaper(ModelMap model, HttpSession session) {
	   
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("papers", status);
	   model.put("paperExcellForm", new PaperExcellValidation());
       return "cp.generate";
   }
   
   @RequestMapping("/paperdetail/{id}")
   public String editAPaper(ModelMap model, @PathVariable("id") int paperId, HttpSession session) {
	   
	   // Get list reportingYear
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   
	   /*
	    * Put data back to view
	    */
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("papers", status);
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   Papers papers = paperService.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
	   if(papers != null)
	   {
		   List<PaperCategory> paperCategory = paperCategoryService.list();
		   List<Journal> journalList = journalService.list();
		   String paperConvertedHours = this.setJsonByListPaperCategory(paperCategory);
		   /*
		    * Put journal list and paper category to view
		    */
		   model.put("paperConvertedHours", paperConvertedHours);
		   model.put("paperCategory", paperCategory);
		   model.put("journalList", journalList);
		   model.put("paperFormEdit", new PaperValidation());
		   
		   model.put("paperCate", papers.getPDECL_PaperCategory_Code());
		   model.put("publicationName", papers.getPDECL_PublicationName());
		   model.put("journalName", papers.getPDECL_JournalConferenceName());
		   model.put("ISSN", papers.getPDECL_ISSN());
		   model.put("publicConvertedHours", papers.getPDECL_PublicationConvertedHours());
		   model.put("authorConvertedHours", papers.getPDECL_AuthorConvertedHours());
		   model.put("paperYear", papers.getPDECL_Year());
		   model.put("volumn", papers.getPDECL_Volumn());
		   model.put("authors", papers.getPDECL_AuthorList());
		   model.put("journalIndex", papers.getPDECL_IndexCode());
		   model.put("reportingDate", papers.getPDECL_ReportingAcademicDate());
		   model.put("paperId", paperId);
		   return "cp.editAPaper";
	   }
	   return "cp.notFound404";
   }
   
   /**
    * Adding a paper
    * @param model
    * @return
    */
   @RequestMapping(value = "/edit-a-paper", method = RequestMethod.POST)
   public String updateAPaper(HttpServletRequest request, @Valid @ModelAttribute("paperFormEdit") PaperValidation paperFormEdit, BindingResult result, Map model, HttpSession session) {
	   
	   List<PaperCategory> paperCategories = paperCategoryService.list();
	   List<Journal> journalList = journalService.list();
	   String paperConvertedHours = this.setJsonByListPaperCategory(paperCategories);
	   
	   // Get list reportingYear
	   List<AcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   
	   /*
	    * Put data back to view
	    */
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("paperConvertedHours", paperConvertedHours);
	   model.put("paperCategory", paperCategories);
	   model.put("journalList", journalList);
	   model.put("papers", status);
	   // Add the saved validationForm to the model  
      if (result.hasErrors()) {
    	   model.put("paperCate", paperFormEdit.getPaperCatCode());
		   model.put("publicationName", paperFormEdit.getPaperPubName());
		   model.put("journalName", paperFormEdit.getPaperJConfName());
		   model.put("ISSN", paperFormEdit.getPaperISSN());
		   model.put("publicConvertedHours", paperFormEdit.getPaperPubConHours());
		   model.put("authorConvertedHours", paperFormEdit.getPaperAutConHours());
		   model.put("paperYear", paperFormEdit.getPaperYear());
		   model.put("volumn", paperFormEdit.getPaperVolumn());
		   model.put("authors", paperFormEdit.getPaperAuthorList());
		   model.put("journalIndex", paperFormEdit.getPaperJIndexCode());
		   model.put("reportingDate", paperFormEdit.getPatentReportingAcademicDate());
		   
           return "cp.editAPaper";
      }else
      {
    	   /**
   	       * Uploading file
	   	   */
	   	   MultipartFile paperSourceUploadFile = paperFormEdit.getPaperFileUpload();
	   	   String fileName = paperSourceUploadFile.getOriginalFilename();
	   	   String paperSourceUploadFileSrc = "";
	   	   String userRole = session.getAttribute("currentUserRole").toString();
	   	   String userCode = session.getAttribute("currentUserCode").toString();
	   	   String paperCate = paperFormEdit.getPaperCatCode();
	   	   try {
	   		   	//	Creating Date in java with today's date.
	   		   	Date currentDate = new Date();
	   		   	//change date into string yyyyMMdd format example "20110914"
	   		   	SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("HHmmssddMMyyyy");
	   		   	String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
    		   
	    	   	byte[] bytes = paperSourceUploadFile.getBytes();
	    	   	String path = request.getServletContext().getRealPath("uploads");
	    	   	File dir = new File(path+ "/papers");
	           	if (!dir.exists()){
	        	   dir.mkdirs();
	           	}
	           
	           	if(!fileName.equals("")){
	        	   // Create a file
	           	   String currentUserName 	= session.getAttribute("currentUserName").toString();
	 	           fileName = currentUserName + "_" + sCurrentDate + "_" + fileName; 
		           File serverFile = new File(dir.getAbsolutePath()+ File.separator + fileName);
		           // Save data into file
		           BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		           stream.write(bytes);
		           stream.close();
		           
		           if(serverFile.exists()){
	           	   		paperSourceUploadFileSrc = dir.getAbsolutePath()+ File.separator + fileName;
		           }
	           	}
	           
	    	  	/**
	    	  	 * Prepare data for inserting DB
	    	  	 */
		   	  	PaperCategory paperCategory = paperCategoryService.getPaperCateByCode(paperCate);
	   	   
		   	  	String authors = paperFormEdit.getPaperAuthorList();
	   	   		String[] paperAuthorsList 	= authors.trim().split("\\,");
	   	   		Integer numberOfAuthors 		= paperAuthorsList.length;
	   	   		for(int i=0; i<paperAuthorsList.length; i++){
		   		   if(paperAuthorsList[i].equals("")){
		   			   numberOfAuthors--;
		   		   }
	   	   		}
	   	   
	   	   		String publicationName = paperFormEdit.getPaperPubName();
	   	   		String journalName = paperFormEdit.getPaperJConfName();
	   	   		String ISSN = paperFormEdit.getPaperISSN();
	   	   		String paperReportingAcademicDate = paperFormEdit.getPatentReportingAcademicDate();
	   	   		int publicConvertedHours = (!paperCategory.getPCAT_ConvertedHours().equals(null)) ? paperCategory.getPCAT_ConvertedHours() : paperFormEdit.getPaperPubConHours();
	   	   		int authorConvertedHours = (!numberOfAuthors.equals(0)) ? (int) Math.round(publicConvertedHours/numberOfAuthors) : 0;
	   	   		int paperYear = paperFormEdit.getPaperYear();
	   	   		int volumn = paperFormEdit.getPaperVolumn();
	    	  
	   	   		String journalIndex = paperCategory.getPCAT_Journal();
	   	   		int paperId = paperFormEdit.getPaperId();
	          
	   	   		paperService.editAPaper(userRole, userCode, paperId, paperCate, publicationName, journalName, ISSN, publicConvertedHours, authorConvertedHours, paperYear, volumn, authors, journalIndex, paperReportingAcademicDate, paperSourceUploadFileSrc);
	   	   		model.put("status", "Successfully edited paper");
	   	   }catch (Exception e) {
   		   		model.put("status", "You failed to upload " + fileName + " => " + e.getMessage());
	   	   }
	   	   return "cp.editAPaper";
      }
   }
   
   /**
    * Show list all papers
    * @param model
    * @return
    */
   @RequestMapping(value = "/remove-a-paper/{id}", method = RequestMethod.GET)
   public String removeAPaper(ModelMap model, @PathVariable("id") int paperId, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   model.put("papers", status);
	   Papers paper = paperService.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
	   if(paper != null){
		   paperService.removeAPaper(paperId);
		   List<Papers> papersList = paperService.loadPaperListByStaff(userRole, userCode);
		   model.put("papersList", papersList);
		   return "cp.papers";
	   }
	   return "cp.notFound404";
   }
   
   /**
    * Show list all papers
    * @param model
    * @return
    */
   @RequestMapping(value = "/download-paper/{id}", method = RequestMethod.GET)
   public String downloadPaper(ModelMap model, @PathVariable("id") int paperId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   model.put("papers", status);
	   Papers paper = paperService.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
	   if(paper.getPDECL_SourceFile() != null){
		   ServletContext context = request.getServletContext();
		   
		   File downloadFile = new File(paper.getPDECL_SourceFile());
		   if(downloadFile.exists()){
		       FileInputStream inputStream = new FileInputStream(downloadFile);
		       
		       String mimeType = context.getMimeType(paper.getPDECL_SourceFile());
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		        
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		        
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		        
		        // get output stream of the response
		        OutputStream outStream = response.getOutputStream();
		 
		        byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
		   }
	   }
	   return "redirect:" + this.baseUrl + "/cp/papers.html";
   }

}
