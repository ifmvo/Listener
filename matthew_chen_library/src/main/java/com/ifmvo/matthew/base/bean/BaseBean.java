package com.ifmvo.matthew.base.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Matthew_Chen on 16/9/11.
 */
public class BaseBean implements Serializable, Cloneable {

    @DatabaseField(generatedId = true)
    public int id;

}
