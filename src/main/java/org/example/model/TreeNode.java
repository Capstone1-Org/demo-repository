package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    private String attributeName; // Thuộc tính tại nút hiện tại
    private Map<Object, TreeNode> children; // Bản đồ các nút con
    private boolean isLeaf; // Kiểm tra xem nút có phải là lá không
    private boolean classification; // Kết quả phân loại (true hoặc false) nếu nút là lá
    private double gainRatio; // Thêm thuộc tính cho Gain Ratio
    private String recommendation;

    public TreeNode(String attributeName) {
        this.attributeName = attributeName;
        this.children = new HashMap<>();
        this.isLeaf = false;
        this.classification = false;
        this.gainRatio = 0.0; // Khởi tạo Gain Ratio
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Map<Object, TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Object, TreeNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isClassification() {
        return classification;
    }

    public void setClassification(boolean classification) {
        this.classification = classification;
    }

    public double getGainRatio() {
        return gainRatio;
    }

    public void setGainRatio(double gainRatio) {
        this.gainRatio = gainRatio;
    }
    public String getRecommendation() {
        return recommendation;
    }

    // Phương thức setter cho recommendation
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
