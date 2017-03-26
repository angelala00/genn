package ${targetPackage}.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ${targetPackage}.dao.I${tbinfo.tbnameJavaType}Dao;
import ${targetPackage}.model.${tbinfo.tbnameJavaType};

@Service
public class ${tbinfo.tbnameJavaType}Service extends BaseService {

	@Autowired
	private I${tbinfo.tbnameJavaType}Dao dao;

	public Page<${tbinfo.tbnameJavaType}> getAllList(Pageable pageableObj) {
		return dao.getAllList(pageableObj);
	}

	public ${tbinfo.tbnameJavaType} getOne(int id) {
		return dao.selectOneById(id);
	}

	public void save(${tbinfo.tbnameJavaType} itemParam) {
		if (itemParam.getId() != null && itemParam.getId() > 0) {
			dao.updateByPrimaryKeySelective(itemParam);
		} else {
			//initoperate
			itemParam.setCreateTime(new Date());
			dao.insert(itemParam);
		}
		
	}

	public void removeOne(int iid) {
		dao.deleteByPrimaryKey(iid);
	}
}
