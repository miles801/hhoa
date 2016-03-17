package eccrm.base.attachment.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.attachment.AttachmentContext;
import eccrm.base.attachment.AttachmentHandler;
import eccrm.base.attachment.AttachmentProvider;
import eccrm.base.attachment.bo.AttachmentBo;
import eccrm.base.attachment.domain.Attachment;
import eccrm.base.attachment.domain.AttachmentType;
import eccrm.base.attachment.service.AttachmentService;
import eccrm.base.attachment.utils.AttachmentFormat;
import eccrm.base.attachment.utils.AttachmentHolder;
import eccrm.base.attachment.utils.ImageUtils;
import eccrm.base.attachment.vo.AttachmentVo;
import eccrm.utils.StringUtils;
import eccrm.utils.UUIDGenerator;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author miles
 * @datetime 14-2-28 下午11:18
 */
@Controller
@Scope("prototype")
@RequestMapping("/attachment")
public class AttachmentCtrl {
    private Logger logger = Logger.getLogger(AttachmentCtrl.class);
    private AttachmentHolder attachmentHolder = AttachmentHolder.newInstance();
    @Resource
    private AttachmentService attachmentService;

    /**
     * 上传附件，返回随机生成的id列表
     * <p/>
     * 可选参数：
     * description：附件的描述信息
     * businessType：附件所属业务类型
     *
     * @param dataType 可选，数据类型：目前支持json(默认)、html(text/html)
     *                 返回["","",""]
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings(value = {"unchecked"})
    public void upload(@RequestParam(required = false) String dataType,
                       MultipartHttpServletRequest request, HttpServletResponse response) {
        Iterator<String> itr = request.getFileNames();
        List<String> ids = new ArrayList<String>();
        while (itr.hasNext()) {
            MultipartFile file = request.getFile(itr.next());
            //获得文件名称
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            long fileSize = file.getSize();

            String id = UUIDGenerator.generate();
            try {
                File tmp = AttachmentHolder.newInstance().getTempFile(id);
                boolean result = tmp.mkdirs();
                if (!result) {
                    throw new RuntimeException("附件创建失败!" + tmp.getAbsolutePath());
                }
                file.transferTo(tmp);
                logger.info(SecurityContext.getUsername() + ("上传文件:" + fileName + " , 文件大小:" + AttachmentFormat.format(fileSize)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ids.add(id);
            Attachment attachment = new Attachment();
            attachment.setId(id);
            attachment.setFileName(fileName);
            attachment.setFileType(contentType);
            attachment.setContentType(contentType);
            attachment.setAttachmentType(AttachmentType.FILE.getValue());
            attachment.setSize(fileSize);
            // 保存描述信息
            String desc = request.getParameter("description");
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(desc)) {
                attachment.setDescription(StringUtils.decodeByUTF8(desc));
            }
            attachment.setUploadTime(new Date().getTime());
            String businessType = request.getParameter("businessType");
            if (!StringUtils.isEmpty(businessType)) {
                attachment.setBusinessType(businessType);
            }
            attachment.setStatus(Attachment.STATUS_TEMP);
            attachmentService.save(attachment);
        }
        GsonUtils.printData(response, ids);
    }

    /**
     * 上传附件，返回上传的文件的信息列表
     * 返回[{id:'',fileName:'',uploadTime:'',size:''},{}]
     */
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings(value = {"unchecked"})
    public void upload2(@RequestParam(required = false) String dataType,
                        MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        Iterator<String> itr = request.getFileNames();

        List<Attachment> attaList = new ArrayList<Attachment>();
        while (itr.hasNext()) {
            MultipartFile file = request.getFile(itr.next());
            //获得文件名称
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            long fileSize = file.getSize();

            String id = UUIDGenerator.generate();
            try {
                File tmp = AttachmentHolder.newInstance().getTempFile(id);
                logger.info(SecurityContext.getUsername() + ("上传文件:" + fileName + " , 文件大小:" + AttachmentFormat.format(fileSize)));
                if ("true".equals(request.getParameter("thumb"))) {
                    int w = 80; // 默认宽高
                    int h = 80;
                    String width = request.getParameter("width");
                    if (com.ycrl.utils.string.StringUtils.isNotEmpty(width)) {
                        w = Integer.parseInt(width);
                    }
                    String height = request.getParameter("height");
                    if (com.ycrl.utils.string.StringUtils.isNotEmpty(height)) {
                        h = Integer.parseInt(height);
                    }
                    logger.info(String.format("图片[%s]压缩:宽%d,高%d", fileName, w, h));
                    File tmpFile = AttachmentHolder.newInstance().getTempFile(id + ".png");
                    Thumbnails.of(file.getInputStream()).forceSize(w, h).toFile(tmpFile);
                    FileUtils.moveFile(tmpFile, tmp);
                } else {
                    Assert.isTrue(tmp.createNewFile(), "附件上传失败：附件文件无法成功创建!");

                    file.transferTo(tmp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Attachment attachment = new Attachment();
            attachment.setId(id);
            attachment.setFileName(fileName);
            attachment.setFileType(contentType);
            attachment.setContentType(contentType);
            attachment.setAttachmentType(AttachmentType.FILE.getValue());
            attachment.setSize(fileSize);
            // 保存描述信息
            String desc = request.getParameter("description");
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(desc)) {
                attachment.setDescription(StringUtils.decodeByUTF8(desc));
            }
            attachment.setUploadTime(new Date().getTime());
            String businessType = request.getParameter("businessType");
            if (!StringUtils.isEmpty(businessType)) {
                attachment.setBusinessType(businessType);
            }
            attaList.add(attachment);
            attachment.setStatus(Attachment.STATUS_TEMP);
            attachmentService.save(attachment);

        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dataType)) {
            if ("html".equals(dataType)) {
                response.setContentType("text/html");
            } else if ("xml".equals(dataType)) {
                response.setContentType("xml/html");
            } else if ("json".equals(dataType)) {
                response.setContentType("json/application");
            } else if ("jsp".equals(dataType)) {
                // 此处为定制化代码，为KindEditor定制
                response.setContentType("text/html");
                JsonObject o = new JsonObject();
                o.addProperty("error", 0);
                Attachment attachment = attaList.get(0);
                o.addProperty("id", attachment.getId());
                o.addProperty("name", attachment.getFileName());
                o.addProperty("url", request.getContextPath() + "/attachment/download?id=" + attachment.getId());
                response.getWriter().print(GsonUtils.toJson(o));
                return;
            }
        } else {
            response.setContentType("json/application");
        }
        response.setCharacterEncoding("utf-8");
        String html = GsonUtils.toJson(attaList);
        try {
            response.getWriter().write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加URL附件
     */
    @RequestMapping(value = "/upload/url", method = RequestMethod.POST)
    @ResponseBody
    public void uploadUrl(HttpServletRequest request, HttpServletResponse response) {
        JsonObject object = GsonUtils.wrapDataToEntity(request, JsonObject.class);
        if (object == null || object.get("url") == null || StringUtils.isEmpty(object.get("url").getAsString())) {
            throw new IllegalArgumentException("上传URL附件时,没有获得URL!");
        }
        String url = object.get("url").getAsString();
        String id = UUIDGenerator.generate();
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setAttachmentType(AttachmentType.URL.getValue());
        attachment.setUploadTime(new Date().getTime());
        attachment.setPath(url);
        JsonElement element = object.get("businessType");
        if (element != null && !StringUtils.isEmpty(element.getAsString())) {
            attachment.setBusinessType(element.getAsString());
        }
        attachment.setStatus(Attachment.STATUS_TEMP);
        attachmentService.save(attachment);
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        GsonUtils.printData(response, json);
    }

    /**
     * 删除附件：
     * 会直接查询附件信息，从数据库中删除的同时，会删除源文件
     */
    @RequestMapping(value = "/delete", params = {"id"}, method = RequestMethod.DELETE)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void delete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        attachmentService.deleteById(id);
        GsonUtils.printSuccess(response);
    }

    /**
     * 删除附件：接收附件ID（多个附件使用逗号进行分割）
     * 会直接查询附件信息，从数据库中删除的同时，会删除源文件
     */
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void deleteGet(@RequestParam String ids, HttpServletRequest request, HttpServletResponse response) {
        String idArr[] = ids.split(",");
        attachmentService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    /**
     * 图片压缩
     *
     * @param id     文件ID
     * @param width  宽度
     * @param height 高度
     */
    @RequestMapping(value = "/thumb", params = {"id", "width", "height"}, method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void thumb(@RequestParam String id,
                      @RequestParam int width,
                      @RequestParam int height, HttpServletResponse response) {
        File file = AttachmentProvider.getFile(id);
        try {
            Thumbnails.of(file).size(width, height).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GsonUtils.printSuccess(response);
    }


    /**
     * 查看临时文件
     *
     * @param id 附件id
     * @throws IOException
     */
    @RequestMapping(value = "/temp/view", method = RequestMethod.GET, params = {"id"})
    @SuppressWarnings("unchecked")
    public void viewTemp(@RequestParam final String id, HttpServletRequest request, final HttpServletResponse response) throws IOException {
        AttachmentProvider.handle(id, new AttachmentHandler() {
            @Override
            public void handle(InputStream stream) {
                if (stream == null) {
                    throw new RuntimeException("附件[" + id + "]不存在!");
                }
                AttachmentVo vo = AttachmentContext.get();
                String contentType = vo.getContentType();
                response.setContentType(contentType);
                try {
                    if (contentType.contains("text/plain")) {
                        IOUtils.copy(stream, response.getWriter());
                    } else {
                        IOUtils.copy(stream, response.getOutputStream());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 获得指定id的文件的信息
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"id"})
    @ResponseBody
    public void getFileInfo(@RequestParam String id, HttpServletResponse response) {
        AttachmentVo vo = attachmentService.findById(id);
        GsonUtils.printData(response, vo);
    }

    /**
     * 查询附件列表
     * 参数:
     * bid:必须，业务id
     * btype：可选，业务类型
     * bclass：可选，业务类
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public void query(HttpServletRequest request, HttpServletResponse response) {
        String businessId = request.getParameter("bid");
        if (StringUtils.isEmpty(businessId)) {
            throw new IllegalArgumentException("查询附件列表时,必须指定业务ID!");
        }
        String businessType = request.getParameter("btype");
        String businessClass = request.getParameter("bclass");
        List<AttachmentVo> vos = attachmentService.queryByBusiness(businessId, businessType, businessClass);
        GsonUtils.printData(response, vos);
    }

    /**
     * 附件高级查询接口,含分页信息
     */
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    @ResponseBody
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        AttachmentBo bo = GsonUtils.wrapDataToEntity(request, AttachmentBo.class);
        PageVo vos = attachmentService.pageQuery(bo);
        GsonUtils.printData(response, vos);
    }

    /**
     * 根据附件的id（多个值使用逗号分隔）查询对应的附件信息
     * 返回附件列表信息
     */
    @RequestMapping(value = "/queryByIds", params = {"ids"}, method = RequestMethod.GET)
    @ResponseBody
    public void queryByIds(@RequestParam String ids, HttpServletResponse response) {
        List<AttachmentVo> vos = attachmentService.queryByIds(ids.split(","));
        GsonUtils.printData(response, vos);
    }

    /**
     * 下载指定id的文件
     *
     * @param id 附件的id
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET, params = {"id"})
    public void download(@RequestParam String id, final HttpServletResponse response) throws FileNotFoundException {
        final AttachmentVo vo = AttachmentProvider.getInfo(id);
        if (vo == null) {
            throw new EntityNotFoundException("ID为[" + id + "]的附件不存在或者已经被删除!");
        }
        if (AttachmentType.FILE.getValue().equals(vo.getAttachmentType())) {
            AttachmentProvider.handle(id, new AttachmentHandler() {
                @Override
                public void handle(InputStream stream) {
                    response.setContentType(vo.getFileType());
                    String fileName = vo.getFileName();
                    String disposition = null;//
                    try {
                        disposition = "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    response.setHeader("Content-disposition", disposition);
                    try {
                        IOUtils.copy(stream, response.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET, params = {"id"})
    public void view(@RequestParam String id, final HttpServletResponse response) throws IOException {
        AttachmentProvider.handle(id, new AttachmentHandler() {
            @Override
            public void handle(InputStream stream) {
                if (stream == null) {
                    return;
                }
                AttachmentVo vo = AttachmentContext.get();
                String contentType = vo.getContentType();
                response.setContentType(contentType);
                try {
                    if (contentType.contains("text/plain")) {
                        IOUtils.copy(stream, response.getWriter());
                    } else {
                        IOUtils.copy(stream, response.getOutputStream());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET, params = {"id"})
    public void image(@RequestParam String id, final HttpServletResponse response) {
        //获得附件信息
        AttachmentVo attachment = attachmentService.findById(id);
        if (attachment == null) {
            throw new EntityNotFoundException("ID为[" + id + "]的附件不存在或者已经被删除!");
        }
//        String fileType = attachment.getFileType();
//        if (fileType==null || !fileType.startsWith("image/")) {
//            throw new ImageTypeException("ID为[" + id + "]的附件的类型不是图片类型!");
//        }

        //获得附件的文件路径
        AttachmentProvider.handle(id, new AttachmentHandler() {
            @Override
            public void handle(InputStream stream) {
                try {
                    String base64 = ImageUtils.image2Base64(stream);
                    IOUtils.write("data:image/jpeg;base64," + base64, response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
