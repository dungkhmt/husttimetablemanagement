package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.JuryMember;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("JuryMemberDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class JuryMemberDAOImpl extends BaseDao implements JuryMemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<JuryMember> listJuryMembers(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryMem_StaffCode", staffCode));
			List<JuryMember> listJuryMembers = criteria.list();
			commit();
			return listJuryMembers;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	
	@Override
	public JuryMember getAJuryMemberByCode(String sJuryMemberCode, String sStaffCode){
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_Code", sJuryMemberCode));
			criteria.add(Restrictions.eq("JuryMem_StaffCode", sStaffCode));
			JuryMember aJuryMember = (JuryMember)criteria.uniqueResult();
			commit();
			return aJuryMember;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public List<JuryMember> listJuryMembers(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_StaffCode", staffCode));
			List<JuryMember> listJuryMembers = criteria.list();
			commit();
			return listJuryMembers;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public int saveJuryMember(JuryMember juryMember){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(juryMember);
            commit();
            return id;           
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
             return 0;
         } finally {
             flush();
             close();
         }
	}
	
	@Override
	public int removeAJuryMemberByCode(JuryMember aJuryMember){
		try {
            begin();
            getSession().delete(aJuryMember);
            commit();
            return 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
	}
	
}
