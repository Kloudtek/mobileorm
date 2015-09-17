package com.kloudtek.mobileorm.examples;

import com.kloudtek.mobileorm.DbTable;
import com.kloudtek.mobileorm.ORMAnnotationProcessor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yannick on 9/17/15.
 */
@DbTable("dbclass1")
public class DbClass1 {
    private Long id;
    private Date date;
    private Date timestamp;
    private byte[] data;
    private Serializable serializableData;
    private Test enumOrd;
    private Test enumStr;
    private SomeObj someObj;

    public enum Test {
        VAL1, VAL2
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Serializable getSerializableData() {
        return serializableData;
    }

    public void setSerializableData(Serializable serializableData) {
        this.serializableData = serializableData;
    }

    public Test getEnumOrd() {
        return enumOrd;
    }

    public void setEnumOrd(Test enumOrd) {
        this.enumOrd = enumOrd;
    }

    public Test getEnumStr() {
        return enumStr;
    }

    public void setEnumStr(Test enumStr) {
        this.enumStr = enumStr;
    }

    public SomeObj getSomeObj() {
        return someObj;
    }

    public void setSomeObj(SomeObj someObj) {
        this.someObj = someObj;
    }


    public static void main(String[] args) throws Exception {
        com.sun.tools.javac.Main.main(new String[]{
                "-proc:only",
                "-processor", ORMAnnotationProcessor.class.getName(),
                "examples/src/main/java/com/kloudtek/mobileorm/examples/DbClass1.java"
        });
    }
}
