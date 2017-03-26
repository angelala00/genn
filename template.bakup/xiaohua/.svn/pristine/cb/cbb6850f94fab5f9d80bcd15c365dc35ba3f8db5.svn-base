package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.User;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by ChenLong Date: 13-9-26
 */
public class UserVo {
    private Integer id = 0;
    private String mobile = "";
    private String mac = "";
    private String openUdid = "";
    private String token = "";
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String nickName = "";
    private Long totalPoints = 0L;//剩余分数（由于历史原因，兼容其他的app，所以名字比较别扭）
    private Long score = 0L;//总分
    private String alipayNo = "";
    private String qq = "";
    private String sce = "";
    private Integer level = 0;
    private String version ;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public UserVo(User user) {
        if (user == null)
            return;
        setId(user.getId());
        this.mac = user.getMac();
        this.openUdid = user.getOpenUdid();
        this.token = user.getToken();
        this.nickName = user.getNickName() + (user.getIsBlack() ? "锁定" : "");
        this.totalPoints = user.getSurplus();
        this.sce = user.getSce();
        this.score = user.getSurplus() ;
        this.alipayNo = user.getAlipayNo() ;
        this.password = user.getPassword();
        if (StringUtils.isNotBlank(user.getMobilePhone()))
            this.mobile = user.getMobilePhone();
        if (StringUtils.isNotBlank(user.getAlipayNo())) {
            setAlipayNo(user.getAlipayNo());
        }
        if (StringUtils.isNotBlank(user.getQq())) {
            setQq(user.getQq());
        }
        setLevel(user.getLevel());
    }

    public UserVo() {
        super();
    }

    public UserVo(String mobile, String mac, String openUdid, String token, String nickName, Long totalPoints) {
        super();
        this.mobile = mobile;
        this.mac = mac;
        this.openUdid = openUdid;
        this.token = token;
        this.nickName = nickName;
        this.totalPoints = totalPoints;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getSce() {
        return sce;
    }

    public void setSce(String sce) {
        this.sce = sce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
