/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import vn.webapp.model.AcademicYear;

@Repository("academicYearDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AcademicYearDAOImpl extends BaseDao implements AcademicYearDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<AcademicYear> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(AcademicYear.class);
            criteria.setFirstResult(0);
            List<AcademicYear> academicYear = criteria.list();
            commit();
            return academicYear;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
    
}
