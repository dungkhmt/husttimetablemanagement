package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.MasterClass;

public interface MasterClassDAO {

	public List<MasterClass> listMasterClass();
	
	public MasterClass loadMasterClassByCode(String masterClassCode);
	
}
