package com.zg.kyrie.dto;

/**
 * @Auther: kyrie
 * @Date: 2018/7/23 21:54
 */
public class Exposer {
    private boolean isExposed;
    private long seckillId;
    private String md5;

    public Exposer(boolean isExposed, long seckillId) {
        this.isExposed = isExposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean isExposed, long seckillId, String md5) {
        this.isExposed = isExposed;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "isExposed=" + isExposed +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
