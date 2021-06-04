package org.jianzhao.boot.model;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {

    private String data;

    private List<TreeNode> children;
}
