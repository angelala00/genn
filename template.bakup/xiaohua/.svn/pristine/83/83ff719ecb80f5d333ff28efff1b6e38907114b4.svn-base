package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Advertisement;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-4
 */
public class AdvertisementsResponse extends ResponseVo {
    private List<AdvertisementVo> advertisements;

    public List<AdvertisementVo> getAdvertisements() {
        return advertisements;
    }

    public void fillAdvertisements(List<Advertisement> advertisements) {
        List<AdvertisementVo> tmp = new ArrayList<AdvertisementVo>();
        AdvertisementVo vo = null;
        for (Advertisement advertisement : advertisements) {
            vo = new AdvertisementVo(advertisement);
            tmp.add(vo);
        }
        setAdvertisements(tmp);
    }

    public void setAdvertisements(List<AdvertisementVo> advertisements) {
        this.advertisements = advertisements;
    }

    public int getCount() {
        return CollectionUtils.isEmpty(advertisements) ? 0 : advertisements.size();
    }
}
