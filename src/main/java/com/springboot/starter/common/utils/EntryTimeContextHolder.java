package com.springboot.starter.common.utils;

/**
 * @author linhuanzhen
 * 请求起始时间记录线程
 */
public class EntryTimeContextHolder {

    private EntryTimeContextHolder() {
    }

    private static final ThreadLocal<Long> ENTRY_TIME = new ThreadLocal<>();

    public static void setEntryTime(long time) {
        ENTRY_TIME.set(time);
    }

    public static Long getEntryTime(){
        return ENTRY_TIME.get();
    }

    public static void clear(){
        ENTRY_TIME.remove();
    }
}
