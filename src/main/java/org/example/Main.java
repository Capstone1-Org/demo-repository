package org.example;

import org.example.data.FitnessData;
import org.example.model.Exercise;
import org.example.model.TreeNode;
import org.example.service.BuildDecisionTree;

import java.util.*;

import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Thu thập dữ liệu người dùng
        System.out.println("Nhập mức độ hoạt động của bạn (Beginner/Intermediate/Advanced/Moderate/Occasionally/Regularly):");
        String activityLevel = scanner.nextLine().trim();

        int age = promptForInt(scanner, "Nhập tuổi của bạn:");

        System.out.println("Nhập giới tính của bạn (Male/Female):");
        String gender = scanner.nextLine().trim();

        double bmi = promptForDouble(scanner, "Nhập bmi của bạn:");
        System.out.println("Nhập mục tiêu của bạn:");
        String training_goals=scanner.nextLine().trim();;
        System.out.println("Nhập lịch sử tập luyện:");
        String training_history=scanner.nextLine().trim();;
        // Lấy dữ liệu và xây dựng cây quyết định
        FitnessData fitnessData = new FitnessData();
        List<Exercise> exercises = fitnessData.FitnessRecord();
        BuildDecisionTree builder = new BuildDecisionTree(exercises);

        // Định nghĩa thuộc tính sẽ được sử dụng trong cây quyết định
        List<String> attributeNames = new ArrayList<>(Arrays.asList("activity_level", "age", "gender", "bmi","training_goals","training_history"));

        // Xây dựng cây quyết định
        TreeNode decisionTree = builder.buildDecisionTree(exercises, attributeNames);

        // In ra cấu trúc của cây quyết định
        System.out.println("Cấu trúc của cây quyết định:");
        builder.printTree(decisionTree, "");

        // Duyệt qua cây quyết định để tìm đề xuất
        Main mainInstance = new Main();
        String recommendedExercise = mainInstance.traverseDecisionTree(decisionTree, createUserDataMap(activityLevel, age, gender, bmi,training_goals,training_history));

        // In ra đề xuất bài tập
        System.out.println("Bài tập được đề xuất cho bạn là: " + recommendedExercise);

        scanner.close();
    }

    private static Map<String, Object> createUserDataMap(String activityLevel, int age, String gender, double bmi, String training_goals,String training_history) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("activity_level", activityLevel);
        userData.put("age", age);
        userData.put("gender", gender);
        userData.put("bmi", bmi);
        userData.put("training_goals", training_goals);
        userData.put("training_history", training_history);
        return userData;
    }
    private static int promptForInt(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            scanner.next(); // Đọc và bỏ qua giá trị không hợp lệ
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // Đọc và bỏ qua dấu kết thúc dòng
        return number;
    }

    private static double promptForDouble(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Vui lòng nhập một số thực hợp lệ.");
            scanner.next(); // Đọc và bỏ qua giá trị không hợp lệ
        }
        double number = scanner.nextDouble();
        scanner.nextLine(); // Đọc và bỏ qua dấu kết thúc dòng
        return number;
    }


    private String traverseDecisionTree(TreeNode node, Map<String, Object> userData) {
        if (node.isLeaf()) {
            // Trả về thông tin đề xuất từ nút lá
            return node.getRecommendation();
        } else {
            // Logic để duyệt qua các nút không phải lá
            Object attributeValue = userData.get(node.getAttributeName());
            TreeNode childNode = node.getChildren().get(attributeValue);
            if (childNode != null) {
                return traverseDecisionTree(childNode, userData);
            } else {
                // Xử lý trường hợp không tìm thấy nhánh phù hợp
                // Ví dụ: trả về một đề xuất mặc định hoặc dựa trên phân loại đa số
                return getDefaultRecommendation(); // Phương thức này cần được thêm và xác định
            }
        }
    }

    private String getDefaultRecommendation() {
        // Xác định và trả về một đề xuất mặc định
        // Ví dụ: có thể dựa trên phân loại đa số từ toàn bộ dữ liệu hoặc một đề xuất an toàn
        return "Đề xuất An Toàn"; // Thay thế này bằng logic xác định đề xuất cụ thể
    }


}
