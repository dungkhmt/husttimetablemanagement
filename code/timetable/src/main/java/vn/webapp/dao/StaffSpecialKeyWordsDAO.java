/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import vn.webapp.model.StaffSpecializationKeywords;

public interface StaffSpecialKeyWordsDAO {
	
	public StaffSpecializationKeywords getStaffSpecializationKeywordsByStaffAndCode(String sUserCode, String sKeywordCode);
	
    public int saveAStaffSpecializationKeywords(StaffSpecializationKeywords staffSpecializationKeywords);
    
    //public int removeAMasterThesis(int MasterThesisId);
    
}
