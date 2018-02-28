package app.qimi.cn.lib_common.retrofit.bean;

/**
 * @author GaoXP
 * @time 2017/7/29.
 */
public class RequestParam {

    public String key;
    public Object value;

    public RequestParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}