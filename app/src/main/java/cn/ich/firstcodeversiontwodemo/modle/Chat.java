package cn.ich.firstcodeversiontwodemo.modle;

public class Chat {
    public static final int SEND = 1;
    public static final int RECEIVE = 2;
    private int type;
    private String msg;

    public Chat(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
