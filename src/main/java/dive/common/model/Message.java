package dive.common.model;

import dive.common.util.DateUtil;

import java.util.Objects;

/**
 * 信息模板
 *
 * @author dawn
 * @param <M> 传输的对象
 */
public class Message<M> {

    /**
     * 成功
     */
    public static final int CODE_SUCCESS = 0;

    /**
     * 错误
     */
    public static final int CODE_FAILED = 1;

    /**
     * 当前时间
     */
    private final String created;
    {
        created = DateUtil.formatISO8601(System.currentTimeMillis());
    }

    /**
     * 信息码
     */
    private int code = CODE_FAILED;

    /**
     * 描述
     */
    private String message;

    /**
     * 数据
     */
    private M data;

    /**
     * 构造器
     */
    public Message() {
    }

    /**
     * 构造器
     *
     * @param code 信息码
     * @param message 描述
     */
    public Message(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    /**
     * 构造器
     *
     * @param code 信息码
     * @param message 描述
     * @param data 数据
     */
    public Message(int code, String message, M data) {
        this(code, message);
        this.data = data;
    }

    /**
     * 构造器，默认成功
     *
     * @param data 数据
     */
    public Message(M data) {
        this(CODE_SUCCESS, "success");
        this.data = data;
    }

    /**
     * 获取成功实例
     *
     * @param <M> 数据类型
     * @return 成功实例
     */
    public static <M> Message<M> success() {
        return new Message<>(CODE_SUCCESS, "success");
    }

    /**
     * 获取成功实例
     *
     * @param message 描述
     * @param <M> 数据类型
     * @return 成功实例
     */
    public static <M> Message<M> success(String message) {
        return new Message<>(CODE_SUCCESS, message);
    }

    /**
     * 获取失败实例
     *
     * @param <M> 数据类型
     * @return 失败实例
     */
    public static <M> Message<M> fail() {
        return new Message<>(CODE_FAILED, "failed");
    }

    /**
     * 获取失败实例
     *
     * @param message 描述
     * @param <M> 数据类型
     * @return 失败实例
     */
    public static <M> Message<M> fail(String message) {
        return new Message<>(CODE_FAILED, message);
    }

    //----------------------------------------------------------------------------


    public String getCreated() {
        return created;
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

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }

    /**
     * 设置code
     *
     * @param code 信息码
     * @return 本实例
     */
    public Message<M> code(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置描述
     *
     * @param message 描述
     * @return 本实例
     */
    public Message<M> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置数据
     *
     * @param data 数据
     * @return 本实例
     */
    public Message<M> data(M data) {
        this.data = data;
        return this;
    }

    //----------------------------------------------------------------------------

    /**
     * 是否成功
     * @return 是否成功
     */
    public boolean fine() {
        return CODE_SUCCESS == this.code;
    }

    //----------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Message{" +
                "created='" + this.created + '\'' +
                ", code=" + this.code +
                ", message='" + this.message + '\'' +
                ", data=" + this.data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message<?> other = (Message<?>) o;
        return this.code == other.code &&
                Objects.equals(this.created, other.created) &&
                Objects.equals(this.message, other.message) &&
                Objects.equals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.created, this.code, this.message, this.data);
    }

}
