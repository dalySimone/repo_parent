package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.bean.PromotionAd;
import org.example.bean.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /*分页查询广告信息*/
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVO promotionAdVO);

    public void updatePromotionAdStatus(int id, int status);

    public void savePromotionAd(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    public void updatePromotionAd(PromotionAd promotionAd);
}
