/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.MasterDefenseJuryThesis;
import vn.webapp.model.RawMasterDefenseJuryThesis;

public interface MasterDefenseJuryDAO {

	public List<MasterDefenseJuryThesis> getListMasterDefenseJuryThesis();
	
	public List<MasterDefenseJuryThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode);
	
	public MasterDefenseJuryThesis getMasterDefenseJuryThesisByIdAndOwner(String masterDefenseJuryCode, String ownerCode);
	
	public MasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode);
	
	public void updateAMasterDefenseJuryThesis(MasterDefenseJuryThesis masterDefenseJuryThesis);
	
	public int saveAMasterThesis(MasterDefenseJuryThesis masterDefenseJuryThesis);
	
	public RawMasterDefenseJuryThesis getRawMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode);
	
	public int removeAMasterThesis(RawMasterDefenseJuryThesis masterDefenseJuryThesis);
	
}
