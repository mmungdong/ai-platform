package com.platform.domain;

public class InvitationCode {

    private Integer id;
    private String code;

    //邀请码状态
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InvitationCode(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public InvitationCode(Integer id, String code, String status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }

    @Override
    public String toString() {
        return "InvitationCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
