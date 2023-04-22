package org.example.dao;

import org.example.bean.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    /*查询所有广告位数据*/
    public List<PromotionSpace> findAllPromotionSpace();

    /*新增广告位*/
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*根据id查询广告位信息*/
    public PromotionSpace findPromotionSpaceById(Integer id);

    /*修改广告位信息*/
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
