package com.zg.kyrie.enums;

/**
 * @Auther: kyrie
 * @Date: 2018/7/28 21:43
 */
public enum  SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀活动结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统错误"),
    DATA_REWRITE(-3, "数据错误");

    SeckillStatEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    private int state;
    private String info;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String stateInfo(int state){
        for(SeckillStatEnum seckillStatEnum : values()){
            if (seckillStatEnum.getState() == state){
                return seckillStatEnum.info;
            }
        }
        return null;
    }
}
