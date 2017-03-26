package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Withdraw;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 14-1-3
 */
public class AlipayRecordsExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HSSFSheet excelSheet = workbook.createSheet("支付宝提现数据");
        setExcelHeader(excelSheet);

        List<Withdraw> WithdrawList = (List<Withdraw>) model.get("alipayRecords");
        setExcelRows(excelSheet, WithdrawList);

    }

    public void setExcelHeader(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("序号");
        excelHeader.createCell(1).setCellValue("用户名称");
        excelHeader.createCell(2).setCellValue("用户手机号码");
        excelHeader.createCell(3).setCellValue("提取的支付宝帐号");
        excelHeader.createCell(4).setCellValue("金额");
        excelHeader.createCell(5).setCellValue("创建时间");
        excelHeader.createCell(6).setCellValue("当前支付状态");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<Withdraw> withdrawList) {
        int record = 1;
        for (Withdraw withdraw : withdrawList) {
            HSSFRow excelRow = excelSheet.createRow(record);
            excelRow.createCell(0).setCellValue(record++);
            excelRow.createCell(1).setCellValue(withdraw.getUser().getNickName());
            excelRow.createCell(2).setCellValue(withdraw.getUser().getMobilePhone());
            excelRow.createCell(3).setCellValue(withdraw.getAlipayNo());
            excelRow.createCell(4).setCellValue(withdraw.getAmountMoney());
            excelRow.createCell(5).setCellValue(new DateTime(withdraw.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss"));
            excelRow.createCell(6).setCellValue(withdraw.getState().getMeaning() + "(" + withdraw.getState().getCode() + ")");
        }
    }
}