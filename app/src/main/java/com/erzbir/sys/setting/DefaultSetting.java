package com.erzbir.sys.setting;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public class DefaultSetting implements Setting {
    private String server = "";

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public void setServer(String server) {
        this.server = server.trim();
    }
}
