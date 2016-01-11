/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.PaperCategoryDAO;
import vn.webapp.model.PaperCategory;

@Service("paperCategoryService")
public class PaperCategoryServiceImpl implements PaperCategoryService{

    @Autowired
    private PaperCategoryDAO paperCategoryDAO;

    public void setPaperCategoryDAO(PaperCategoryDAO paperCategoryDAO) {
        this.paperCategoryDAO = paperCategoryDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<PaperCategory> list(){
    	return paperCategoryDAO.getList();
    }
    
    /**
     * get a paper category by code
     * @param String
     * @return object
     */
    @Override
    public PaperCategory getPaperCateByCode(String paperCateCode){
    	if(paperCateCode != null){
    		return paperCategoryDAO.getPaperCateByCode(paperCateCode);
    	}
    	return null;
    }
}
