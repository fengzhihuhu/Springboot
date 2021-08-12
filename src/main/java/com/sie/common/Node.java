package com.sie.common;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName Node
 * @Description TODO 树形菜单
 * @Author 徐啸儒
 * @Data 2021/7/29 15:47
 * @Version 1.0
 **/
public class Node {
    //menuID
    private BigDecimal value;
    //menuName
    private String label;

    private List<Node> children;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
