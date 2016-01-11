package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.JuryRoom;
import vn.webapp.model.JurySlot;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("JurySlotDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class JurySlotDAOImpl extends BaseDao implements JurySlotDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<JurySlot> listJurySlots(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JurySlot_StaffCode", staffCode));
			List<JurySlot> listJurySlots = criteria.list();
			commit();
			return listJurySlots;
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
	public List<JurySlot> listJurySlots(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_StaffCode", staffCode));
			List<JurySlot> listJurySlots = criteria.list();
			commit();
			return listJurySlots;
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
	public JurySlot getJurySlotByCode(String sJurySlotCode, String defenseSessionCode, String userCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_Code", sJurySlotCode));
			criteria.add(Restrictions.eq("JurySlot_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JurySlot_StaffCode", userCode));
			JurySlot jurySlot = (JurySlot)criteria.uniqueResult();
			commit();
			return jurySlot;
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
	public JurySlot getJurySlotByUserCode(String sJurySlotCode, String userCode)
	{
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_Code", sJurySlotCode));
			criteria.add(Restrictions.eq("JurySlot_StaffCode", userCode));
			JurySlot jurySlot = (JurySlot)criteria.uniqueResult();
			commit();
			return jurySlot;
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
	public int saveJurySlot(JurySlot jurySlot){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(jurySlot);
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
	public int removeAJurySlot(JurySlot jurySlot){
		try {
            begin();
            getSession().delete(jurySlot);
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
