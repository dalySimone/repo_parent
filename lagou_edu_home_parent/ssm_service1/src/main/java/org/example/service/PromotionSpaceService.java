package org.example.service;

import org.example.bean.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    public List<PromotionSpace> findAllPromotionSpace();

    public void savePromotionSpace(PromotionSpace promotionSpace);

    public PromotionSpace findPromotionSpaceById(int id);

    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
