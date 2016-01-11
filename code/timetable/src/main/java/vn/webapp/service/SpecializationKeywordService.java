/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;
import java.util.Set;

import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;

public interface SpecializationKeywordService {

    public List<SpecializationKeyword> loadSpecializationKeywordList();    
    public List<SpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code);
    public SpecializationKeyword getSpecializationKeywordByCode(String KW_Code);
    public List<SpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode);
    
}
