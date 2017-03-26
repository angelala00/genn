package com.cjteam.xiao.dao;

import com.google.common.base.Joiner;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by ChenLong on 2014/3/30.
 */
public abstract class BaseDao {
    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected String parseOrderBySQLPart(String tableAlias,Sort sort) {
        if (null == sort)
            return "";
        Iterator<Sort.Order> orderIterator = sort.iterator();
        Sort.Order order = null;
        Sort.Direction direction = null;
        List<String> sorts = new ArrayList<String>();
        while (orderIterator.hasNext()) {
            order = orderIterator.next();
            direction = order.getDirection();
            sorts.add("  " + tableAlias + "." + order.getProperty() + " " + parseDirection(direction));
        }
        return " order by " + Joiner.on(",").join(sorts);
    }

    protected String parseDirection(Sort.Direction direction) {
        if (Sort.Direction.DESC.equals(direction))
            return "desc";
        return "asc";
    }
}
