package com.yousufsohail.myimdb.entity;

/**
 * Created by yousuf on 28-Aug-17.
 */

public class Genres {
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", name = " + name + "]";
    }
}
