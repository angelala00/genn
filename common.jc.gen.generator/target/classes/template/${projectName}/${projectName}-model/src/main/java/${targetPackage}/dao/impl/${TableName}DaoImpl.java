package ${targetPackage}.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ${targetPackage}.dao.I${tbinfo.tbnameJavaType}Dao;
import ${targetPackage}.model.${tbinfo.tbnameJavaType};
import ${targetPackage}.model.${tbinfo.tbnameJavaType}Example;
import ${targetPackage}.persistence.${tbinfo.tbnameJavaType}Mapper;

@Component
public class ${tbinfo.tbnameJavaType}DaoImpl implements I${tbinfo.tbnameJavaType}Dao {

	@Autowired
	private ${tbinfo.tbnameJavaType}Mapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(${tbinfo.tbnameJavaType} record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(${tbinfo.tbnameJavaType} record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<${tbinfo.tbnameJavaType}> getAllList(Pageable pageableObj) {
		${tbinfo.tbnameJavaType}Example example = new ${tbinfo.tbnameJavaType}Example();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<${tbinfo.tbnameJavaType}> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<${tbinfo.tbnameJavaType}> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<${tbinfo.tbnameJavaType}>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public ${tbinfo.tbnameJavaType} selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}