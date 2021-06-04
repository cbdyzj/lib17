package org.jianzhao.boot.model;

import lombok.Data;

import java.util.Map;

@Data
public class RenderRequest {

    private String template;
    private Map<String, Object> scope;
}
