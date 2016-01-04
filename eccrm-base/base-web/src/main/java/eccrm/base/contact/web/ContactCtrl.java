package eccrm.base.contact.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michael.poi.exp.ExportEngine;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.DateStringConverter;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.contact.bo.ContactBo;
import eccrm.base.contact.domain.Contact;
import eccrm.base.contact.service.ContactService;
import eccrm.base.contact.vo.ContactVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = {"/customer/contact"})
public class ContactCtrl extends BaseController {
    @Resource
    private ContactService contactService;

    @RequestMapping(value = "/flag1", method = RequestMethod.GET)
    public String findByFlag1(HttpServletRequest request) {
        request.setAttribute("contactMethod", 1);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @RequestMapping(value = "/flag2", method = RequestMethod.GET)
    public String findByFlag2(HttpServletRequest request) {
        request.setAttribute("contactMethod", 2);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @RequestMapping(value = "/flag3", method = RequestMethod.GET)
    public String findByFlag3(HttpServletRequest request) {
        request.setAttribute("contactMethod", 3);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @RequestMapping(value = "/flag4", method = RequestMethod.GET)
    public String findByFlag4(HttpServletRequest request) {
        request.setAttribute("contactMethod", 4);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "customer/contact/list/contact_list";
    }

    /**
     * 仪表盘专用
     */
    @RequestMapping(value = {"/Board"}, method = RequestMethod.GET)
    public String toBoardList(HttpServletRequest request) {
        String isMore = request.getParameter("isMore");
        if ("true".equals(isMore)) {
            return "customer/contactRecords/list/contactRecords_list";
        }
        return "customer/contactRecords/listBoard/contactRecords_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = GsonUtils.wrapDataToEntity(request, Contact.class);
        String id = contactService.save(contact);
        GsonUtils.printSuccess(response, id);
    }


    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "customer/contact/edit/contact_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = GsonUtils.wrapDataToEntity(request, Contact.class);
        contactService.update(contact);
        GsonUtils.printSuccess(response);
    }

    /**
     * 更新联络明细
     * 联系人、联系人名称、联系类型
     */
    @RequestMapping(value = "/updateResource", method = RequestMethod.POST)
    @ResponseBody
    public void updateResource(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = GsonUtils.wrapDataToEntity(request, Contact.class);
        contactService.updateResource(contact);
        GsonUtils.printSuccess(response);
    }

    /**
     * 更新联络记录的结束时间
     */
    @RequestMapping(value = "/updateEndTime", params = "recordId", method = RequestMethod.POST)
    @ResponseBody
    public void updateEndTime(@RequestParam String recordId, HttpServletResponse response) {
        contactService.updateEndTime(recordId);
        GsonUtils.printSuccess(response);
    }

    /**
     * 更新联络记录的工单信息
     */
    @RequestMapping(value = "/updateFlow", method = RequestMethod.POST)
    @ResponseBody
    public void updateFlow(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = GsonUtils.wrapDataToEntity(request, Contact.class);
        Assert.notNull(contact, "更新联络记录的流程信息失败:联络记录不能为空!");
        contactService.updateFlow(contact);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail/{id}"}, method = RequestMethod.GET)
    public String toDetail(@PathVariable String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "customer/contactRecords/edit/contactRecords_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public void findById(@PathVariable String id, HttpServletResponse response) {
        ContactVo vo = contactService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/gets", params = {"ids"}, method = RequestMethod.GET)
    public void findByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        PageVo pageVo = contactService.findByIds(idArr);
        GsonUtils.printData(response, pageVo);
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public void query(HttpServletRequest request, HttpServletResponse response) {
        ContactBo bo = GsonUtils.wrapDataToEntity(request, ContactBo.class);
        PageVo pageVo = contactService.query(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        contactService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/export"}, method = {RequestMethod.GET})
    public void export(HttpServletRequest request, HttpServletResponse response) {
        ContactBo bo = GsonUtils.wrapDataToEntity(request, ContactBo.class);
        PageVo pageVo = this.contactService.query(bo);
        List data = pageVo.getData();
        Gson gson = (new GsonBuilder()).registerTypeAdapter(Date.class, new DateStringConverter()).create();
        String json = gson.toJson(data);
        JsonElement element = gson.fromJson(json, JsonElement.class);
        JsonObject o = new JsonObject();
        o.add("c", element);
        String disposition = null;

        try {
            disposition = "attachment;filename=" + URLEncoder.encode("联络记录数据.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);

        try {
            new ExportEngine().export(response.getOutputStream(), ContactCtrl.class.getClassLoader().getResourceAsStream("export_contactRecords.xlsx"), o);
        } catch (IOException var12) {
            var12.printStackTrace();
        }

    }
}
