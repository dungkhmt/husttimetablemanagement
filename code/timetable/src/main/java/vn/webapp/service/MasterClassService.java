package vn.webapp.service;

import java.util.List;

import vn.webapp.model.MasterClass;

public interface MasterClassService {
	
	  public List<MasterClass> loadMasterClassList();
	  
	  public MasterClass loadMasterClassByCode(String masterClassesCode);

}
