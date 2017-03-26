package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import java.lang.reflect.Field;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

/**
 * @author Jiangchi
 * 
 */
public class SqlMapGeneratedOverridePlugin extends PluginAdapter {

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
			IntrospectedTable introspectedTable) {
		try {
			Field field = sqlMap.getClass().getDeclaredField("isMergeable");
			field.setAccessible(true);
			field.set(sqlMap, false);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
