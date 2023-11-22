    package org.example.service;

    import org.example.model.Exercise;
    import org.example.model.TreeNode;

    import java.util.*;

    public class BuildDecisionTree {

        private static DecisionTree decisionTree;

        public BuildDecisionTree(List<Exercise> exercises) {
            this.decisionTree = new DecisionTree(exercises);
        }

        public TreeNode buildDecisionTree(List<Exercise> data, List<String> attributeNames) {
            if (attributeNames.isEmpty() || data.isEmpty()) {
                // Xác định một đề xuất cụ thể hoặc sử dụng giá trị mặc định
                return createLeafNode(data); // Tạo nút lá dựa trên dữ liệu còn lại
            }

            Map<String, Double> gainRatios = new HashMap<>();
            for (String attributeName : attributeNames) {
                gainRatios.put(attributeName, decisionTree.calculateGainRatio(attributeName));
            }

            List<Map.Entry<String, Double>> sortedAttributes = new ArrayList<>(gainRatios.entrySet());
            sortedAttributes.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            TreeNode node = null;
            for (Map.Entry<String, Double> attribute : sortedAttributes) {
                String bestAttribute = attribute.getKey();
                double bestGainRatio = attribute.getValue();

                node = new TreeNode(bestAttribute);
                node.setGainRatio(bestGainRatio);

                Set<Object> attributeValues = decisionTree.getDistinctAttributeValues(bestAttribute);
                for (Object value : attributeValues) {
                    List<Exercise> subset = decisionTree.filterByAttributeValue(data, bestAttribute, value);
                    if (subset.isEmpty()) {
                        // Xác định một đề xuất cụ thể dựa trên dữ liệu còn lại
                        TreeNode leafNode = createLeafNode(data); // Sử dụng dữ liệu còn lại để tạo nút lá
                        node.getChildren().put(value, leafNode);
                    } else {
                        // Xử lý dữ liệu con và tạo nhánh mới
                        List<String> remainingAttributes = new ArrayList<>(attributeNames);
                        remainingAttributes.remove(bestAttribute);
                        node.getChildren().put(value, buildDecisionTree(subset, remainingAttributes));
                    }
                }
            }
            return node;
        }




        private TreeNode createLeafNode(List<Exercise> data) {
            TreeNode leafNode = new TreeNode(null);
            leafNode.setLeaf(true);

            // Xác định phân loại đa số
            boolean classification = determineMajorityClassification(data);
            leafNode.setClassification(classification);

            // Tạo đề xuất dựa trên dữ liệu
            String recommendation = createRecommendation(data);
            leafNode.setRecommendation(recommendation);

            return leafNode;
        }

        private String createRecommendation(List<Exercise> data) {
            // Xác định đề xuất dựa trên dữ liệu của các bài tập
            // Ví dụ: đề xuất loại bài tập phổ biến nhất trong dữ liệu
            Map<String, Integer> exerciseCount = new HashMap<>();
            for (Exercise exercise : data) {
                String exerciseType = exercise.getSuggested_exercises(); // Giả sử có phương thức này
                exerciseCount.put(exerciseType, exerciseCount.getOrDefault(exerciseType, 0) + 1);
            }

            return Collections.max(exerciseCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        }


        private  boolean determineMajorityClassification(List<Exercise> data) {
            if (data.isEmpty()) {
                return false; // Or handle this case as per your requirement
            }

            int countTrue = 0;
            for (Exercise exercise : data) {
                if (exercise.isEffective()) {
                    countTrue++;
                }
            }
            return countTrue >= data.size() / 2;
        }

        private boolean areAllExamplesEffective(List<Exercise> data) {
            if (data.isEmpty()) return false;

            boolean firstClassification = data.get(0).isEffective();
            for (Exercise exercise : data) {
                if (exercise.isEffective() != firstClassification) {
                    return false;
                }
            }
            return true;
        }

        private String selectBestAttribute(List<Exercise> data, List<String> attributeNames) {
            double maxGainRatio = Double.MIN_VALUE;
            String bestAttribute = null;

            for (String attributeName : attributeNames) {
                double gainRatio = decisionTree.calculateGainRatio(attributeName);

                if (gainRatio > maxGainRatio) {
                    maxGainRatio = gainRatio;
                    bestAttribute = attributeName;
                }
            }
            return bestAttribute;
        }
        public void printTree(TreeNode node, String indent) {
            if (node == null) {
                return;
            }

            // In thông tin của nút hiện tại
            System.out.println(indent + "Node: " + (node.getAttributeName() == null ? "Leaf" : node.getAttributeName()));
            if (node.isLeaf()) {
                System.out.println(indent + "  Recommendation: " + node.getRecommendation());
            }

            // Duyệt qua các nút con
            if (node.getChildren() != null) {
                for (Map.Entry<Object, TreeNode> entry : node.getChildren().entrySet()) {
                    System.out.println(indent + "  Value: " + entry.getKey());
                    printTree(entry.getValue(), indent + "    ");
                }
            }
        }

    }
