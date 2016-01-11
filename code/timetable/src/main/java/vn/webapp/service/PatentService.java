/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import vn.webapp.model.Patents;

public interface PatentService {

    public List<Patents> loadPatentListByStaff(String userRole, String userCode);
    
    public List<Patents> loadPatentListByYear(String userRole, String userCode, String reportingrYear);
    
    public int saveAPatent(String userCode, String patentName, int patentConVertedHours, int patentAutConHours, 
							String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate);
    
    public Patents loadAPatentByIdAndUserCode(String userRole, String userCode, int patentId);
    
    public void editAPatent(String userRole, String userCode, int patentId, String patentName, int patentConVertedHours, 
    						int patentAutConHours, String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate);
    
    public int removeAPatent(int patentId);
    
}
