package com.cjteam.mrile.persistence;

import com.cjteam.mrile.model.Channel;
import com.cjteam.mrile.model.ChannelExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ChannelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int countByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int deleteByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int insert(Channel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int insertSelective(Channel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    List<Channel> selectByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    Channel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Channel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Channel record);

    int executeUpdateBySql(Map<String, String> map);

    List<Map<String, Object>> executeSql(Map<String, String> map);
}