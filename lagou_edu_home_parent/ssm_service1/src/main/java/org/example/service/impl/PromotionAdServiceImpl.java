package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.bean.PromotionAd;
import org.example.bean.PromotionAdVO;
import org.example.dao.PromotionAdMapper;
import org.example.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVO promotionAdVO) {

        PageHelper.startPage(promotionAdVO.getCurrentPage(), promotionAdVO.getPageSize());
        List<PromotionAd> list = promotionAdMapper.findAllPromotionAd();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {

        // 封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        // 调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        // 封装数据
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        // 调用mapper
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {

        PromotionAd list = promotionAdMapper.findPromotionAdById(id);
        return list;
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        // 封装数据
        promotionAd.setUpdateTime(new Date());

        // 调用mapper
        promotionAdMapper.updatePromotionAd(promotionAd);
    }
}
