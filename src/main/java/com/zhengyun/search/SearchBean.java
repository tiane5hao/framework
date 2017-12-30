package com.zhengyun.search;

import java.util.*;

/**
 * Created by 听风 on 2017/12/22.
 */
public class SearchBean {

    private Map<String, Set<String>> keyMap = new HashMap();

    private Map<String, Object> objMap = new HashMap();
    /**
     * @param id
     * @param obj
     * @Description: 添加搜索记录
     */
    public void add(String id, String searchKey, String obj) {
        objMap.put(id, obj);

        Set<String> set = new HashSet<String>(searchKey.length());
        String[] s = searchKey.split("");
        for(String c : s){
            set.add(c);
        }
        keyMap.put(id, set);
    }






    public static void main(String[] args) {
        SearchBean searchBean = new SearchBean();
        searchBean.add("1", "你好！", "你好！");
        searchBean.add("2", "你好！我是张三。", "你好！我是张三。");
        searchBean.add("3", "今天的天气挺好的。", "今天的天气挺好的。");
        searchBean.add("4", "你是谁？", "你是谁？");
        searchBean.add("5", "高数这门学科很难", "高数确实很难。");
        searchBean.add("6", "测试", "上面的只是测试");
        List<SortBean> list = searchBean.getIds("你的高数");
        searchBean.printObj(list);
    }

    private  void printObj(List<SortBean> list) {
        for(SortBean sortBean : list){

            System.out.println(sortBean + ":" + objMap.get(sortBean.getId()));
        }
    }

    private List<SortBean> getIds(String str) {
        List<SortBean> list = new ArrayList<SortBean>();
        for(String id : keyMap.keySet()){
            SortBean sortBean = new SortBean();
            Set<String> set = keyMap.get(id);
            sortBean.setId(id);
            for(String s : str.split("")){
                if(set.contains(s)){
                    sortBean.increase();
                }
            }
            list.add(sortBean);
        }

        Collections.sort(list, new Comparator<SortBean>() {
            public int compare(SortBean o1, SortBean o2) {
                if(o1.getTimes() < o2.getTimes()){
                    return 1;
                }else if(o1.getTimes() > o2.getTimes()){
                    return -1;
                }
                return 0;
            }
        });
        return list;
    }
}
