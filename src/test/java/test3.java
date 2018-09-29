import java.io.FileOutputStream;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * Created by zoujing on 2017/10/19.
 */
public class test3 {
    public static void main(String[] args) throws Exception {

        try
        {
            String TemplatePDF =test3.class.getResource("").getPath()+"/index.pdf"; //魔板路径
            String outFile = test3.class.getResource("").getPath()+"/out.pdf";  //生成新的pdf的路径
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
            Image image = Image.getInstance(test3.class.getResource("").getPath()+"/stamp.jpg");
            PdfContentByte under = ps.getOverContent(2);
            float x = 100;
            float y = 200;
            System.out.println(x);
            System.out.println(y);
            image.setAbsolutePosition(x, y);
            image.scaleToFit(200, 200);
            under.addImage(image);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
