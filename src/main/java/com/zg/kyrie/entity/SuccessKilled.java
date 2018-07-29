package com.zg.kyrie.entity;


import java.util.Date;
import java.util.List;

public class SuccessKilled {

  private long seckillId;
  private long userPhone;
  private short state;
  private Date createTime;
  private List<Seckill> seckill;

  public List<Seckill> getSeckill() {
    return seckill;
  }

  public void setSeckill(List<Seckill> seckill) {
    this.seckill = seckill;
  }

  public long getSeckillId() {
    return seckillId;
  }

  public void setSeckillId(long seckillId) {
    this.seckillId = seckillId;
  }


  public long getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(long userPhone) {
    this.userPhone = userPhone;
  }


  public long getState() {
    return state;
  }

  public void setState(short state) {
    this.state = state;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "SuccessKilled{" +
            "seckillId=" + seckillId +
            ", userPhone=" + userPhone +
            ", state=" + state +
            ", createTime=" + createTime +
            ", seckill=" + seckill +
            '}';
  }
}
