package com.neo.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/15
 */
public class PdfTest {

    /**
     * 日志
     */
    private static final Logger LOGGER = LogManager.getLogger(PdfTest.class);
    // 边距
    private static final int MARGIN = 72;
    private static final int FONTSIZE_24 = 24;
    private static final int FONTSIZE_14 = 14;
    private static final int SPACINGAFTER30 = 30;
    private static final int SPACINGAFTER20 = 20;
    // 表格宽度 100%
    private static final int WIDTHPERCENTAGE = 100;
    // 表格列数
    private static final int NUMBERCOL = 2;

    public static void main(String[] args) {
        try {
            // 纸张大小A4y 上下左右的间距72
            Document document = new Document(PageSize.A4, MARGIN, MARGIN, MARGIN, MARGIN);
            // 获取PdfWriter的实例
            String fileName = "G:\\testPdf\\createSamplePDF.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            // 创建字体对象
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font1 = new Font(bfChinese, FONTSIZE_24);
            // 新建一个段落
            Paragraph p1 = new Paragraph("検査依頼書", font1);
            // 格式居中
            p1.setAlignment(Element.ALIGN_CENTER);
            // 行后间距
            p1.setSpacingAfter(SPACINGAFTER30);
            document.add(p1);

            Font font2 = new Font(bfChinese, FONTSIZE_14);
            Paragraph p2 = new Paragraph("検査依頼日：2020-02-13", font2);
            p2.setAlignment(Element.ALIGN_RIGHT);
            p2.setSpacingAfter(SPACINGAFTER30);
            document.add(p2);

            Paragraph p3 = new Paragraph("下記のとおり検査を依頼します。", font2);
            p3.setAlignment(Element.ALIGN_LEFT);
            p3.setSpacingAfter(SPACINGAFTER20);
            document.add(p3);

            Paragraph p4 = new Paragraph("基本情報", font2);
            p4.setAlignment(Element.ALIGN_LEFT);
            p4.setSpacingAfter(SPACINGAFTER20);
            document.add(p4);

            // 插入表格
            PdfPTable table = new PdfPTable(NUMBERCOL);
            table.addCell(new Phrase("受診者 ID", font2));
            table.addCell(new Phrase("xxxxxx", font2));
            table.addCell(new Phrase("名前（漢字）", font2));
            table.addCell(new Phrase("aaaaaa123", font2));
            table.addCell(new Phrase("名前（カナ）", font2));
            table.addCell(new Phrase("bbbbbb", font2));
            table.addCell(new Phrase("生年月日（西暦）", font2));
            table.addCell(new Phrase("1993-02-13", font2));
            table.addCell(new Phrase(" 検査コース", font2));
            table.addCell(new Phrase("検査コース1", font2));
            table.addCell(new Phrase("検査結果提供方法", font2));
            table.addCell(new Phrase("mail", font2));
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("オプションサービス", font2));
            cell.setRowspan(3);
            table.addCell(cell);
            table.addCell(new Phrase("AAAAA", font2));
            table.addCell(new Phrase("  ", font2));
            table.addCell(new Phrase("  ", font2));
            table.addCell(new Phrase("施設ID(診察番号など)", font2));
            table.addCell(new Phrase("xxxxxx", font2));
            // 100%
            table.setWidthPercentage(WIDTHPERCENTAGE);
            table.setSpacingAfter(SPACINGAFTER20);
            document.add(table);

            Paragraph p5 = new Paragraph("付帯情報", font2);
            p5.setAlignment(Element.ALIGN_LEFT);
            p5.setSpacingAfter(SPACINGAFTER20);
            document.add(p5);

            PdfPTable table2 = new PdfPTable(NUMBERCOL);
            table2.addCell(new Phrase("身長（cm）", font2));
            table2.addCell(new Phrase("170", font2));
            table2.addCell(new Phrase("体重（kg）", font2));
            table2.addCell(new Phrase("70", font2));
            // 100%
            table2.setWidthPercentage(WIDTHPERCENTAGE);
            document.add(table2);

            // 第二页免責事項
            document.newPage();
            Paragraph page2 = new Paragraph("免責事項", font1);
            page2.setAlignment(Element.ALIGN_CENTER);
            document.add(page2);

            // 第三页同意書
            document.newPage();
            Paragraph page3 = new Paragraph("同意書", font1);
            page3.setAlignment(Element.ALIGN_CENTER);
            page3.setSpacingAfter(80);
            document.add(page3);

            Paragraph page3_1 = new Paragraph("上記検査の実施に同意します。", font2);
            page3_1.setAlignment(Element.ALIGN_LEFT);
            page3_1.setSpacingAfter(80);
            document.add(page3_1);

            Paragraph page3_2 = new Paragraph("年          月          日", font2);
            page3_2.setAlignment(Element.ALIGN_LEFT);
            page3_2.setSpacingAfter(80);
            page3_2.setAlignment(Element.ALIGN_RIGHT);
            document.add(page3_2);

            Paragraph page3_3 = new Paragraph("署名 ：______________________", font2);
            page3_3.setAlignment(Element.ALIGN_LEFT);
            document.add(page3_3);
            document.close();
            LOGGER.info("pdf文档生成完成{}",fileName);
        } catch (DocumentException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
