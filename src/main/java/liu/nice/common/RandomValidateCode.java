package liu.nice.common;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
 
/**
 * ��֤��������
 * Created by zhm on 2017/9/30.
 */
public class RandomValidateCode {
    public static final String RANDOMCODEKEY= "RANDOMVALIDATECODEKEY";//�ŵ�session�е�key
    //private String randString = "0123456789";//�������ֻ�����ֵ��ַ��� private String
    private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//�������ֻ����ĸ���ַ���
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//���������������ĸ��ϵ��ַ���
    private int width = 95;// ͼƬ��
    private int height = 25;// ͼƬ��
    private int lineSize = 40;// ����������
    private int stringNum = 4;// ��������ַ�����
 
    private Random random = new Random();
 
    /*
     * �������
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }
 
    /*
     * �����ɫ
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
 
    /**
     * �������ͼƬ
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage���Ǿ��л�������Image��,Image������������ͼ����Ϣ����
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// ����Image�����Graphics����,�Ķ��������ͼ���Ͻ��и��ֻ��Ʋ���
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // ���Ƹ�����
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // ��������ַ�
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        //�����ɵ�����ַ������浽session�У���jsp����ͨ��session.getAttribute("RANDOMCODEKEY")��
        //������ɵ���֤�룬Ȼ����û�����Ľ��бȽ�
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        g.dispose();
        try {
            // ���ڴ��е�ͼƬͨ��������ʽ������ͻ���
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /*
     * �����ַ���
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }
 
    /*
     * ���Ƹ�����
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
 
    /*
     * ��ȡ������ַ�
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}