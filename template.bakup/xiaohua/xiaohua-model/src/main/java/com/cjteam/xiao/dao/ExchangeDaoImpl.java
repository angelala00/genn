package com.cjteam.xiao.dao;

import com.cjteam.xiao.model.Exchange;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 14-1-4
 */
@Repository("exchangeDao")
public class ExchangeDaoImpl  extends BaseDao  implements ExchangeDao {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeDaoImpl.class);

    private EntityManager entityManager;

    @Override
    public Page<Exchange> filter(Map<String, Object> whereCondition, Pageable pageable) {
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
                whereStr.append(" e." + whereKey[begin]).append("?").append(String.valueOf(begin + 1));
            }
            LOG.debug("sql's where part is {} ", whereStr.toString());
        }

        whereStr.append(parseOrderBySQLPart("e", pageable.getSort()));
        TypedQuery<Exchange> contentQuery = entityManager.createQuery("from Exchange e  " + whereStr.toString(), Exchange.class);
        contentQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        contentQuery.setMaxResults(pageable.getPageSize());
        TypedQuery<Long> countQuery = entityManager.createQuery("select count(*) from Exchange e  " + whereStr.toString(), Long.class);
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
        return new PageImpl<Exchange>(contentQuery.getResultList(), pageable, countQuery.getSingleResult());
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
