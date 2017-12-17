package com.joybar.library.common.log;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LogLevel {


    public final static int TYPE_VERBOSE = 5; // 不过滤输出所有调试信息

    public final static int TYPE_DEBUG = 4; // debug过滤器，输出DEBUG、TYPE_INFO、TYPE_WARN、ERROR调试信息

    public final static int TYPE_INFO = 3; // info过滤器，输出INFO、TYPE_WARN、ERROR调试信息

    public final static int TYPE_WARN = 2; // waring过滤器，输出WARN和ERROR调试信息

    public final static int TYPE_ERROR = 1; // error过滤器，只输出ERROR调试信息

    public final static int TYPE_NONE = 0; // 什么都不输出


    @IntDef({TYPE_VERBOSE, TYPE_DEBUG, TYPE_INFO, TYPE_WARN, TYPE_ERROR, TYPE_NONE})

    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevelType {
    }
}