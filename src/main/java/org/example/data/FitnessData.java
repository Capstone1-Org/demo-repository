package org.example.data;

import org.example.model.Course;
import org.example.model.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessData {
    String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/wellness_navigator_gym";
    String username = "root";
    String password = "123456@Abc";

    public List<Exercise> FitnessRecord() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
//track_data_ai
            String sqlQuery = "SELECT * FROM track_data_ai";
            ResultSet resultSetExercise = statement.executeQuery(sqlQuery);
            List<Exercise> exerciseList = new ArrayList<>();
            while (resultSetExercise.next()) {
                // Retrieve each field from resultSet
                int track_data_ai_id = resultSetExercise.getInt("track_data_ai_id");
                String activity_level = resultSetExercise.getString("activity_level");
                int age = resultSetExercise.getInt("age");
                String gender = resultSetExercise.getString("gender");
                double bmi = resultSetExercise.getDouble("bmi");
                int course_id = resultSetExercise.getInt("course_id");
                Course course = getCourseById(course_id);
                String training_goals = resultSetExercise.getString("training_goals");
                String training_history = resultSetExercise.getString("training_history");

                boolean effective = resultSetExercise.getBoolean("effective");
                // Create a new Exercise object with the retrieved fields
                Exercise exerciseData = new Exercise(track_data_ai_id, activity_level, age, gender,bmi, course, training_goals, training_history, effective);
                exerciseList.add(exerciseData);
            }
            //Course
            resultSetExercise.close();
            statement.close();
            connection.close();
            return exerciseList; // Thay đổi từ null thành exerciseList
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Course getCourseById(int courseId) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        String sqlQuery = "SELECT * FROM Course WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, courseId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Course course = null;
        if (resultSet.next()) {
            // Tạo đối tượng Course với tất cả thuộc tính từ bảng Course
            int course_id = resultSet.getInt("course_id");
            String course_name = resultSet.getString("course_name");
            String course_type = resultSet.getString("course_type_id");
            String description = resultSet.getString("description");
            String duration = resultSet.getString("duration");
            String image = resultSet.getString("image");
            int customer_id = resultSet.getInt("customer_id");

            course = new Course(course_id, course_name, course_type, description, duration, image, customer_id);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return course;
    }

}
