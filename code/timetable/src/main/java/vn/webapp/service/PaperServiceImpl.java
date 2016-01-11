/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.dao.PaperDAO;
import vn.webapp.dao.UserDAO;
import vn.webapp.model.Papers;
import vn.webapp.model.Users;

@Service("paperService")
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperDAO paperDAO;

    @Autowired
    private UserDAO userDAO;
    
    /**
     * Get a list Papers by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Papers> loadPaperListByStaff(String userRole, String userCode) {
        try {
        	return paperDAO.loadPaperListByStaff(userRole, userCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Papers by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Papers> loadPaperListByYear(String userRole, String currentUserCode, String reportingrYear){
    	try {
        	if(currentUserCode != null){
        		return paperDAO.loadPaperListByYear(userRole, currentUserCode, reportingrYear);
        	}
        	return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Papers Summary by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Papers> loadPaperSummaryListByYear(String reportingrYear){
    	try {
        	if(reportingrYear != null){
        		return paperDAO.loadPaperSummaryListByYear(reportingrYear);
        	}
        	return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a paper
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    public int saveAPaper(String currentUserName, String paperCatCode, String paperPubName, String paperJConfName, String paperISSN, int paperPubConHours, 
    						int paperAutConHours, int paperYear, String paperJIndexCode, int paperVolumn, String paperAuthors, String paperReportingAcademicDate, String paperSourceUploadFile)
    {
    	Users user = userDAO.getByUsername(currentUserName);
    	if(user.getUser_Code()  != null){
    		Papers paper = new Papers();
            paper.setPDECL_PaperCategory_Code(paperCatCode);
            paper.setPDECL_User_Code(user.getUser_Code());
            paper.setPDECL_PublicationName(paperPubName);
            paper.setPDECL_JournalConferenceName(paperJConfName);
            paper.setPDECL_Volumn(paperVolumn);
            paper.setPDECL_Year(paperYear);
            paper.setPDECL_ISSN(paperISSN);
            paper.setPDECL_IndexCode(paperJIndexCode);
            paper.setPDECL_PublicationConvertedHours(paperPubConHours);
            paper.setPDECL_AuthorConvertedHours(paperAutConHours);
            paper.setPDECL_AuthorList(paperAuthors);
            paper.setPDECL_ReportingAcademicDate(paperReportingAcademicDate);
            paper.setPDECL_SourceFile(paperSourceUploadFile);
            int i_SaveAPaper = paperDAO.saveAPaper(paper);
            return i_SaveAPaper;
    	}
        return 0;
    }
    
    /**
     * load a paper by usercode and it's id
     * @param String
     * @param int
     * @return object
     */
    @Override
    public Papers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId){
    	try {
    		return paperDAO.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a paper
     * @param String
     * @param int
     * @return null
     */
    @Override
    public void editAPaper(String userRole, String userCode, int paperId, String paperCate, String publicationName, String journalName, 
    						String ISSN, int publicConvertedHours, int authorConvertedHours, int paperYear, 
    						int volumn, String authors, String journalIndex, String paperReportingAcademicDate, String paperSourceUploadFile ){
    	Papers paper = paperDAO.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
    	if(paper != null){
	    	paper.setPDECL_ID(paperId);;
	    	paper.setPDECL_PaperCategory_Code(paperCate);;
	    	paper.setPDECL_AuthorConvertedHours(authorConvertedHours);
	    	paper.setPDECL_ISSN(ISSN);
	    	paper.setPDECL_PublicationConvertedHours(publicConvertedHours);
	    	paper.setPDECL_User_Code(userCode);
	    	paper.setPDECL_Volumn(volumn);
	    	paper.setPDECL_Year(paperYear);
	    	paper.setPDECL_JournalConferenceName(journalName);
	    	paper.setPDECL_IndexCode(journalIndex);
	    	paper.setPDECL_PublicationName(publicationName);
	    	paper.setPDECL_AuthorList(authors);
	    	paper.setPDECL_ReportingAcademicDate(paperReportingAcademicDate);
	    	if(paperSourceUploadFile.equals(""))
	    	{
	    		paper.setPDECL_SourceFile(paper.getPDECL_SourceFile());
	    	}else{
	    		
	    		String sOldSourceFile = paper.getPDECL_SourceFile();
		   		if((sOldSourceFile != null)){
		   			File oldFile = new File(sOldSourceFile);
			   		oldFile.delete();
		   		}
		   		paper.setPDECL_SourceFile(paperSourceUploadFile);
	    	}
	    	paperDAO.editAPaper(paper);
    	}
    }
    
    /**
     * Remove a paper
     * @param int
     * @return int
     */
    @Override
    public int removeAPaper(int paperId){
    	return paperDAO.removeAPaper(paperId);
    }
    
}
