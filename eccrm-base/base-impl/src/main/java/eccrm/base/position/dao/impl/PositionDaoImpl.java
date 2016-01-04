package eccrm.base.position.dao.impl;

import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.exception.Argument;
import com.ycrl.utils.string.StringUtils;
import eccrm.base.position.bo.PositionBo;
import eccrm.base.position.dao.PositionDao;
import eccrm.base.position.domain.Position;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * Generated by chenl on 2014-10-11.
 */

@Repository("positionDao")
public class PositionDaoImpl extends HibernateDaoHelper implements PositionDao {
    private static final String DEFAULT_ORDER = "pinyin";

    @Override
    public String save(Position position) {
        return (String) getSession().save(position);
    }

    @Override
    public void update(Position position) {
        getSession().update(position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> query(PositionBo bo) {
        Criteria criteria = getDefaultCriteria(bo);
        return criteria.list();
    }

    @Override
    public long getTotal(PositionBo bo) {
        Criteria criteria = createRowCountsCriteria(Position.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public int deleteById(String id) {
        return getSession().createQuery("delete from " + Position.class.getName() + " p where p.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public boolean hasName(String id, String name) {
        Argument.isEmpty(name, "判断指定名称的角色是否存在时,角色名称不能为空!");
        Criteria criteria = createRowCountsCriteria(Position.class);
        if (id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        criteria.add(Restrictions.eq("name", name));
        return (Long) criteria.uniqueResult() > 0;
    }

    @Override
    public boolean hasCode(String id, String code) {
        Argument.isEmpty(code, "判断指定编号的角色是否存在时,编号名称不能为空!");
        Criteria criteria = createRowCountsCriteria(Position.class);
        if (id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        criteria.add(Restrictions.eq("code", code));
        return (Long) criteria.uniqueResult() > 0;
    }

    @Override
    public List<Position> findByCode(String code) {
        Argument.isEmpty(code, "查询系统不能为空");
        Criteria criteria = createCriteria(Position.class);
        criteria.add(Restrictions.eq("code", code));
        return criteria.list();
    }

    @Override
    public Position findById(String id) {
        return (Position) getSession().get(Position.class, id);
    }

    /**
     * 获得默认的org.hibernate.Criteria对象,并根据bo进行初始化（如果bo为null，则会新建一个空对象）
     * 为了防止新的对象中有数据，建议实体/BO均采用封装类型
     */
    private Criteria getDefaultCriteria(PositionBo bo) {
        Criteria criteria = createCriteria(Position.class);
        initCriteria(criteria, bo);
        return criteria;
    }

    /**
     * 根据BO初始化org.hibernate.Criteria对象
     * 如果org.hibernate.Criteria为null，则抛出异常
     * 如果BO为null，则新建一个空的对象
     */
    private void initCriteria(Criteria criteria, PositionBo bo) {
        if (criteria == null) {
            throw new IllegalArgumentException("criteria must not be null!");
        }
        if (bo == null) bo = new PositionBo();
        if (bo.getClassify() != null && bo.getClassify().getId() != null) {
            criteria.add(Restrictions.eq("classify.id", bo.getClassify().getId()));
        }
        criteria.add(
                Example.create(bo).enableLike(MatchMode.ANYWHERE)
                        .excludeProperty("status")
                        .excludeProperty("type")
                        .ignoreCase()
        );
        if (StringUtils.isNotEmpty(bo.getType())) {
            criteria.add(Restrictions.eq("classify.id", bo.getType()));
        }
        if (StringUtils.isNotEmpty(bo.getStatus())) {
            criteria.add(Restrictions.eq("status", bo.getStatus()));
        }
    }

    @Override
    public Boolean findByName(String name) {
        eccrm.utils.Argument.isEmpty(name, "查询指定岗位名称，岗位名称不能为空!");
        return (Long) createRowCountsCriteria(Position.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult() > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findIdByName(String[] positionNames) {
        if (positionNames == null || positionNames.length < 1) {
            return null;
        }
        return createCriteria(Position.class)
                .setProjection(Projections.id())
                .add(Restrictions.in("name", positionNames))
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Position queryByCode(String code) {
        Assert.hasText(code, "查询岗位:岗位编号不能为空!");
        List<Position> positions = createCriteria(Position.class)
                .add(Restrictions.eq("code", code))
                .list();
        if (positions == null || positions.isEmpty()) {
            return null;
        }
        if (positions.size() == 1) {
            return positions.get(0);
        }
        throw new RuntimeException("岗位数据异常:编号为[" + code + "]的岗位存在多个!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> findByPositionName(String positionName) {
        eccrm.utils.Argument.isEmpty(positionName, "查询指定岗位名称，岗位名称不能为空!");
        return createCriteria(Position.class)
                .add(Restrictions.like("name", positionName, MatchMode.ANYWHERE))
                .list();
    }
}