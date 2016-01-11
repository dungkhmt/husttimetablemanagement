package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.University;


public interface UniversityDAO {

    public List<University> loadUniversityList();
    
    public University loadAUniversityByCodes(String universityCode);
    
}
