import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zoujing on 2017/10/19.
 */
public class test2 {
    public static void main(String[] args) throws DocumentException,
            IOException {
        // 要输出的pdf文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new java.io.File("/Users/zoujing/Downloads/target.pdf")));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 将pdf文件先加水印然后输出
        setWatermark(bos, "/Users/zoujing/Downloads/jianli.pdf", format.format(cal.getTime())
                + "  下载使用人：" + "zoujing", 16);
    }

    public static void setWatermark(BufferedOutputStream bos, String input,
                                    String waterMarkName, int permission) throws DocumentException,
            IOException {

        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.2f);
        for (int i = 1; i < total; i++) {
            content = stamper.getOverContent(i);// 在内容上方加水印
            //content = stamper.getUnderContent(i);//在内容下方加水印

            content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 50);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "爱奇艺版权所有！", 200,350, 55);
            com.itextpdf.text.Image image =  com.itextpdf.text.Image.getInstance("/Users/zoujing/Downloads/stamp.jpg");
            /*img.setAlignment(Image.LEFT | Image.TEXTWRAP);
            img.setBorder(Image.BOX);
            img.setBorderWidth(10);
            img.setBorderColor(BaseColor.WHITE);
            img.scaleToFit(1000, 72);//大小
            img.setRotationDegrees(-30);//旋转 */
            image.setAbsolutePosition(400, 150); // set the first background image of the absolute
            image.scaleToFit(100,100);
            content.addImage(image);
            content.setColorFill(BaseColor.BLACK);
            content.setFontAndSize(base, 8);
            content.showTextAligned(Element.ALIGN_CENTER, "下载时间："
                    + waterMarkName + "", 300, 10, 0);
            content.endText();

        }
        stamper.close();
    }
}
