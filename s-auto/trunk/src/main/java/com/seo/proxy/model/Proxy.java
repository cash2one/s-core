package com.seo.proxy.model;

import java.util.Date;

public class Proxy {
    private String host;
    private Integer port;
    private ProxyType proxyType;
    private Date creationDate;

    public Proxy(String host, Integer port, ProxyType proxyType) {
        this.host = host;
        this.port = port;
        this.proxyType = proxyType;
        this.creationDate = new Date();
    }

    public Proxy() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ProxyType getProxyType() {
        return proxyType;
    }

    public void setProxyType(ProxyType proxyType) {
        this.proxyType = proxyType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proxy)) return false;

        Proxy proxy = (Proxy) o;

        if (!host.equals(proxy.host)) return false;
        if (!port.equals(proxy.port)) return false;
        if (proxyType != proxy.proxyType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + port.hashCode();
        result = 31 * result + proxyType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Proxy{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", proxyType=" + proxyType +
                '}';
    }
}
