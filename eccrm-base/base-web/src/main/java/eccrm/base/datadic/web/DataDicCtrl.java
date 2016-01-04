package eccrm.base.datadic.web;

import com.ycrl.core.web.BaseController;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.datadic.bo.DataDicBo;
import eccrm.base.datadic.domain.DataDic;
import eccrm.base.datadic.service.DataDicService;
import eccrm.base.datadic.vo.DataDicVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/base/datadic"})
public class DataDicCtrl extends BaseController {
    @Resource
    private DataDicService dataDicService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList() {
        return "base/datadic/datadic_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "base/datadic/datadic_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        DataDic dataDic = GsonUtils.wrapDataToEntity(request, DataDic.class);
        dataDicService.save(dataDic);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "base/datadic/datadic_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        DataDic dataDic = GsonUtils.wrapDataToEntity(request, DataDic.class);
        dataDicService.update(dataDic);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "base/datadic/datadic_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        DataDicVo vo = dataDicService.detail(id);
        GsonUtils.printData(response, vo);
    }
    @ResponseBody
    @RequestMapping(value = "/findByCode", params = {"code"}, method = RequestMethod.GET)
    public void findByCode(@RequestParam String code, HttpServletResponse response) {
        DataDicVo vo = dataDicService.findByCode(code);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        DataDicBo bo = GsonUtils.wrapDataToEntity(request, DataDicBo.class);
        PageVo pageVo = dataDicService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        dataDicService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
