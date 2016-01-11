/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Patents;

public interface PatentDAO {

    public List<Patents> loadPatentListByStaff(String userRole, String userCode);
    
    public List<Patents> loadPatentListByYear(String userRole, String userCode, String reportingrYear);
    
    public int saveAPatent(Patents patent);
    
    public Patents loadAPatentByIdAndUserCode(String userRole, String userCode, int patentId);
    
    public void editAPatent(Patents patent);
    
    public int removeAPatent(int patentId);
    
}
