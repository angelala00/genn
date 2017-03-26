package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author Jiangchi
 * 
 */
public class AddExcuteSqlPlugin extends PluginAdapter {

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		Method executeUpdateBySqlMethod = new Method();
		executeUpdateBySqlMethod.setVisibility(JavaVisibility.PUBLIC);
		executeUpdateBySqlMethod.setReturnType(FullyQualifiedJavaType.getIntInstance());
		executeUpdateBySqlMethod.setName("executeUpdateBySql");
		executeUpdateBySqlMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "sql"));

		Method executeSelectBySqlMethod = new Method();
		executeSelectBySqlMethod.setVisibility(JavaVisibility.PUBLIC);
		FullyQualifiedJavaType executeSqlMethodResultType = FullyQualifiedJavaType.getNewListInstance();
		FullyQualifiedJavaType executeSqlMethodResultSubType = FullyQualifiedJavaType.getNewMapInstance();
		executeSqlMethodResultSubType.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		executeSqlMethodResultSubType.addTypeArgument(FullyQualifiedJavaType.getObjectInstance());
		executeSqlMethodResultType.addTypeArgument(executeSqlMethodResultSubType);
		executeSelectBySqlMethod.setReturnType(executeSqlMethodResultType);
		executeSelectBySqlMethod.setName("executeSelectBySql");
		executeSelectBySqlMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "sql"));
		
		Method executeSelectSingleValueBySqlMethod = new Method();
		executeSelectSingleValueBySqlMethod.setVisibility(JavaVisibility.PUBLIC);
		executeSelectSingleValueBySqlMethod.setReturnType(FullyQualifiedJavaType.getIntInstance());
		executeSelectSingleValueBySqlMethod.setName("executeSelectSingleValueBySql");
		executeSelectSingleValueBySqlMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "sql"));

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(FullyQualifiedJavaType.getNewMapInstance());
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(executeUpdateBySqlMethod);
		interfaze.addMethod(executeSelectBySqlMethod);
		interfaze.addMethod(executeSelectSingleValueBySqlMethod);
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();
		Element executeSelectSingleValueBySql = new Element() {
			@Override
			public String getFormattedContent(int indentLevel) {
				return "  <select id=\"executeSelectSingleValueBySql\" parameterType=\"String\" resultType=\"java.lang.Integer\">\r\n  	${value}\r\n  </select>";
			}
		};
		parentElement.addElement(executeSelectSingleValueBySql);
		Element executeSelectBySql = new Element() {
			@Override
			public String getFormattedContent(int indentLevel) {
				return "  <select id=\"executeSelectBySql\" parameterType=\"String\" resultType=\"java.util.Map\">\r\n  	${value}\r\n  </select>";
			}
		};
		parentElement.addElement(executeSelectBySql);
		Element executeUpdateBySql = new Element() {
			@Override
			public String getFormattedContent(int indentLevel) {
				return "  <update id=\"executeUpdateBySql\" parameterType=\"String\">\r\n  	${value}\r\n  </update>";
			}
		};
		parentElement.addElement(executeUpdateBySql);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
