package eccrm.base.datadic.web;

import com.ycrl.core.web.BaseController;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.datadic.bo.DataDicItemBo;
import eccrm.base.datadic.domain.DataDicItem;
import eccrm.base.datadic.service.DataDicItemService;
import eccrm.base.datadic.vo.DataDicItemVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/base/dataDicItem"})
public class DataDicItemCtrl extends BaseController {
    @Resource
    private DataDicItemService dataDicItemService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList() {
        return "base/dataDicItem/list/dataDicItem_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "base/dataDicItem/edit/dataDicItem_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        DataDicItem dataDicItem = GsonUtils.wrapDataToEntity(request, DataDicItem.class);
        dataDicItemService.save(dataDicItem);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "base/dataDicItem/edit/dataDicItem_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        DataDicItem dataDicItem = GsonUtils.wrapDataToEntity(request, DataDicItem.class);
        dataDicItemService.update(dataDicItem);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "base/dataDicItem/edit/dataDicItem_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        DataDicItemVo vo = dataDicItemService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        DataDicItemBo bo = GsonUtils.wrapDataToEntity(request, DataDicItemBo.class);
        PageVo pageVo = dataDicItemService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        dataDicItemService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
