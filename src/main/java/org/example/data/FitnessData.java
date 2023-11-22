package org.example.data;

import org.example.model.Exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FitnessData {
    String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/wellness_navigator_gym";
    String username = "root";
    String password = "123456";

    public List<Exercise> FitnessRecord() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM track_data_ai";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            List<Exercise> exerciseList = new ArrayList<>();

            while (resultSet.next()) {
                // Retrieve each field from resultSet
                int track_data_ai_id = resultSet.getInt("track_data_ai_id");
                String activity_level = resultSet.getString("activity_level");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                double bmi = resultSet.getDouble("bmi");
                String suggested_exercises = resultSet.getString("suggested_exercises");
                String training_goals = resultSet.getString("training_goals");
                String training_history = resultSet.getString("training_history");
                int customer_id = resultSet.getInt("customer_id");
                boolean effective = resultSet.getBoolean("effective");

                // Create a new Exercise object with the retrieved fields
                Exercise exerciseData = new Exercise(track_data_ai_id, activity_level, age, gender,bmi, suggested_exercises, training_goals, training_history, customer_id, effective);
                exerciseList.add(exerciseData);
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println(exerciseList);
            return exerciseList; // Thay đổi từ null thành exerciseList
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}