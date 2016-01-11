/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.MasterThesis;
import vn.webapp.model.RawMasterThesis;

public interface MasterThesisDAO {

	public List<MasterThesis> listMasterThesis();
	
	public MasterThesis getMasterThesisById(int MasterThesis_Id);
	
	public void editAMasterThesis(MasterThesis MasterThesis);
    
    public int saveAMasterThesis(RawMasterThesis MasterThesis);
    
    public int removeAMasterThesis(int MasterThesisId);
    
}
