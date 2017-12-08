package com.example.demo.backend.domain;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * A sample class for adding error information in the response
 * from https://github.com/khoubyari/spring-boot-rest-example/blob/master/src/main/java/com/khoubyari/example/domain/RestErrorInfo.java
 */
@XmlRootElement
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
