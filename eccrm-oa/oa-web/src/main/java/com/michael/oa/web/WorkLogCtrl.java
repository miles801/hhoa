package com.michael.oa.web;

import com.michael.oa.bo.WorkLogBo;
import com.michael.oa.domain.WorkLog;
import com.michael.oa.domain.WorkLogComment;
import com.michael.oa.service.WorkLogCommentService;
import com.michael.oa.service.WorkLogService;
import com.michael.oa.vo.WorkLogCommentVo;
import com.michael.oa.vo.WorkLogVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.GsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工作日志
 *
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/oa/workLog"})
public class WorkLogCtrl extends BaseController {
    @Resource
    private WorkLogService workLogService;

    @Resource
    private WorkLogCommentService workLogCommentService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "oa/workLog/list/workLog_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "oa/workLog/edit/workLog_edit";
    }

    /**
     * 跳转到日志评论页面
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String toCommentAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "oa/workLog/edit/workLog_comment";
    }

    /**
     * 保存日志评论
     */
    @RequestMapping(value = "/comment/save", method = RequestMethod.POST)
    @ResponseBody
    public void saveComment(HttpServletRequest request, HttpServletResponse response) {
        WorkLogComment workLogComment = GsonUtils.wrapDataToEntity(request, WorkLogComment.class);
        workLogCommentService.save(workLogComment);
        GsonUtils.printSuccess(response);
    }

    /**
     * 查询日志评论
     */
    @RequestMapping(value = "/{workLogId}/comment", method = RequestMethod.GET)
    @ResponseBody
    public void saveComment(@PathVariable String workLogId, HttpServletResponse response) {
        List<WorkLogCommentVo> data = workLogCommentService.queryByWorkLog(workLogId);
        GsonUtils.printData(response, data);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        WorkLog workLog = GsonUtils.wrapDataToEntity(request, WorkLog.class);
        workLogService.save(workLog);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "oa/workLog/edit/workLog_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        WorkLog workLog = GsonUtils.wrapDataToEntity(request, WorkLog.class);
        workLogService.update(workLog);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "oa/workLog/edit/workLog_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        WorkLogVo vo = workLogService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        WorkLogBo bo = GsonUtils.wrapDataToEntity(request, WorkLogBo.class);
        PageVo pageVo = workLogService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        workLogService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
