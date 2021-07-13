package com.laotan.net.common;

/**
 * @author fengshuonan
 * @Description： 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum ResultStatusCode {

    SUCCESS(10000, "操作成功"),
    NOT_NULL(10001, "参数异常"),
    EXIST(10002, "数据已存在"),
    SOURCE_NOT_AUTH(10003, "来源未授权"),
    EXCEPTION(10004, "程序处理异常"),
    JSON_ERROR(10005, "json数据解析失败"),
    METHOD_NOT_ALLOWED(10006, "不支持的请求方式"),
    ORTHER_ERROR(10007, "其他未知系统错误"),
    TIME_OUT(10008, "处理超时，请重试"),
    INVALID_CAPTCHA(10009, "无效的验证码"),
    FAIL_OPERATION(10010, "操作失败"),
    ERROR_CODE_EMPTY(10011, "字典类型不能为空"),
    DB_RESOURCE_NULL(10012, "数据库中没有该资源"),
    USER_ALREADY_REG(10013, "该用户已经注册"),
    NO_THIS_USER(10014, "没有此用户"),
    ACCOUNT_NOT_EXISTED(10015, "用户名不存在"),
    PASSWORD_ERROR(10016, "密码错误"),
    LICENSE_OUT(10017,"您的license可用总数量超标，请联系管理员！"),
    LICENSE_DEVICE_OUT(10018,"您的证书可用设备超限，请联系管理员！"),
    LICENSE_CONFIG_NULL(10019,"此次查询配置信息为空"),
    AUTHENTICATION_FAIL(10020,"认证失败"),
    ADMIN_NOT_DEL(10021,"admin用户不能被删除"),
    ACCOUNT_NOT_FIND(10022,"账号不存在"),
    SYSTEM_ERR(10023,"系统错误"),
    ACCOUNT_USER_NOT_EXIST(10024,"账号关联用户不存在"),
    ACCOUNT_EXIST(10025,"账号已存在"),
    FILE_SIZE_ERROR(10026,"文件大小超过10MB"),
    ADD_ERROR(10027,"数据添加失败"),
    UPDATE_ERROR(10028,"数据更新失败"),
    DELETE_ERROR(10029,"数据删除失败"),
    DATA_ERROR(10030,"参数错误"),
    APPROVAL_PROCESS_ERROR(10031,"未设置审批流程"),
    TASK_END_ERROR(10032,"该任务已完成"),
    SYS_DATA_ERROR(10033,"未设置系统参数"),
    FILE_NUMBER_ERROR(10034,"文件数量错误"),
    FILE_IS_NULL(10035,"文件数量错误"),
    AGAIN_LOGIN(10036,"请重新登录"),
    APPROVE_NODE_NOT_EXIST(10037,"审批节点不存在或与当前审批节点不匹配"),
    APPROVE_TIME_OUT(10038,"审批时间已超时，无法审批，该单据将作废"),
    END_TIME_OUT(10039,"结束时间已超时，无法进行该操作"),
    ACCOUNT_STATUS_ERROR(10040,"此账号已被禁用，请联系管理员"),

    NO_PERMISSIONS(20000,"没有权限"),

    ZHONGJI(999999,"限制最后节点");



    ResultStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
