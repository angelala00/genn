package com.cjteam.xiao.dao.impl;

import com.cjteam.xiao.dao.Base1Dao;
import com.cjteam.xiao.util.JPQLUtil;
import com.cjteam.xiao.util.Where;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class Base1DaoImpl<T>  implements Base1Dao<T> {
	private  Class modelClass ;
	{
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.modelClass =  (Class) params[0];
	}
	private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	private JPQLUtil jPQLUtil;

    public T get(int id){
        return (T) this.entityManager.find(this.modelClass, id);
    }
	public List<T> getList(Where where , int limit , int limit1){
    	jPQLUtil = new JPQLUtil(this.modelClass) ;
		if (where != null){
			this.jPQLUtil.setWhere(where);
		}
        String sql = this.jPQLUtil.select().toString() ;
		Query query = entityManager.createQuery(sql) ;
		
		if (limit>0){
			query.setFirstResult(limit);
		}
		if (limit1 >0){
			query.setMaxResults(limit1);
		}
    	return query.getResultList() ;
    }
    public Long  getCount(Where where){
    	jPQLUtil = new JPQLUtil(this.modelClass) ;
    	String value  ; 
    	if (where != null){
			this.jPQLUtil.setWhere(where);
		}
		Query query = entityManager.createQuery(this.jPQLUtil.selectCount().toString()) ; 
		@SuppressWarnings("unchecked")
		List<Long> list  = query.getResultList() ; 
		if (list.size()>0){
			return  list.get(0) ;
		}
		else{
			return (long) 0 ;
		}
	}
    public  PageImpl<T> getPage(Pageable pageable , Where where ){
    	return new PageImpl<T>(this.getList(where, pageable.getPageNumber() * pageable.getPageSize() , pageable.getPageSize()), pageable, this.getCount(where));
    }
    @Transactional
    public void update(Map<String, String> data , Where where  ){
    	jPQLUtil = new JPQLUtil(this.modelClass) ;
    	jPQLUtil.setWhere(where) ;
    	System.out.println(this.jPQLUtil.update(data).toString());
    	this.entityManager.createQuery(this.jPQLUtil.update(data).toString()).executeUpdate() ; 
		
    }
    @Transactional
    public void seve(T t){
    	this.entityManager.persist(t);
    }
    @Transactional
    public void update(T t){
    	this.entityManager.merge(t) ;
    }
}
