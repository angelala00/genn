package com.jc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import com.jc.MainMain;

public class Tools {

	public static Map<String, Map<String, String>> parseFieldAndLableMap()
			throws FileNotFoundException {
		Map<String, Map<String, String>> allEntityFieldLableMap = new HashMap<String, Map<String, String>>();
		Scanner scanner = new Scanner(new File(MainMain.class
				.getClassLoader().getResource("./field_lable").getFile()));
		String key = null;
		String value = null;
		Scanner lineScanner;
		int index;
		String[] lineContent = null;
		String entityName = null;
		while (scanner.hasNextLine()) {
			lineScanner = new Scanner(scanner.nextLine());
			lineContent = new String[3];
			index = 0;
			if (!lineScanner.hasNext())
				continue;
			while (lineScanner.hasNext()) {
				lineContent[index++] = lineScanner.next();
			}
			if (index == 1) {
				// table
				if (StringUtils.isNotBlank(entityName))
					System.out.println("entity " + entityName
							+ "'s columns no. is "
							+ allEntityFieldLableMap.get(entityName).size());
				entityName = Utils.parseEntityName(lineContent[--index]);
				System.out.println("entity : " + entityName);
				continue;
			} else if (index == 2) {
				// column
				value = lineContent[--index];
				key = Utils.parseField(lineContent[--index]);
			} else {
				System.out.println("invalid data :" + lineContent);
				continue;
			}
			if (!allEntityFieldLableMap.containsKey(entityName)) {
				allEntityFieldLableMap.put(entityName,
						new HashMap<String, String>());
			}
			if (allEntityFieldLableMap.get(entityName).containsKey(key)) {
				System.out.println("key:" + key + ",oldValue:"
						+ allEntityFieldLableMap.get(entityName).get(key)
						+ ",newValue:" + value);
			} else {
				System.out.println("field:" + key + ",value:" + value);
				allEntityFieldLableMap.get(entityName).put(key, value);
			}
		}
		System.out.println(allEntityFieldLableMap.size());
		return allEntityFieldLableMap;
	}

	public static void main(String[] args) throws FileNotFoundException {
		new Tools().parseFieldAndLableMap();
	}
}
