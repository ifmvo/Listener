package com.ifmvo.matthew.base.parser;

/**
 * Created by ZongfaHe on 16/3/16.
 */
public interface Parser<T> {
     T parse(String jsonStr);

    /**
     * Only the GroupParser needs to implement this.
     */
//    abstract T parseArray(String str);
}
