package com.jt.msg.bean;

/**
 * since 2015/4/27.
 */
public class SystemContext {

    private static ThreadLocal<Integer> pageIndex = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

    public static Integer getPageIndex() {
        return pageIndex.get();
    }

    public static void setPageIndex(int _pageIndex) {
        pageIndex.set(_pageIndex);
    }

    public static Integer getPageSize() {
        return pageSize.get();
    }

    public static void setPageSize(int _pageSize) {
        pageSize.set(_pageSize);
    }

    public static void removePageIndex() {
        pageIndex.remove();
    }

    public static void removePageSize() {
        pageSize.remove();
    }
}
