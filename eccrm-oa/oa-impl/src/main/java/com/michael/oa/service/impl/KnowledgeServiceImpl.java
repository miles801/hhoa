package com.michael.oa.service.impl;

import com.michael.oa.bo.KnowledgeBo;
import com.michael.oa.dao.KnowledgeDao;
import com.michael.oa.domain.Knowledge;
import com.michael.oa.service.KnowledgeService;
import com.michael.oa.vo.KnowledgeVo;
import com.ycrl.base.common.CommonStatus;
import com.ycrl.base.common.CommonSysParam;
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
@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService, BeanWrapCallback<Knowledge, KnowledgeVo> {
    @Resource
    private KnowledgeDao knowledgeDao;

    @Override
    public String save(Knowledge knowledge) {
        ValidatorUtils.validate(knowledge);
        String id = knowledgeDao.save(knowledge);
        return id;
    }

    @Override
    public void update(Knowledge knowledge) {
        ValidatorUtils.validate(knowledge);
        knowledgeDao.update(knowledge);
    }

    @Override
    public PageVo pageQuery(KnowledgeBo bo) {
        PageVo vo = new PageVo();
        Long total = knowledgeDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Knowledge> knowledgeList = knowledgeDao.query(bo);
        List<KnowledgeVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(knowledgeList, KnowledgeVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public KnowledgeVo findById(String id) {
        Knowledge knowledge = knowledgeDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(knowledge, KnowledgeVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            knowledgeDao.deleteById(id);
        }
    }

    @Override
    public void batchActive(String[] ids) {
        if (ids == null || ids.length < 1) {
            return;
        }
        for (String id : ids) {
            Knowledge knowledge = knowledgeDao.findById(id);
            if (knowledge != null) {
                knowledge.setStatus(CommonStatus.ACTIVE.getValue());
            }
        }
    }

    @Override
    public void batchCancel(String[] ids) {
        if (ids == null || ids.length < 1) {
            return;
        }
        for (String id : ids) {
            Knowledge knowledge = knowledgeDao.findById(id);
            if (knowledge != null) {
                knowledge.setStatus(CommonStatus.CANCELED.getValue());
            }
        }
    }

    @Override
    public void doCallback(Knowledge knowledge, KnowledgeVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        // 知识类型
        vo.setTypeName(container.getBusinessName(TYPE, knowledge.getType()));
        // 状态
        vo.setStatusName(container.getSystemName(CommonSysParam.STATUS, knowledge.getStatus()));
    }
}
