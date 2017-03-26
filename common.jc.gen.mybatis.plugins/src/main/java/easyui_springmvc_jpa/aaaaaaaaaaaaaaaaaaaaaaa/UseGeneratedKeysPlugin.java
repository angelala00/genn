package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author Jiangchi
 * 
 */
public class UseGeneratedKeysPlugin extends PluginAdapter {

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		String pk = introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName();
		XmlElement parentElement = document.getRootElement();
		List<Element> es = parentElement.getElements();
		for (Element e : es) {
			if (e instanceof XmlElement) {
				XmlElement xe = (XmlElement) e;
				if ("insert".equals(xe.getName())) {
					List<Attribute> elementAttr = xe.getAttributes();
					boolean isTargetSql = false;
					for (Attribute attr : elementAttr) {
						String attrName = attr.getName();
						String attrValue = attr.getValue();
						if ("id".equals(attrName) && "insert".equals(attrValue)) {
							isTargetSql = true;
							break;
						}
					}
					if (isTargetSql) {
						Attribute useGeneratedKeysAttribute = new Attribute("useGeneratedKeys", "true");
						xe.addAttribute(useGeneratedKeysAttribute);
						Attribute keyPropertyAttribute = new Attribute("keyProperty", pk);
						xe.addAttribute(keyPropertyAttribute);
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
