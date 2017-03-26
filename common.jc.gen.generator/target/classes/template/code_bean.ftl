package ${beanPackage};

//import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * User: jiangchi
 * DateTime: ${currdate}
 */
public class ${beanName} extends BaseBean {
	
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
