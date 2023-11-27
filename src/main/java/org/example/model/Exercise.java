package org.example.model;

import java.util.ArrayList;

public class Exercise {
    private ArrayList<String> mValues;
    private int track_data_ai_id;
    private String activity_level;
    private int age;
    private String gender;
    private double bmi;
    private Course course;
    private String training_goals;
    private String training_history;

    private boolean effective;


    public Exercise(ArrayList<String> mValues) {
        this.mValues = mValues;
    }

    public Exercise() {
    }

    public Exercise(int track_data_ai_id, String activity_level, int age, String gender, double bmi, Course course, String training_goals, String training_history, boolean effective) {
        this.track_data_ai_id = track_data_ai_id;
        this.activity_level = activity_level;
        this.age = age;
        this.gender = gender;
        this.bmi = bmi;
        this.course = course; // Changed
        this.training_goals = training_goals;
        this.training_history = training_history;

        this.effective = effective;
    }

    public String[] getValues() {
        if (mValues != null)
            return mValues.toArray(new String[0]);
        else
            return null;
    }
    public boolean isValidValue(String value) {
        return indexValue(value) >= 0;
    }

    public int indexValue(String value) {
        if (mValues != null)
            return mValues.indexOf(value);
        else
            return -1;
    }

    public int getTrack_data_ai_id() {
        return track_data_ai_id;
    }

    public void setTrack_data_ai_id(int track_data_ai_id) {
        this.track_data_ai_id = track_data_ai_id;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTraining_goals() {
        return training_goals;
    }

    public void setTraining_goals(String training_goals) {
        this.training_goals = training_goals;
    }

    public String getTraining_history() {
        return training_history;
    }

    public void setTraining_history(String training_history) {
        this.training_history = training_history;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    @Override
    public String toString() {
        return "exercise{" +
                "track_data_ai_id=" + track_data_ai_id +
                ", activity_level='" + activity_level + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", bmi=" + bmi+
                ", suggested_exercises='" + course + '\'' +
                ", training_goals='" + training_goals + '\'' +
                ", training_history='" + training_history + '\'' +

                ", effective=" + effective +
                '}';
    }
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "track_data_ai_id":
                return getTrack_data_ai_id();
            case "activity_level":
                return getActivity_level();
            case "age":
                return getAge();
            case "gender":
                return getGender();
            case "bmi":
                return getBmi();
            case "suggested_exercises":
                return getCourse();
            case "training_goals":
                return getTraining_goals();
            case "training_history":
                return getTraining_history();
            case "effective":
                return isEffective();
            default:
                System.out.println("Thuộc tính không hợp lệ: " + attributeName);
                return null; // Hoặc bạn có thể ném một ngoại lệ
        }
    }
}
