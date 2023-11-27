package org.example.model;

public class Course {
    private int course_id;
    private String course_name;
    private String course_type_id;
    private String description;
    private String duration;
    private String image;
    private int customer_id;

    public Course(int course_id, String course_name, String course_type, String description, String duration, String image, int customer_id) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_type_id = course_type;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.customer_id = customer_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                '}';
    }
}
