package easyui_springmvc_jpa.aaaaaaaaaaaaaaaaaaaaaaa;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

public class TestMain {

	public static void main(String[] args) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("executeUpdateBySql");
		
		FullyQualifiedJavaType ptype = FullyQualifiedJavaType.getNewMapInstance();
		ptype.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		ptype.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		method.addParameter(new Parameter(ptype, "map"));
		
		System.out.println(method.getFormattedContent(0, true));

//		int all = 12030;
//		for (int i = 0; i <= all; i++) {
//			System.out.println("rate of progress is " + (i * 100 / all) + "%");
//		}
		
		String imageName = "3a5a54228bad0000001981fe2451.jpg";
		System.out.println(imageName.contains("_"));
		System.out.println(imageName.substring(0, imageName.length() - 3));
		
		System.out.println(1000 / 500 - 100);
	}

}
