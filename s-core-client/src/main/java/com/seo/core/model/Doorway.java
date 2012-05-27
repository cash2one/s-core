package com.seo.core.model;

import com.seo.core.model.account.FTPAccount;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "doorway")
public class Doorway implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "doorway_state")
    @Basic
    private DoorwayState doorwayState;

    private String path;
    private String url;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ftp_account_id")
    private FTPAccount ftpAccount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "redirect_script_id")
    private RedirectScript redirectScript;

    @Column(name = "yandex_index", columnDefinition = "int default 0")
    private Integer yandexIndex;


    public Doorway() {
    }

    public Doorway(DoorwayState doorwayState, String path, String url, FTPAccount ftpAccount, RedirectScript redirectScript) {
        this.doorwayState = doorwayState;
        this.path = path;
        this.url = url;
        this.ftpAccount = ftpAccount;
        this.redirectScript = redirectScript;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoorwayState getDoorwayState() {
        return doorwayState;
    }

    public void setDoorwayState(DoorwayState doorwayState) {
        this.doorwayState = doorwayState;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FTPAccount getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(FTPAccount ftpAccount) {
        this.ftpAccount = ftpAccount;
    }

    public RedirectScript getRedirectScript() {
        return redirectScript;
    }

    public void setRedirectScript(RedirectScript redirectScript) {
        this.redirectScript = redirectScript;
    }

    public Integer getYandexIndex() {
        return yandexIndex;
    }

    public void setYandexIndex(Integer yandexIndex) {
        this.yandexIndex = yandexIndex;
    }
}
