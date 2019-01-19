package dive.common.model;

/**
 * 传输字符串模式，反解析使用
 *
 * @author dawn
 * @date 2019/1/20 1:11
 */
public class StringMessage {
    /**
     * 当前时间
     */
    private String created;

    /**
     * 信息码
     */
    private int code;

    /**
     * 描述
     */
    private String message;

    /**
     * 数据
     */
    private String data;

    //---------------------------------------------------------------------


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    //---------------------------------------------------------------------

    @Override
    public String toString() {
        return "StringMessage{" +
                "created='" + this.created + '\'' +
                ", code=" + this.code +
                ", message='" + this.message + '\'' +
                ", data='" + this.data + '\'' +
                '}';
    }

}
