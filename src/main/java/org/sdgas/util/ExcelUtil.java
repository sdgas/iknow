package org.sdgas.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;


/**
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 *
 * @author wilson.he
 */

@Service
@Transactional
public class ExcelUtil {

    private static ExcelUtil eu = new ExcelUtil();
    private final static Logger logger = Logger.getLogger(ExcelUtil.class);

    private UserInfoService userInfoService;


    /**
     * 根据标题获取相应的方法名称
     *
     * @param eh
     * @return
     */
    private String getMethodName(ExcelHeader eh) {
        String mn = eh.getMethodName().substring(3);
        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
        return mn;
    }


    //考勤报表导出
    private Workbook handleExcel(List objs, Class clz, boolean isXssf, String message, String dep) {
        XSSFWorkbook wb = new XSSFWorkbook();

        return wb;
    }

    /**
     * 导出对象到Excel，直接新建一个Excel完成导出，基于路径的导出，不基于模板
     *
     * @param outPath 导出路径
     * @param objs    对象列表
     * @param clz     对象类型
     * @param isXssf  是否是2007版本
     */

    public void exportExcelByPath(String outPath, List objs, Class clz, boolean isXssf, String message, String dep) {
        Workbook wb = handleExcel(objs, clz, isXssf, message, dep);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于流
     *
     * @param os     输出流
     * @param objs   对象列表
     * @param clz    对象类型
     * @param isXssf 是否是2007版本
     */
    public void exportExcelByPath(OutputStream os, List objs, Class clz, boolean isXssf, String message, String dep) {
        try {
            Workbook wb = handleExcel(objs, clz, isXssf, message, dep);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表
     *
     * @param path     文件路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     * @return
     */
    public List<Object> readExcelByPath(String path, Class clz, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File(path));
            return readExcel(wb, clz, readLine, tailLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     * @return 对象列表
     */
    public List<Object> readExcelByPath(String path, Class clz) {
        return this.readExcelByPath(path, clz, 0, 0);
    }

    /**
     * 取对应单元格类型的值
     *
     * @param c 列数
     * @return 单元格的值
     */
    private String getCellValue(Cell c) {
        String o = null;
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(c)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (c.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = c.getDateCellValue();
                    o = sdf.format(date);
                } else if (c.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = c.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    o = sdf.format(date);
                } else {
                    double value = c.getNumericCellValue();
                    CellStyle style = c.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    o = format.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }

    //读取excel表格信息
    public List<Object> readExcel(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);     //取第一张表
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);  //开始行，主题栏

            objs = new ArrayList<Object>();
            Map<Integer, String> maps = getHeaderMap(row, clz);   //设定对应的字段顺序与方法名
            if (maps == null || maps.size() <= 0)
                throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");//与order顺序不符
            for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {     //取数据
                row = sheet.getRow(i);
                Object obj = clz.newInstance();        //   调用无参结构
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci).substring(3);  //消除get
                    mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
                    if ("workDate".equals(mn))
                        BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                    else if (this.getCellValue(c).matches("\\d{4}-\\d{2}-\\d{2}"))
                        BeanUtils.copyProperty(obj, mn, ChangeTime.parseStringToShortDate(this.getCellValue(c)));
                    else
                        BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                }
                objs.add(obj);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return objs;
    }

    private List<ExcelHeader> getHeaderList(Class clz) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        //获取全部get/is方法
        Method[] ms = clz.getDeclaredMethods();
        for (Method m : ms) {
            String mn = m.getName();
            if (mn.startsWith("get") || mn.startsWith("is")) {
                if (m.isAnnotationPresent(ExcelResources.class)) {
                    ExcelResources er = m.getAnnotation(ExcelResources.class);
                    headers.add(new ExcelHeader(er.title(), er.order(), mn));
                }
            }
        }
        return headers;
    }

    /**
     * 判断excel标题与对象的标签标题是否一致
     *
     * @param titleRow 开始行
     * @param clz      对象
     * @return 判断类型
     */

    private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
        List<ExcelHeader> headers = getHeaderList(clz);    //取后台标题
        Map<Integer, String> maps = new HashMap<Integer, String>();
        for (Cell c : titleRow) {
            String title = this.getCellValue(c);        //取excel的标题栏
            for (ExcelHeader eh : headers) {
                if (eh.getTitle().equals(title.trim())) {      //相等则设定对应的字段顺序与方法名
                    maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
                    break;
                }
            }
        }
        return maps;
    }

    private void copyDefaultCellStyle(XSSFDataFormat format, Cell cell, XSSFCellStyle cs, int i) {
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
        cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cs.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
        if (i == 1)
            cs.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:s"));
        if (i == 2)
            cs.setDataFormat(format.getFormat("0"));
        cell.setCellStyle(cs);
    }

    public ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        return eu;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private String matchNum(String str) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group();
        }
        return "ERR";
    }
}