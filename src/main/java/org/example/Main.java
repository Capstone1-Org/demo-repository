package org.example;

import org.example.data.FitnessData;
import org.example.model.Course;
import org.example.model.Exercise;
import org.example.model.TreeNode;
import org.example.service.BuildDecisionTree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Thu thập dữ liệu từ người dùng
        System.out.println("Nhập mức độ hoạt động của bạn (Beginner/Intermediate/Advanced/Moderate/Occasionally/Regularly):");
        String activityLevel = scanner.nextLine().trim();

        System.out.println("Nhập tuổi của bạn:");
        int age = promptForInt(scanner);

        System.out.println("Nhập giới tính của bạn (Male/Female):");
        String gender = scanner.nextLine().trim();

        System.out.println("Nhập BMI của bạn:");
        double bmi = promptForDouble(scanner);

        System.out.println("Nhập mục tiêu tập luyện của bạn:");
        String trainingGoals = scanner.nextLine().trim();

        System.out.println("Nhập lịch sử tập luyện của bạn:");
        String trainingHistory = scanner.nextLine().trim();

        // Lấy dữ liệu và xây dựng cây quyết định
        FitnessData fitnessData = new FitnessData();
        List<Exercise> exercises = fitnessData.FitnessRecord();
        BuildDecisionTree builder = new BuildDecisionTree(exercises);

        List<String> attributeNames = Arrays.asList("activity_level", "age", "gender", "bmi", "training_goals", "training_history");

        TreeNode decisionTree = builder.buildDecisionTree(exercises, attributeNames);

        // Tạo map dữ liệu người dùng
        Map<String, Object> userData = new HashMap<>();
        userData.put("activity_level", activityLevel);
        userData.put("age", age);
        userData.put("gender", gender);
        userData.put("bmi", bmi);
        userData.put("training_goals", trainingGoals);
        userData.put("training_history", trainingHistory);

        // Duyệt qua cây quyết định và tìm đề xuất
        List<Course> recommendedCourses = traverseDecisionTree(decisionTree, userData);

        // In ra đề xuất
        System.out.println("Các khóa học được đề xuất cho bạn là: ");
        for (Course course : recommendedCourses) {
            System.out.println(course);
        }

        scanner.close();
    }

    private static int promptForInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng kết thúc
        return number;
    }

    private static double promptForDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Vui lòng nhập một số thực hợp lệ.");
            scanner.next();
        }
        double number = scanner.nextDouble();
        scanner.nextLine(); // Đọc dòng kết thúc
        return number;
    }

    private static List<Course> traverseDecisionTree(TreeNode node, Map<String, Object> userData) {
        if (node.isLeaf()) {
            return node.getRecommendation();
        } else {
            Object attributeValue = userData.get(node.getAttributeName());
            if (attributeValue != null) {
                TreeNode childNode = node.getChildren().get(attributeValue);
                if (childNode != null) {
                    return traverseDecisionTree(childNode, userData);
                }
            }
            return Collections.emptyList();
        }
    }
}
