package mona.joyhentai.model;

import java.io.Serializable;

/**
 * @Author manonmona
 * @@Date 2021/10/23 16:09
 */
public class JoyResult implements Serializable {

    /*"code": 0,
  "msg": "",
  "count": 19,
  "data": */

    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
