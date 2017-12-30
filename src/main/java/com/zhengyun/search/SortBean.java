package com.zhengyun.search;

/**
 * Created by 听风 on 2017/12/22.
 */
public class SortBean {

    private String id;

    private int times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void increase(){
        times++;
    }

    public static void main(String[] args) {
        String s = "中国";
        System.out.println(s.split(""));
    }

    @Override
    public String toString() {
        return "SortBean{" +
                "id='" + id + '\'' +
                ", times=" + times +
                '}';
    }
}
