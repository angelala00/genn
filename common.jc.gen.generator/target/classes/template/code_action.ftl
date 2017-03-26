package ${actionPackage}.${categoryName};

import ${beanPackage}.${BeanName};
import com.nisa.iservice.ICodeService;
import com.opensymphony.xwork2.ActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * User: jiangchi
 * DateTime: ${currdate}
 */
public class ${actionName} extends ActionSupport {
	@Autowired
	private ICodeService codeService;
	private List<${BeanName}> list;
	private ${BeanName} ${beanName};
	public String list(){
		list = (List<${BeanName}>) codeService.queryAll(${BeanName}.class);
		return "listjsp";
	}
	public String edit(){
		${beanName} = (${BeanName}) codeService.getOne(${BeanName}.class, ${beanName}.get${tbinfo.pk_Java}());
		return "addjsp";
	}
	public String toadd(){
		${beanName} = null;
		return "addjsp";
	}
	public String add(){
		codeService.saveOrUpdate(${beanName});
		return "list";
	}
	public String save(){
		codeService.saveOrUpdate(${beanName});
		return "list";
	}
	public String del(){
		codeService.del(${BeanName}.class, ${beanName}.get${tbinfo.pk_Java}());
		return "list";
	}
	
	<#list tbinfo.columns as column>
	private ${column.type_java} ${column.name_java};//${column.desc}
	</#list>
	
	<#list tbinfo.columns as column>
	public ${column.type_java} get${column.name_Java}() {
		return ${column.name_java};
	}
	
	public void set${column.name_Java}(${column.type_java} ${column.name_java}) {
		this.${column.name_java} = ${column.name_java};
	}
	
	</#list>
	
	public ICodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(ICodeService codeService) {
		this.codeService = codeService;
	}

	public ${BeanName} get${BeanName}() {
		return ${beanName};
	}
	public void set${BeanName}(${BeanName} ${beanName}) {
		this.${beanName} = ${beanName};
	}
	public List<${BeanName}> getList() {
		return list;
	}
	public void setList(List<${BeanName}> list) {
		this.list = list;
	}
	
}
