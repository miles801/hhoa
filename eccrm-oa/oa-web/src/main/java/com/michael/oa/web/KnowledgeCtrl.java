package com.michael.oa.web;

import com.michael.oa.bo.KnowledgeBo;
import com.michael.oa.domain.Knowledge;
import com.michael.oa.service.KnowledgeService;
import com.michael.oa.vo.KnowledgeVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.GsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/oa/knowledge"})
public class KnowledgeCtrl extends BaseController {
    @Resource
    private KnowledgeService knowledgeService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "oa/knowledge/list/knowledge_list";
    }

    /**
     * 跳转到指定类型知识的搜索页面
     *
     * @param type 知识类型
     */
    @RequestMapping(value = {"/search/{type}"}, method = RequestMethod.GET)
    public String toSearch(@PathVariable String type, HttpServletRequest request) {
        request.setAttribute("type", type);
        return "oa/knowledge/list/knowledge_search";
    }

    /**
     * 跳转到添加知识页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "oa/knowledge/edit/knowledge_edit";
    }


    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    public String toTechnology(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.LIST);
        return "oa/knowledge/list/knowledge_list_tech";
    }

    @RequestMapping(value = "/technology/add", method = RequestMethod.GET)
    public String addTechnology(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "oa/knowledge/edit/knowledge_edit_tech";
    }

    @RequestMapping(value = "/technology/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModifyTechnology(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "oa/knowledge/edit/knowledge_edit_tech";
    }

    @RequestMapping(value = "/technology/search", method = RequestMethod.GET)
    public String toTechnologySearch(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.LIST);
        return "oa/knowledge/list/knowledge_tech_search";
    }

    @RequestMapping(value = {"/technology/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetailTechnology(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "oa/knowledge/edit/knowledge_edit_tech";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Knowledge knowledge = GsonUtils.wrapDataToEntity(request, Knowledge.class);
        knowledgeService.save(knowledge);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "oa/knowledge/edit/knowledge_edit";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Knowledge knowledge = GsonUtils.wrapDataToEntity(request, Knowledge.class);
        knowledgeService.update(knowledge);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "oa/knowledge/edit/knowledge_edit";
    }


    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        KnowledgeVo vo = knowledgeService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        KnowledgeBo bo = GsonUtils.wrapDataToEntity(request, KnowledgeBo.class);
        PageVo pageVo = knowledgeService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        knowledgeService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/active", params = {"ids"}, method = RequestMethod.POST)
    public void batchActive(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        knowledgeService.batchActive(idArr);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/cancel", params = {"ids"}, method = RequestMethod.POST)
    public void batchCancel(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        knowledgeService.batchCancel(idArr);
        GsonUtils.printSuccess(response);
    }

}
