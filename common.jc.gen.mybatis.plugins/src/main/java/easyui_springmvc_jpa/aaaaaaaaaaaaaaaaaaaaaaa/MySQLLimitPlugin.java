package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author Jiangchi
 * 
 */
public class MySQLLimitPlugin extends PluginAdapter {

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		Field field = new Field();
		field.setName("limitClause");
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getStringInstance());
		topLevelClass.addField(field);
		
		Method limitClauseGetterMethod = new Method();
		limitClauseGetterMethod.setName("getLimitClause");
		limitClauseGetterMethod.setVisibility(JavaVisibility.PUBLIC);
		limitClauseGetterMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		limitClauseGetterMethod.addBodyLine("return limitClause;");
		topLevelClass.addMethod(limitClauseGetterMethod);
		
		Method limitClauseSetterMethod = new Method();
		limitClauseSetterMethod.setName("setLimitClause");
		limitClauseSetterMethod.setVisibility(JavaVisibility.PUBLIC);
		Parameter parameter = new Parameter(FullyQualifiedJavaType.getStringInstance(), "limitClause");
		limitClauseSetterMethod.addParameter(parameter);
		limitClauseSetterMethod.addBodyLine("this.limitClause = limitClause;");
		topLevelClass.addMethod(limitClauseSetterMethod);
		
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
	

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();
		List<Element> es = parentElement.getElements();
		for (Element e : es) {
			if (e instanceof XmlElement) {
				XmlElement xe = (XmlElement) e;
				if ("select".equals(xe.getName())) {
					List<Attribute> elementAttr = xe.getAttributes();
					boolean isTargetSql = false;
					for (Attribute attr : elementAttr) {
						String attrName = attr.getName();
						String attrValue = attr.getValue();
						if ("id".equals(attrName) && "selectByExample".equals(attrValue)) {
							isTargetSql = true;
							break;
						}
						if ("id".equals(attrName) && "selectByExampleWithBLOBs".equals(attrValue)) {
							isTargetSql = true;
							break;
						}
					}
					if (isTargetSql) {
						Element element = new Element() {
							@Override
							public String getFormattedContent(int indentLevel) {
								return "    <if test=\"limitClause != null\">\r\n      ${limitClause}\r\n    </if>";
							}
						};
						xe.addElement(element);
					}
				}
			}
		}
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
