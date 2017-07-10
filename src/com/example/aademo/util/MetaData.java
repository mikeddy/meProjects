package com.example.aademo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.Serializable;
import java.util.Locale;

public class MetaData implements Serializable {

    private static final long serialVersionUID = -8646108651736532838L;

    // 应用名称
    protected String appId;
    // 应用的操作系统平台(Android/iOS/WP)
    protected String platform;
    // 手机的操作系统的版本
    protected String osVersion;
    // 手机的语言
    protected String language;
    // 手机设备的唯一ID
    protected String deviceId;
    // 国际国家码
    protected String mcc;
    // 手机网络运营商码
    protected String mnc;
    // 应用的当前版本
    protected String version;
    // 设备型号-厂商
    protected String moduleName;
    // 手机是否破解
    protected int isJailbroken;
    // 经度
    protected String longitude;
    // 纬度
    protected String latitude;

    protected String imei;
    protected String imsi;
    protected String networkType;

    public MetaData(Context context) {
        appId = context.getPackageName();
        platform = "Android";
        osVersion = android.os.Build.VERSION.RELEASE;
        language = Locale.getDefault().getCountry();
        deviceId = getDeviceUniqueId(context);
        mcc = "";
        mnc = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId();
        imsi = getDeviceIMSI(context);
        // 手机没插SIM卡，就取不到 imsi
        if (!StringUtil.isNull(imsi) && imsi.length() >= 5) {
            mcc = imsi.substring(0, 3);
            mnc = imsi.substring(3, 5);
        }
        moduleName = getMobileModel();
        isJailbroken = isRoot() ? 1 : 0;

//友盟统计中，部分手机会抛出SecurityException，因无法复现，暂时不统计地理位置
//		String[] location = getLatitudeAndLongitude(context);
//		longitude = location[0];
//		latitude = location[1];
        networkType = getNetworkType(context);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDeviceId() {
        if (StringUtil.isNull(deviceId)) {
            deviceId = "-1";
        }
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModuleName() {
        if (StringUtil.isNull(moduleName)) {
            moduleName = "未知";
        }
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getIsJailbroken() {
        return isJailbroken;
    }

    public void setIsJailbroken(int isJailbroken) {
        this.isJailbroken = isJailbroken;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getDeviceUniqueId(Context context) {
        final TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = "" + teleManager.getDeviceId();
        return deviceId.toString().toUpperCase();
    }

    public String getDeviceIMSI(Context context) {
        String imsi = "-";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (!StringUtil.isNull(tm.getSubscriberId())) {
                imsi = tm.getSubscriberId();
            }
        } catch (Exception e) {
        }
        return imsi;

    }

    public String getMobileModel() {
        return android.os.Build.MODEL;
    }

    public boolean isRoot() {
        boolean bool = false;
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {
        }
        return bool;
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return "NULL";
        }
        int netType = networkInfo.getType();

        if (netType == ConnectivityManager.TYPE_MOBILE) {
            return "GPRS";
        } else if (netType == ConnectivityManager.TYPE_WIFI) {
            return "WIFI";
        }
        return "Unknow";
    }
}