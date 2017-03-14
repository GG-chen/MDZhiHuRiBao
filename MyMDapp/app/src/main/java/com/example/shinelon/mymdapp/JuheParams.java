package com.example.shinelon.mymdapp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Shinelon on 2017/3/10.
 */

public class JuheParams {
    public static final String JUHE_APIKEY = "2c61086389954c54a9624a282999b164";

    /*类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)*/
    public static final String JUHE_TOP = "top";
    public static final String JUHE_SHEHUI = "shehui";
    public static final String JUHE_GUONEI = "guonei";
    public static final String JUHE_GUOJI = "guoji";
    public static final String JUHE_YULE = "yule";
    public static final String JUHE_TIYU = "tiyu";
    public static final String JUHE_JUNSHI = "junshi";
    public static final String JUHE_KEJI = "keji";
    public static final String JUHE_CAIJING = "caijing";
    public static final String JUHE_SHISHANG = "shishang";
    public static final List<String> JUHE_LISTS = Arrays.asList(new String[]{JUHE_TOP,JUHE_SHEHUI,JUHE_GUONEI,JUHE_GUOJI,JUHE_YULE,JUHE_TIYU,JUHE_JUNSHI,JUHE_KEJI,JUHE_CAIJING,JUHE_SHISHANG});
}



