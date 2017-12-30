package com.zhengyun.astar;

import java.util.*;


/**
 * Created by swipal on 2017/12/20.
 */
public class AStar{

    private Map<String, MyPoint> openList = new HashMap<String, MyPoint>();
    private Map<String, MyPoint> closeList = new HashMap<String, MyPoint>();



    private MyPoint startPoint;

    private MyPoint endPoint;

    private Set<String> bar;

    private String[][] map;

    private MyPoint currentPoint;

    public AStar(MyPoint startPoint, MyPoint endPoint,Set<String> bar, String[][] map){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        currentPoint = startPoint;
        this.bar = bar;
        this.map = map;
    }

    public MyPoint run(){
        if(currentPoint.equals(endPoint)){
            return currentPoint;
        }
        System.out.print(currentPoint.getKey()+ " ");
        addCloseList();
        addOpenList();

        return run();
    }

    private void addCloseList() {
        closeList.put(currentPoint.getKey(), currentPoint);
    }

    private void addOpenList() {
        int x = currentPoint.getX();
        int y = currentPoint.getY();

        List<MyPoint> list = new ArrayList<MyPoint>();

        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){

                MyPoint point = new MyPoint(i, j, endPoint);
                if(!bar.contains(point.getKey())
                        && !closeList.containsKey(point.getKey())
                        && i >= 0 && i <map.length
                        && j >=0 && j < map[0].length){

                    double addValue = (i!=x && j!=y ? 1.4 : 1);
                    if(openList.containsKey(point.getKey())){
                        MyPoint oldPoint = openList.get(point.getKey());
                        oldPoint.setParent(currentPoint);
                        oldPoint.setG(currentPoint.getG() + addValue);
                        list.add(oldPoint);
                    }else {
                        double g = currentPoint.getG() + addValue;
                        point.setG(g);
                        point.setParent(currentPoint);
                        openList.put(point.getKey(), point);
                        list.add(point);
                    }
                }
            }
        }

        Collections.sort(list, new Comparator<MyPoint>() {
            public int compare(MyPoint o1, MyPoint o2) {
                if(o1.getCost() > o2.getCost()){
                    return 1;
                }else if(o1.getCost() < o2.getCost()){
                    return -1;
                }
                return 0;
            }
        });

        currentPoint = list.get(0);

        //修改行走路线
        updatePath();

    }

    private void updatePath() {
        int x = currentPoint.getX();
        int y = currentPoint.getY();

        List<MyPoint> list = new ArrayList<MyPoint>();
        list.add(currentPoint);
        for(int i = x-1; i <= x+1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                String key = i+"_"+j;
                if((openList.containsKey(key) || closeList.containsKey(key))

                        && !key.equals(x + "_" +y)){

                    MyPoint openPoint = openList.get(key) == null ? closeList.get(key) : openList.get(key);

                    double addValue = (i!=x && j!=y ? 1.4 : 1);
                    if(openPoint.getG() + addValue < currentPoint.getG()){
                        MyPoint point = new MyPoint(x, y, endPoint);
                        point.setParent(openPoint);
                        point.setG(openPoint.getG() + addValue);
                        list.add(point);
                        closeList.put(key, openPoint);
                        openList.remove(key);
                    }

                }
            }
        }

        Collections.sort(list, new Comparator<MyPoint>() {
            public int compare(MyPoint o1, MyPoint o2) {
                if(o1.getG() > o2.getG()){
                    return 1;
                }else if(o1.getG() < o2.getG()){
                    return -1;
                }
                return 0;
            }
        });
        currentPoint = list.get(0);
    }

    public static void main(String[] args) {
        String[][] map ={
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"},
                {"*","*","*","*","*","*","*","*","*","*","*","*"}
        };

        Set<String> bar = new HashSet<String>();
        for(int i = 3; i < map.length-1; i++){
            bar.add(i + "_" + 6);
            map[i][6] = "#";
        }

        MyPoint startPoint = new MyPoint(9, 3);
        MyPoint endPoint = new MyPoint(10, 9);
        AStar aStar = new AStar(startPoint, endPoint, bar, map);
        endPoint = aStar.run();
        setPath(endPoint, map);

        for(int i=0; i < map.length; i++){
            for (int j=0; j < map[i].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void setPath(MyPoint endPoint, String[][] map) {
        if(endPoint == null){
            return;
        }

        map[endPoint.getX()][endPoint.getY()] = "@";

        setPath(endPoint.getParent(), map);
    }

}
