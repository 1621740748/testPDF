import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.util.List;

/**
 * Created by zoujing on 2017/10/19.
 */
public class test {
    public static void main(String[] args) throws Exception {
//        // 模板文件路径
//        String templatePath = "/Users/zoujing/Downloads/jianli.pdf";
//        // 生成的文件路径
//        String targetPath = "/Users/zoujing/Downloads/target.pdf";
//        // 书签名
//        String fieldName = "field";
//        // 图片路径
//        String imagePath = "/Users/zoujing/Downloads/logo.jpg";
//
//        // 读取模板文件
//        InputStream input = new FileInputStream(new File(templatePath));
//        PdfReader reader = new PdfReader(input);
//        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
//        // 提取pdf中的表单
//        AcroFields form = stamper.getAcroFields();
//        form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
//
//        // 通过域名获取所在页和坐标，左下角为起点
//        int pageNo = form.getFieldPositions(fieldName).get(0).page;
//        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
//        float x = signRect.getLeft();
//        float y = signRect.getBottom();
//
//        // 读图片
//        Image image = Image.getInstance(imagePath);
//        // 获取操作的页面
//        PdfContentByte under = stamper.getOverContent(pageNo);
//        // 根据域的大小缩放图片
//        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
//        // 添加图片
//        image.setAbsolutePosition(x, y);
//        under.addImage(image);
//
//        stamper.close();
//        reader.close();

        try
        {
            String TemplatePDF = "/Users/zoujing/Downloads/jianli.pdf"; //魔板路径
            String outFile = "/Users/zoujing/Downloads/test.pdf"; //生成新的pdf的路径
            PdfReader reader = new PdfReader(TemplatePDF);
            PdfStamper ps = new PdfStamper(reader, new FileOutputStream(outFile)); // 生成的输出流

            AcroFields s = ps.getAcroFields();
            // 插入图片
            insertImage(ps, s);
            ps.close();
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
    public static void insertImage(PdfStamper ps, AcroFields s)
    {


        try
        {
            List<AcroFields.FieldPosition> list = s.getFieldPositions("QR_CODE");
            Rectangle signRect = list.get(0).position;


            Image image = Image.getInstance("/Users/zoujing/Downloads/logo.jpg");
            PdfContentByte under = ps.getOverContent(2);
            float x = signRect.getLeft();
            float y = signRect.getBottom();
            System.out.println(x);
            System.out.println(y);
            image.setAbsolutePosition(x, y);
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());


            under.addImage(image);
        }
        catch (Exception e)
        {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
