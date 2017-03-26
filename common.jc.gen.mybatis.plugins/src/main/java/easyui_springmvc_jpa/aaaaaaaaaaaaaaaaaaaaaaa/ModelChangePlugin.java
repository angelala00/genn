package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Logger;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author Jiangchi
 * 
 */
public class ModelChangePlugin extends PluginAdapter {
//	protected Logger logger = LoggerFactory.getLogger(ModelChangePlugin.class);
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Map<String, String> map = new HashMap<String, String>();
		for (IntrospectedColumn dd : introspectedTable.getAllColumns()) {
			if (dd.getDefaultValue() != null) {
				map.put(dd.getJavaProperty(),dd.getDefaultValue());
			}
			System.out.println("==1:" + dd.getActualColumnName() + "=" + dd.getJavaProperty() + "=" + dd.getDefaultValue());
		}
		

		Method method = new Method(); 
		method.setName("toString");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getStringInstance());
		method.addAnnotation("@Override");
		
		method.addBodyLine("return \"" + topLevelClass.getType().getShortName() + " [\"");
		List<Field> fs = topLevelClass.getFields();
		System.out.println("map:" + map);
		for (Field f : fs) {
			if (map.containsKey(f.getName())) {
				if (FullyQualifiedJavaType.getStringInstance().equals(f.getType())) {
					f.setInitializationString("\""+map.get(f.getName())+"\"");
				} else {
					f.setInitializationString(map.get(f.getName()));
				}
			}
			method.addBodyLine("+ \", " + f.getName() + "=\" + " + f.getName() + " ");
		}
		method.addBodyLine("+ \"]\";");
		
		topLevelClass.addMethod(method);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
