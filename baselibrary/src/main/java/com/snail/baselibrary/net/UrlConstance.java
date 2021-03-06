package com.snail.baselibrary.net;

import com.snail.baselibrary.config.Config;
import com.snail.baselibrary.config.Environment;

/**
 * @author changsunhaipeng
 * @date 2017/10/21
 */

public class UrlConstance {

    public static final String Host_URL_DEV = "http://app.thinkerol.com/";
    public static final String HOST_URL_RELEASE = "http://app.thinkerol.com/";
    public static final String HOST_URL = Config.getENV() == Environment.DEV ? Host_URL_DEV : HOST_URL_RELEASE;

    public static final String CMS_Host_URL_DEV = "http://www.thinkerol.com/";
    public static final String CMS_HOST_URL_RELEASE = "http://www.thinkerol.com/";
    public static final String CMS_HOST_URL = Config.getENV() == Environment.DEV ? CMS_Host_URL_DEV : CMS_HOST_URL_RELEASE;

    public static final String ART_LIST = "share/articles";
    public static final String HOME_BANNER = "cms/banners";
}
