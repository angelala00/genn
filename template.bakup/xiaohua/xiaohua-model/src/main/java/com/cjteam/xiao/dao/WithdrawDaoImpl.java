package com.cjteam.xiao.dao;


import com.google.common.base.Joiner;
import com.cjteam.xiao.model.Withdraw;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository("withdrawDao")
public class WithdrawDaoImpl extends BaseDao   implements WithdrawDao {
    private static final Logger LOG = LoggerFactory.getLogger(WithdrawDaoImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Withdraw> filter(Map<String, Object> whereCondition, Pageable pageable) {
        StringBuilder whereStr = new StringBuilder();
        int conditionCount = 0;
        if (MapUtils.isNotEmpty(whereCondition)) {
            conditionCount = whereCondition.size();
        }
        if (conditionCount > 0) {
            String[] whereKey = whereCondition.keySet().toArray(new String[whereCondition.size()]);
            for (int begin = 0; begin < conditionCount; begin++) {
                if (begin != 0) {
                    whereStr.append(" and ");
                } else {
                    whereStr.append(" where ");
                }
                whereStr.append(" w." + whereKey[begin]).append("?").append(String.valueOf(begin + 1));
            }
        }
        whereStr.append(parseOrderBySQLPart("w", pageable.getSort()));
        LOG.debug("sql's where part is {} ",whereStr.toString());
        TypedQuery<Withdraw> contentQuery = entityManager.createQuery("from Withdraw w  " + whereStr.toString(), Withdraw.class);
        contentQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        contentQuery.setMaxResults(pageable.getPageSize());
        TypedQuery<Long> countQuery = entityManager.createQuery("select count(*) from Withdraw w  " + whereStr.toString(), Long.class);
        countQuery.setFirstResult(0);
        countQuery.setMaxResults(1);
        if (conditionCount > 0) {
            Iterator<Object> iterator = whereCondition.values().iterator();
            Object tmp = null;
            int index = 1;
            while (iterator.hasNext()) {
                tmp = iterator.next();
                contentQuery.setParameter(index, tmp);
                countQuery.setParameter(index++, tmp);
            }
        }
        return new PageImpl<Withdraw>(contentQuery.getResultList(), pageable, countQuery.getSingleResult());
    }
}
