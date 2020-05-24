package mqtest.thread.statistics;

public class ThreadResult {
    private String threadName;
    private boolean flag;
    private String code;
    private String msg;

    public ThreadResult(boolean flag,String threadName, String code, String msg) {
        this.flag = flag;
        this.threadName = threadName;
        this.code = code;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ThreadResult{" +
                "threadName='" + threadName + '\'' +
                ", flag=" + flag +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
