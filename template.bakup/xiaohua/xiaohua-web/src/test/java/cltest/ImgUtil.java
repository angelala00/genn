package cltest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

/**
 * Created by chenlong on 2014/8/29.
 */
public class ImgUtil {
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) throws IOException
    {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx > sy)
        {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        }
        else
        {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM)
        { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
                    targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        }
        else
        {
            //固定宽高，宽高一定要比原图片大
            //target = new BufferedImage(targetW, targetH, type);
            target = new BufferedImage(800, 600, type);
        }

        return target;
    }
}
