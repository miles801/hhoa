package com.michael.oa.web;

import com.michael.oa.bo.ArticleBo;
import com.michael.oa.domain.Article;
import com.michael.oa.service.ArticleService;
import com.michael.oa.vo.ArticleVo;
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
@RequestMapping(value = {"/oa/article"})
public class ArticleCtrl extends BaseController {
    @Resource
    private ArticleService articleService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "oa/article/list/article_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        request.setAttribute("moduleId", request.getParameter("moduleId"));
        return "oa/article/edit/article_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Article article = GsonUtils.wrapDataToEntity(request, Article.class);
        articleService.save(article);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "oa/article/edit/article_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Article article = GsonUtils.wrapDataToEntity(request, Article.class);
        articleService.update(article);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "oa/article/edit/article_edit";
    }

    /**
     * 阅读文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = {"/view"}, params = {"id"}, method = RequestMethod.POST)
    @ResponseBody
    public void viewArticle(@RequestParam String id, HttpServletResponse response) {
        articleService.view(id);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        ArticleVo vo = articleService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        ArticleBo bo = GsonUtils.wrapDataToEntity(request, ArticleBo.class);
        PageVo pageVo = articleService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        articleService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/publish", params = {"ids"}, method = RequestMethod.POST)
    public void publish(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        articleService.publish(idArr);
        GsonUtils.printSuccess(response);
    }

}
