package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Exchange;
import com.cjteam.xiao.service.doublenine.DoubleNineInterfaceException;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionResponse;
import com.cjteam.xiao.service.doublenine.model.DNMobileNotify;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
public interface DoubleNineService {
    void chargeForMobile(Exchange exchange) throws DoubleNineInterfaceException;

    void chargeForQb(Exchange exchange) throws DoubleNineInterfaceException;

    void progressChargeResult(String appId,DNMobileNotify mobileNotify) throws DoubleNineInterfaceException, IOException, NoSuchAlgorithmException;

    TelephoneRegionResponse queryRegion(String appId,String mobile) throws NoSuchAlgorithmException, JAXBException, IOException;
}
