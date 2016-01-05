package com.michael.oa.service.impl;

import com.michael.oa.bo.BlackListBo;
import com.michael.oa.dao.BlackListDao;
import com.michael.oa.domain.BlackList;
import com.michael.oa.service.BlackListService;
import com.michael.oa.vo.BlackListVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("blackListService")
public class BlackListServiceImpl implements BlackListService, BeanWrapCallback<BlackList, BlackListVo> {
    @Resource
    private BlackListDao blackListDao;

    @Override
    public String save(BlackList blackList) {
        ValidatorUtils.validate(blackList);
        String id = blackListDao.save(blackList);
        return id;
    }

    @Override
    public void update(BlackList blackList) {
        ValidatorUtils.validate(blackList);
        blackListDao.update(blackList);
    }

    @Override
    public PageVo pageQuery(BlackListBo bo) {
        PageVo vo = new PageVo();
        Long total = blackListDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<BlackList> blackListList = blackListDao.query(bo);
        List<BlackListVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(blackListList, BlackListVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public BlackListVo findById(String id) {
        BlackList blackList = blackListDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(blackList, BlackListVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            blackListDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(BlackList blackList, BlackListVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        // 黑户类型
        vo.setTypeName(container.getBusinessName(TYPE, blackList.getType()));
    }
}
