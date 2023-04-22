package org.example.dao;

import org.example.bean.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*分页查询广告信息*/
    public List<PromotionAd> findAllPromotionAd();

    /*修改广告状态*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);

    /*新增广告*/
    public void savePromotionAd(PromotionAd promotionAd);

    /*根据id查询广告信息*/
    public PromotionAd findPromotionAdById(Integer id);

    /*修改广告信息*/
    public void updatePromotionAd(PromotionAd promotionAd);
}
