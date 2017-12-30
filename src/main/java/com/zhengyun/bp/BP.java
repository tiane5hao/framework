package com.zhengyun.bp;

/**
 * Created by 听风 on 2017/12/18.
 */
public class BP {

    public double layer[][];//store the output of every unit
    public double layerErr[][];//store the error of every unit
    public double layerWeight[][][];//store weights
    public double layerWeightDelta[][][];//store number of weights adjust
    public double mobp;// momentum rate
    public double rate;// adjust steps length

    public BP(int[] layernum,double rate,double mobp){
        this.mobp = mobp;
        this.rate = rate;
        layer=new double[layernum.length][];
        layerErr=new double[layernum.length][];
        layerWeight=new double[layernum.length][][];
        layerWeightDelta=new double[layernum.length][][];

        for(int l = 0; l < layernum.length; l++){
            layer[l] = new double[layernum[l]];
            
        }
    }
}
