package mona.joyhentai.dao;

import mona.joyhentai.model.Host;

import java.util.List;

public interface HostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int insert(Host record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    Host selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    List<Host> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int updateByPrimaryKey(Host record);
}