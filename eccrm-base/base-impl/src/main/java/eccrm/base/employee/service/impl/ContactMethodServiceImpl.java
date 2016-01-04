package eccrm.base.employee.service.impl;

import eccrm.base.employee.bo.ContactMethodBo;
import eccrm.base.employee.dao.ContactMethodDao;
import eccrm.base.employee.domain.ContactMethod;
import eccrm.base.employee.service.ContactMethodService;
import eccrm.base.employee.service.ContactType;
import eccrm.base.employee.vo.ContactMethodVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import eccrm.core.exception.NullParamException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Generated by chenl on 2014-10-22.
 */

@Service("contactMethodService")
public class ContactMethodServiceImpl implements ContactMethodService {
    @Resource
    private ContactMethodDao contactMethodDao;

    @Override
    public String save(ContactMethod contactMethod) {
        if(StringUtils.isBlank(contactMethod.getSupType())){
            throw  new NullParamException("联系方式类型不能为空!");
        }
        if(!StringUtils.isBlank(contactMethod.getSupType())){
            //如果类型为电话 短信
            if(contactMethod.getSupType().equals("PHONE") || contactMethod.getSupType().equals("SMS")){
                if(!StringUtils.isBlank(contactMethod.getIsShow()) && contactMethod.getIsShow().equals("1")
                        || Boolean.valueOf(contactMethod.getIsShow())==true){//显示
                    contactMethod.setIsShow("1");
                }else{
                    contactMethod.setIsShow("2");
                }
            }else{//其他 微信 email就不需要验证是否显示使用****的形式
                contactMethod.setIsShow("3");
            }
            if(contactMethod.getSupType().equals("WEIXIN") || contactMethod.getSupType().equals("EMAIL")){//email  微信
                contactMethod.setSubType("");
            }
        }else{
            throw  new NullParamException("联系方式类型不能为空!");
        }
        String id = contactMethodDao.save(contactMethod);
        return id;
    }

    @Override
    public void update(ContactMethod contactMethod) {
        contactMethodDao.update(contactMethod);
    }

    @Override
    public PageVo query(ContactMethodBo bo) {
        PageVo vo = new PageVo();
        Long total = contactMethodDao.getTotal(bo);
        vo.setTotal(total);
        if (total == 0) return vo;
        List<ContactMethod> contactMethods = contactMethodDao.query(bo);
        vo.setData(BeanWrapBuilder.newInstance()
                .wrapList(contactMethods, ContactMethodVo.class));
        return vo;
    }

    @Override
    public ContactMethodVo findById(String id) {
     ContactMethod   contType = contactMethodDao.findById(id);
        if(contType!=null){
            contType.setMethod("byId");
        }
        return wrap(contType);
    }


    @Override
    public void deleteByIds(String... ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            contactMethodDao.deleteById(id);
        }
    }
    @Override
    public String phoneShow(String preAddr, String address, String aftAddr) {
        StringBuffer foo = new StringBuffer();
        if(!StringUtils.isBlank(preAddr)){
            foo.append(preAddr+"-");
            if(!StringUtils.isBlank(address)){
                String str = address.substring(0,3)+"*****";
                foo.append(str);
            }
            if(!StringUtils.isBlank(aftAddr)){
                foo.append("-"+aftAddr);
            }
        }else{
            if(!StringUtils.isBlank(address)){
                String str = address.substring(0,3)+"****"+address.substring(address.length()-4,address.length());
                foo.append(str);
            }
        }
        return foo.toString();
    }

    public ContactMethodVo wrap(ContactMethod contactMethod) {
        if (contactMethod == null) return null;
        ContactMethodVo vo = new ContactMethodVo();
        BeanUtils.copyProperties(contactMethod, vo);
        if (contactMethod == null) return null;
        BeanUtils.copyProperties(contactMethod, vo);
        ParameterContainer parameterContainer = ParameterContainer.getInstance();
        if(!StringUtils.isBlank(contactMethod.getSupType())){//存在
            vo.setTypeName(parameterContainer.getSystemName(ContactType.CONT_SUP, contactMethod.getSupType()));
        }
        if(!StringUtils.isBlank(contactMethod.getSubType())){
            vo.setSubTypeName(parameterContainer.getSystemName(ContactType.CONT_SUB, contactMethod.getSubType()));
        }
        if(!StringUtils.isBlank(contactMethod.getTargetType())){
            vo.setTargetTypeName(parameterContainer.getBusinessName(ContactType.BP_GOAL_TYPE, contactMethod.getTargetType()));
        }
        if(!StringUtils.isBlank(contactMethod.getStatus())){
            vo.setStatusName(parameterContainer.getSystemName(ContactType.CONT_TYPE_STATUS, contactMethod.getStatus()));
        }
        /*if(!StringUtils.isBlank(contactMethod.getIsShow()) ){
            if(!StringUtils.isBlank(contactMethod.getMethod()) && contactMethod.getMethod().equals("byId")){//查看
                if(contactMethod.getIsShow().equals("2")){//不显示
                    String address = contactMethod.getAddress() ;
                    if(!StringUtils.isBlank(contactMethod.getSupType())){
                        if(contactMethod.getSupType().equals("PHONE") && contactMethod.getSubType().equals("HOMEPHONE") ||
                                contactMethod.getSubType().equals("OFFICEPHONE") ){//家庭电话  办公电话
                            vo.setAddress(address.substring(0,3)+"*****");
                        }//移动 短信
                        else if(contactMethod.getSupType().equals("PHONE") && contactMethod.getSubType().equals("MOBILPHONE") ){
                            vo.setAddress(address.substring(0,3)+"****"+address.substring(address.length()-4,address.length()));
                        }
                        else if(contactMethod.getSupType().equals("SMS") ){
                            vo.setAddress(address.substring(0,3)+"****"+address.substring(address.length()-4,address.length()));
                        }
                        else {//email 微信
                            vo.setAddress(address);
                        }
                    }

                }else{//显示
                    vo.setAddress(contactMethod.getAddress());
                }
            }else{//列表页面显示address
                if(contactMethod.getIsShow().equals("2")){
                    String phone = phoneShow(contactMethod.getPreAddr(),contactMethod.getAddress(),contactMethod.getAftAddr());
                    vo.setAddress(phone);
                }
            }
        }*/
        return vo;
    }
}
