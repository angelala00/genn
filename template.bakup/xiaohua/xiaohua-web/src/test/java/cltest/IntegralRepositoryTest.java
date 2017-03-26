package cltest;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import  javax.imageio.ImageIO ;
import javax.annotation.Resource;

import com.cjteam.xiao.repository.IntegralRepository;
import com.cjteam.xiao.service.IOSMessageProvider;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.cjteam.xiao.dao.DailyRegionIntegralLimitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class IntegralRepositoryTest extends TestBasis  {

    @Resource
    private IntegralRepository integralRepository ;
    @Test
	public void countByUserIdAndChannelCodeAndCreateTime () {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = formatter.format(currentTime);
        try {
            formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParsePosition pos = new ParsePosition(8);
        Date currentTime1 = formatter.parse(dateString, pos);
        System.out.println(currentTime1.toString());
    }

}
