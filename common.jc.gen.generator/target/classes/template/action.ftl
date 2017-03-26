package ${actionPackage}.${categoryName};

import ${beanPackage}.${BeanName};
import com.opensymphony.xwork2.ActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created with codeGen
 * User: jiangchi
 * DateTime: ${currdate}
 */
public class ${ActionName} extends ActionSupport {
	
	private List<${BeanName}> list;
	public String list(){
		list=new ArrayList<${BeanName}>();
		return "list";
	}
	public String edit(){
		return "list";
	}
	public String add(){
		return "list";
	}
	public String save(){
		return "list";
	}
	public String del(){
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
	
}
