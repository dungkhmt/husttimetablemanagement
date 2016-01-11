/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Papers;
import vn.webapp.model.Users;

public interface PaperDAO {

    public List<Papers> loadPaperListByStaff(String userRole, String userCode);
    
    public List<Papers> loadPaperListByYear(String userRole, String userCode, String reportingrYear);
    
    public List<Papers> loadPaperSummaryListByYear(String reportingrYear);
    
    public int saveAPaper(Papers paper);
    
    public Papers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId);
    
    public void editAPaper(Papers paper);
    
    public int removeAPaper(int paperId);
    
}
