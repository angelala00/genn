package com.cjteam.xiao.service.impl;

import com.google.gson.Gson;
import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.model.DailyRegionIntegralStat;
import com.cjteam.xiao.model.IpLocation;
import com.cjteam.xiao.repository.DailyRegionIntegralLimitRepository;
import com.cjteam.xiao.repository.DailyRegionIntegralStatRepository;
import com.cjteam.xiao.repository.IpLocationRepository;
import com.cjteam.xiao.service.RegionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ChenLong on 14-3-17.
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    private static final Logger LOG = LoggerFactory.getLogger(RegionServiceImpl.class);

    static final String taobaoIpLibraryInterfaceUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    @Override
    public boolean check(String appId, String ip, String channelCode, Integer score) {
        if (StringUtils.isBlank(ip))
            return true;
        IpLocation ipLocation = null;
        try {
            ipLocation = getIpLocationByIp(ip);
        } catch (IOException e) {
            //ignore
            LOG.error(e.getLocalizedMessage(), e);
            return true;
        }
        if (null == ipLocation) {
            LOG.warn("uncontrollable ip {}", ip);
            return true;
        }
        Date today = new Date();
        DailyRegionIntegralLimit limit = regionIntegralLimitRepository.findByAppIdAndChannelAndRegionNameAndCityName(appId, channelCode, ipLocation.getRegion(), ipLocation.getCity());
        if (limit == null) {
            //new region add
            limit = new DailyRegionIntegralLimit(appId, ipLocation.getRegionId(), ipLocation.getRegion(), ipLocation.getCityId(), ipLocation.getCity(), channelCode);
            regionIntegralLimitRepository.save(limit);
        }

        DailyRegionIntegralStat regionIntegralStat = regionIntegralStatRepository.findOneByAppIdAndChannelAndRegionNameAndCityNameAndDate(appId, channelCode, ipLocation.getRegion(), ipLocation.getCity(), today);
        if (regionIntegralStat == null) {
            regionIntegralStat = new DailyRegionIntegralStat(appId, ipLocation.getRegionId(), ipLocation.getRegion(), ipLocation.getCityId(), ipLocation.getCity(), channelCode, today, 0);
        }

        if (limit.getScore() <= 0 || limit.getScore() >= regionIntegralStat.getScore()){
            regionIntegralStat.setScore(regionIntegralStat.getScore() + score);
            regionIntegralStatRepository.save(regionIntegralStat);
            return true;
        }
        return false;
    }

    @Override
    public IpLocation getIpLocationByIp(String ip) throws IOException {
        IpLocation locationInfo = ipLocationRepository.findByIp(ip);
        if (null == locationInfo) {
            locationInfo = queryLocationInfoFromSinaIPInterface(ip);
            if (null != locationInfo) {
                ipLocationRepository.save(locationInfo);
            }
        }
        return locationInfo;
    }

    @Override
    public IpLocation queryLocationInfoFromTaobaoIPInterface(String ip) throws IOException {
        if (StringUtils.isBlank(ip))
            return null;
        HttpGet get = new HttpGet(taobaoIpLibraryInterfaceUrl + ip);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(get);
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        httpResponse.getEntity().writeTo(writer);
        String tmp = writer.toString();
        IPTaoBao ipTaoBao = null;
        try {
            ipTaoBao = new Gson().fromJson(tmp, IPTaoBao.class);
            LOG.debug("query location info from internet for ip {},result is {}", ip, ipTaoBao);
            if (!StringUtils.equals("0", ipTaoBao.getCode())) {
                LOG.warn("{} location query fail.", ip);
                return null;
            }
        } catch (Throwable t) {
            LOG.warn("gson parse error : request url is {},and response is {}", taobaoIpLibraryInterfaceUrl + ip, tmp);
            return null;
        }
        IpLocation ipLocation = new IpLocation(ip);
        Data ipData = ipTaoBao.getData();
        ipLocation.setRegion(ipData.getRegion());
        ipLocation.setRegionId(ipData.getRegion_id());
        ipLocation.setCountry(ipData.getCountry());
        ipLocation.setCountryId(ipData.getCountry_id());
        ipLocation.setCounty(ipData.getCounty());
        ipLocation.setCountyId(ipData.getCounty_id());
        ipLocation.setArea(ipData.getArea());
        ipLocation.setAreaId(ipData.getArea_id());
        ipLocation.setCity(ipData.getCity());
        ipLocation.setCityId(ipData.getCity_id());
        ipLocation.setIsp(ipData.getIsp());
        ipLocation.setIspId(ipData.getIsp_id());
        return ipLocation;
    }

    @Override
    public IpLocation queryLocationInfoFromSinaIPInterface(String ip) throws IOException {
        if (StringUtils.isBlank(ip))
            return null;
        HttpGet get = new HttpGet("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(get);
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        httpResponse.getEntity().writeTo(writer);
        String tmp = writer.toString();
        IPSina ipSina = null;
        try {
            ipSina = new Gson().fromJson(tmp, IPSina.class);
            if (ipSina.getRet() == null || ipSina.getRet() != 1) {
                LOG.warn("{} location query fail.", ip);
                return null;
            }
        } catch (Throwable t) {
            LOG.warn("gson parse error : request url is {},and response is {}", taobaoIpLibraryInterfaceUrl + ip, tmp);
            return null;
        }
        IpLocation ipLocation = new IpLocation(ip);
        ipLocation.setRegion(ipSina.getProvince());
        ipLocation.setCountry(ipSina.getCountry());
        ipLocation.setCity(ipSina.getCity());
        ipLocation.setIsp(ipSina.getIsp());
        return ipLocation;
    }


    @Autowired
    DailyRegionIntegralStatRepository regionIntegralStatRepository;
    @Autowired
    IpLocationRepository ipLocationRepository;
    @Autowired
    DailyRegionIntegralLimitRepository regionIntegralLimitRepository;

    class IPSina {
        Integer ret;
        String start;
        String end;
        String country;
        String province;
        String city;
        String district;
        String isp;
        String type;
        String desc;

        public Integer getRet() {
            return ret;
        }

        public void setRet(Integer ret) {
            this.ret = ret;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    class IPTaoBao {
        private String code;
        private Data data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "IPTaoBao{" +
                    "code='" + code + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    class Data {
        private String ip;
        private String country;
        String area;
        String region;
        String city;
        String county;
        String isp;
        String country_id;
        String area_id;
        String region_id;
        String city_id;
        String county_id;
        String isp_id;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCounty_id() {
            return county_id;
        }

        public void setCounty_id(String county_id) {
            this.county_id = county_id;
        }

        public String getIsp_id() {
            return isp_id;
        }

        public void setIsp_id(String isp_id) {
            this.isp_id = isp_id;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "ip='" + ip + '\'' +
                    ", country='" + country + '\'' +
                    ", area='" + area + '\'' +
                    ", region='" + region + '\'' +
                    ", city='" + city + '\'' +
                    ", county='" + county + '\'' +
                    ", isp='" + isp + '\'' +
                    ", country_id='" + country_id + '\'' +
                    ", area_id='" + area_id + '\'' +
                    ", region_id='" + region_id + '\'' +
                    ", city_id='" + city_id + '\'' +
                    ", county_id='" + county_id + '\'' +
                    ", isp_id='" + isp_id + '\'' +
                    '}';
        }
    }
}
