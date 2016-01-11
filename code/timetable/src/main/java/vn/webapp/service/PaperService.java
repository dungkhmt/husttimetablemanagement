/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;
import vn.webapp.model.Papers;

public interface PaperService {

    public List<Papers> loadPaperListByStaff(String userRole, String userCode);
    
    public List<Papers> loadPaperListByYear(String userRole, String currentUserCode, String reportingrYear);
    
    public List<Papers> loadPaperSummaryListByYear(String reportingrYear);
    
    public int saveAPaper(String currentUserName, String paperCatCode, String paperPubName, String paperJConfName, String paperISSN, int paperPubConHours, 
    						int paperAutConHours, int paperYear, String paperJIndexCode, int paperVolumn, String paperAuthors, String paperReportingAcademicDate, String paperSourceUploadFile);
    
    public Papers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId);
    
    public void editAPaper(String userRole, String userCode, int paperId, String paperCate, String publicationName, String journalName, 
							String ISSN, int publicConvertedHours, int authorConvertedHours, int paperYear, 
							int volumn, String authors, String journalIndex, String paperReportingAcademicDate, String paperSourceUploadFile );
    
    public int removeAPaper(int paperId);
    
}
