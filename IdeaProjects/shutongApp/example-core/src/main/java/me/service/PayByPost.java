package me.service;

/**
 * Created by chn on 15/12/18.
 */
public class PayByPost extends PayBy {

    String postAddr;

    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr;
    }
    public String getPostAddr() {
        return postAddr;
    }

    public PayByPost() {}

    public PayByPost(long empId, String postAddr) {
        super(empId);
        setPostAddr(postAddr);
    }

    public void pay(double sum) {

    }
}
