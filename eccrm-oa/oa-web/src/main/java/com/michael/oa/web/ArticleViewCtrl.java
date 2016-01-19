package com.michael.oa.web;

import com.michael.oa.bo.ArticleViewBo;
import com.michael.oa.domain.ArticleView;
import com.michael.oa.service.ArticleViewService;
import com.michael.oa.vo.ArticleViewVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.GsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/oa/articleView"})
public class ArticleViewCtrl extends BaseController {
    @Resource
    private ArticleViewService articleViewService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "oa/articleView/list/articleView_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "oa/articleView/edit/articleView_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        ArticleView articleView = GsonUtils.wrapDataToEntity(request, ArticleView.class);
        articleViewService.save(articleView);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "oa/articleView/edit/articleView_edit";
    }


    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "oa/articleView/edit/articleView_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        ArticleViewVo vo = articleViewService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        ArticleViewBo bo = GsonUtils.wrapDataToEntity(request, ArticleViewBo.class);
        PageVo pageVo = articleViewService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

}
