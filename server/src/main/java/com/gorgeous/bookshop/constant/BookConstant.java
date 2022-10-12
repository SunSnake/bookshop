package com.gorgeous.bookshop.constant;

public class BookConstant {

    public static final String ORDER_SALE = "0";
    public static final String ORDER_BUY = "1";

    public static final String apikey = "13728.2479869c1222a3448a4e376c1c369b0d.50a042a06a4ca41f83cfec6193ac9e42";
    public static final String isbnUrl = "https://api.jike.xyz/situ/book/isbn/#?apikey=" + apikey;

    public static final String appId = "wx7f288149602c411e";
    public static final String appSecret = "20ec7d49967cf919df6c7979b0d5767f";
    public static final String wxValidUrl = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&appid=" + appId + "&secret=" + appSecret + "&js_code=";

    public static final String[] unusedTags = new String[]{"douban", "doubanScore", "numScore", "brand", "weight", "size", "pages", "localPhotoUrl", "price", "froms", "num", "createTime", "uptime", "reviews", "tags"};

}
