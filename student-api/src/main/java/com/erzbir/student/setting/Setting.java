package com.erzbir.student.setting;

import java.io.Serializable;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public interface Setting extends Serializable {
    String getServer();
    void setServer(String server);
}
