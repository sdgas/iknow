package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.BuildingInfoVO;
import org.sdgas.base.PageView;
import org.sdgas.model.BuildingInfo;
import org.sdgas.model.UserInfo;
import org.sdgas.service.BuildingInfoService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Component("buildingInfo")
@Scope("prototype")
public class BuildingInfoAction extends MyActionSupport implements ModelDriven<BuildingInfoVO> {

    private BuildingInfoVO buildingInfoVO = new BuildingInfoVO();
    private BuildingInfoService buildingInfoService;

    private static String SAVE_PATH_DIR = "D:/iKnow/file/";

    private static final Logger logger = LogManager.getLogger(NoticeAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    UserInfo user = (UserInfo) session.getAttribute("person");
    private String ip = (String) session.getAttribute("ip");

    //新增楼宇收费标准
    @Override
    public String execute() {

        String name = uploadAttachment(buildingInfoVO.getUploadFile(), buildingInfoVO.getFileName(), SAVE_PATH_DIR);

        BuildingInfo buildingInfo = new BuildingInfo();

        try {
            buildingInfo.setStreet(buildingInfoVO.getStreet());
            buildingInfo.setBuildingName(buildingInfoVO.getBuildingName());
            buildingInfo.setAddress(buildingInfoVO.getAddress());
            buildingInfo.setProjectId(buildingInfoVO.getProjectId());
            buildingInfo.setShorthandCode(buildingInfoVO.getShorthandCode());
            buildingInfo.setUserNum(buildingInfoVO.getUserNum());
            buildingInfo.setActualUserNum(buildingInfoVO.getActualUserNum());
            buildingInfo.setConstructionOrganization(buildingInfoVO.getConstructionOrganization());
            buildingInfo.setLocation(buildingInfoVO.getLocation());
            buildingInfo.setMeterType(buildingInfoVO.getMeterType());
            buildingInfo.setGasType(buildingInfoVO.getGasType());
            buildingInfo.setContractId(buildingInfoVO.getContractId());
            if (!buildingInfoVO.getSignatureDate().equals(""))
                buildingInfo.setSignatureDate(ChangeTime.parseStringToShortDate(buildingInfoVO.getSignatureDate()));
            buildingInfo.setCourtyard(buildingInfoVO.getCourtyard());
            buildingInfo.setStandPipe(buildingInfoVO.getStandPipe());
            buildingInfo.setGasPoints(buildingInfoVO.getGasPoints());
            buildingInfo.setInstalledInfo(buildingInfoVO.getInstalledInfo());
            buildingInfo.setMainValve(buildingInfoVO.getMainValve());
            buildingInfo.setValve(buildingInfoVO.getValve());
            buildingInfo.setDn15(buildingInfoVO.getDn15());
            buildingInfo.setOthers(buildingInfoVO.getOthers());
            buildingInfo.setCapacityGasPrice(buildingInfoVO.getCapacityGasPrice());
            buildingInfo.setOtherPrice(buildingInfoVO.getOtherPrice());
            if (!buildingInfoVO.getInstallationTime().equals(""))
                buildingInfo.setInstallationTime(ChangeTime.parseStringToShortDate(buildingInfoVO.getInstallationTime()));
            buildingInfo.setUseGas(buildingInfoVO.getUseGas());
            buildingInfo.setFollowed(buildingInfoVO.getFollowed());
            buildingInfo.setRemark(buildingInfoVO.getRemark());
            buildingInfo.setFile(name);

            buildingInfoService.save(buildingInfo);
        } catch (Exception e) {
            logger.error(e);
            buildingInfoVO.setResultMessage("<script>alert(' 楼宇收费信息保存失败！');location.href='/page/building/addInfo.jsp';</script>");
            return ERROR;
        }
        logger.info("用户:" + user.getUserName() + ",新增一则楼宇收费信息（模板ID：" + buildingInfo.getInfoId() + ")");
        buildingInfoVO.setResultMessage("<script>alert(' 楼宇收费信息保存成功！');location.href='/page/building/addInfo.jsp';</script>");
        return SUCCESS;
    }

    //查找楼宇收费标准
    public String findBuildingInfoByField() {
        if (UserUtil.checkUserLogIn(user)) {
            buildingInfoVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<BuildingInfo> pageView = new PageView<BuildingInfo>(maxResult,
                buildingInfoVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("infoId", "DESC");
        /** 列表条件 **/
        String temp = "buildingName like '%" + buildingInfoVO.getMsg().trim() + "%'";
        StringBuffer jpql = new StringBuffer(temp);


        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(buildingInfoService.getScrollData(BuildingInfo.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        buildingInfoVO.setPageView(pageView);

        view = "/page/building/search.jsp?msg=" + buildingInfoVO.getMsg().trim();
        return VIEW;
    }

    @Resource(name = "buildingInfoServiceImpl")
    public void setBuildingInfoService(BuildingInfoService buildingInfoService) {
        this.buildingInfoService = buildingInfoService;
    }

    @Override
    public BuildingInfoVO getModel() {
        return buildingInfoVO;
    }

    private String uploadAttachment(File file, String fileName, String path) {
        // 得到保存上传文件的目录的真实路径
        File dir = new File(path);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] temp = fileName.split("\\\\");
        fileName = temp[temp.length - 1];
        String name = fileName;
        try {
            FileInputStream is = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(new File(dir, name));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException f) {
            logger.error(f);
        } catch (IOException ioe) {
            logger.error(ioe);
        }
        return name;
    }
}
