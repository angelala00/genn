package com.cjteam.xiao.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;
import com.gif4j.TextPainter;
import com.gif4j.Watermark;
import org.springframework.stereotype.Component;

@Component
public class GifUtil {
    /**
     * 缩放gif图片
     *
     * @param src
     * @param dest
     * @param width
     * @param height
     * @throws IOException
     */
    public  void makeGif(File src, File dest, int width, int height)
            throws IOException {
        GifImage gifImage = GifDecoder.decode(src);// 创建一个GifImage对象.
        GifImage resizeIMG = GifTransformer.resize(gifImage, width, height,
                true);
        GifEncoder.encode(resizeIMG, dest);
    }


    public  void makeGif(String src, String dest, int width, int height)
            throws IOException {
        GifImage gifImage = GifDecoder.decode(new File(src));// 创建一个GifImage对象.
        makeGif(new File(src), new File(dest), gifImage.getScreenWidth() / 2,
                gifImage.getScreenHeight() / 2);
    }


    public  void makeGif(File src, File dest) throws IOException {
        GifImage gifImage = GifDecoder.decode(src);// 创建一个GifImage对象.
        makeGif(src, dest, gifImage.getScreenWidth() / 2, gifImage
                .getScreenHeight() / 2);
    }


    public  void makeGif(String src, String dest) throws IOException {
        makeGif(new File(src), new File(dest));
    }




    /**
     * 在图片中加水印
     * @param src
     * @param watermarkText
     * @param dest
     * @throws IOException
     */
    public  void addTextWatermarkToGif(File src, String watermarkText, File dest)throws IOException {


        //水印初始化、设置（字体、样式、大小、颜色）
        TextPainter textPainter = new TextPainter(new Font("黑体", Font.ITALIC, 12));
        textPainter.setOutlinePaint(Color.WHITE);
        BufferedImage renderedWatermarkText = textPainter.renderString(watermarkText, true);

        //图片对象
        GifImage gf = GifDecoder.decode(src);

        //获取图片大小
        int iw = gf.getScreenWidth();
        int ih = gf.getScreenHeight();

        //获取水印大小
        int tw = renderedWatermarkText.getWidth();
        int th = renderedWatermarkText.getHeight();

        //水印位置
        Point p = new Point();
        p.x = iw - tw - 5;
        p.y = ih - th - 4;

        //加水印
        Watermark watermark = new Watermark(renderedWatermarkText, p);
        gf = watermark.apply(GifDecoder.decode(src), true);

        //输出
        GifEncoder.encode(gf, dest);
    }

}