package com.zzz.easyshare.excption;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:35
 */
public class MethodNotImplementedException extends RuntimeException {

    public MethodNotImplementedException() {

    }

    public MethodNotImplementedException(String s) {
        super(s);
    }
}