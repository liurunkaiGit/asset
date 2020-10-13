package com.ruoyi.shareproject.information.mapper;

import com.ruoyi.shareproject.information.domain.TLpInformationCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 共享信息管理-基础信息管理-中心信息Mapper
 * @auther 麻超
 * @date 2020/10/12
 */
@Mapper
public interface CenterInformationMapper {

    /**
     * 分页查询中心信息
     * @param informationCenter
     * @return
     */
    List<TLpInformationCenter> selectCenterInformationList(TLpInformationCenter informationCenter);

    /**
     * 插入中心数据
     * @param informationCenter
     * @return
     */
    int insertCenterInformation(TLpInformationCenter informationCenter);

    /**
     * 根据中心名称查询中心数量
     * @return
     */
    int selectCenterNameCount(@Param("centerName") String centerName,@Param("id") Long id);

    /**
     * 根据主键ID查询中心数据
     * @param id
     * @return
     */
    TLpInformationCenter selectCenterInformationById(Long id);

    /**
     * 更新中心名称
     * @param informationCenter
     */
    int updateInformationCenter(TLpInformationCenter informationCenter);

    /**
     * 根据id批量查询更新中心
     * @param centerIds
     * @return
     */
    List<TLpInformationCenter> selectCenterInformationByIds(@Param("centerIds") String[] centerIds);

    int deleteCenterInformation(@Param("ids") String[] ids);
}
