package com.jc.schema;

import java.util.List;
import java.util.Map;


public interface ITbinfoUtils {

	/**
	 * get table info from databases
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTbInfos() throws Exception;

}
