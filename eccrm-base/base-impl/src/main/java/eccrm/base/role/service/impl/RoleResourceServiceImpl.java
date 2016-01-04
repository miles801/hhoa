package eccrm.base.role.service.impl;

import eccrm.base.menu.domain.Menu;
import eccrm.base.menu.vo.MenuVo;
import eccrm.base.role.dao.RoleResourceDao;
import eccrm.base.role.domain.RoleResource;
import eccrm.base.role.service.RoleResourceService;
import eccrm.core.VoHelper;
import eccrm.core.VoWrapper;
import eccrm.utils.Argument;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated by miles on 2014-07-21.
 */

@Service("roleMenuService")
public class RoleResourceServiceImpl implements RoleResourceService {
    private Logger logger = Logger.getLogger(RoleResourceServiceImpl.class);
    @Resource
    private RoleResourceDao roleResourceDao;

    @Override
    public void deleteByRoleId(String roleId) {
        roleResourceDao.deleteByRoleId(roleId);
    }

    @Override
    public List<MenuVo> queryByRoleId(String roleId) {
        List<Menu> menus = roleResourceDao.queryByRoleId(roleId);
        return wrapMenus(menus);
    }

    @Override
    public void save(String roleId, String[] menuIds) {
        Argument.isEmpty(roleId, "根据角色ID保存与菜单的关联关系时,角色ID不能为空!");
        // 删除之前角色的所有菜单
        roleResourceDao.deleteByRoleId(roleId);
        if (menuIds != null && menuIds.length > 0) {
            for (int i = 0; i < menuIds.length; i++) {
                RoleResource resource = new RoleResource(roleId, menuIds[i]);
                resource.setSequenceNo(i + 1);
                roleResourceDao.save(resource);
            }
        }
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            roleResourceDao.deleteById(id);
        }
    }

    @Override
    public List<MenuVo> queryByUserId(String userId) {
        logger.debug("正在查询用户[]的可访问的所有资源...");
        List<Menu> resources = roleResourceDao.queryByUserId(userId);
        VoHelper.wrapVos(resources, new VoWrapper<Menu, MenuVo>() {
            @Override
            public MenuVo wrap(Menu menu) {
                if (menu == null) return null;
                MenuVo menuVo = new MenuVo();
                menuVo.setId(menu.getId());
                menuVo.setCode(menu.getCode());
                return menuVo;
            }
        });
        return wrapMenus(roleResourceDao.queryByUserId(userId));
    }

    private List<MenuVo> wrapMenus(List<Menu> menus) {
        if (menus == null) return null;
        List<MenuVo> vos = new ArrayList<MenuVo>();
        for (Menu menu : menus) {
            vos.add(wrapMenu(menu));
        }
        return vos;
    }

    @Override
    public List<MenuVo> defendAccessResources(String userId) {
        return null;
    }

    private MenuVo wrapMenu(Menu menu) {
        if (menu == null) return null;
        MenuVo vo = new MenuVo();
        vo.setId(menu.getId());
        vo.setName(menu.getName());
        vo.setCode(menu.getCode());
        return vo;
    }
}