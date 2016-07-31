package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.BuildingInfoVO;
import org.sdgas.util.ChangeCharset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.apache.struts2.ServletActionContext.getResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-22
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */

//文件下载
@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<BuildingInfoVO> {

    private final BuildingInfoVO buildingInfoVO = new BuildingInfoVO();
    private static String SAVE_PATH_DIR = "D:\\iKnow\\file\\";

    @Override
    public String execute() throws Exception {
        String path = SAVE_PATH_DIR + buildingInfoVO.getFileName();
        File file = new File(path);// path是根据日志路径和文件名拼接出来的
        String filename = file.getName();// 获取日志文件名称
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        HttpServletResponse response = ServletActionContext.getResponse();

        response.reset();
        // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(buffer);// 输出文件
        os.flush();
        os.close();
        return SUCCESS;
    }

    @Override
    public BuildingInfoVO getModel() {
        return buildingInfoVO;
    }
}