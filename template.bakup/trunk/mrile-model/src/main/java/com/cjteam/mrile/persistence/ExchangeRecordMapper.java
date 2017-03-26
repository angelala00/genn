package com.cjteam.mrile.persistence;

import com.cjteam.mrile.model.ExchangeRecord;
import com.cjteam.mrile.model.ExchangeRecordExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ExchangeRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int countByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int deleteByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int insert(ExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int insertSelective(ExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    List<ExchangeRecord> selectByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    ExchangeRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ExchangeRecord record, @Param("example") ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ExchangeRecord record, @Param("example") ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exchange_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ExchangeRecord record);

    int executeUpdateBySql(Map<String, String> map);

    List<Map<String, Object>> executeSql(Map<String, String> map);
}