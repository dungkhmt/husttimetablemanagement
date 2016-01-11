/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.HashSet;
import java.util.List;

import vn.webapp.model.ShowedViewMasterDefenseThesis;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Student;

public interface MasterDefenseJuryService {
    
    public List<ShowedViewMasterDefenseThesis> getListMasterDefenseJuryThesis();
    
    public List<ShowedViewMasterDefenseThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode);
    
    public boolean updateAMasterDefenseJuryThesis(String[] masterDefenseThesis, String[] defenseder01, String[] defenseder02, String[] president, String[] secretary, 
    										String[] commissioner, String[] slot, String[] room, String[] no, String userCode, String sessionCode);

    public int saveAMasterThesis(String sMasterThesisCode, String sDefenseSessionCode, String userCode);
    
    public int removeAMasterThesis(String userCode, String sMasterThesisCode);
    
}
