package com.anton.gs.model.entity.user;


import com.anton.gs.model.entity.Entity;

import java.util.Objects;
import java.util.Optional;

public class User extends Entity {
    private int userId;
    private String email;
    private String nickName;
    private String userRole;

    private String tutorDegree;
    private String studentSkillLevel;
    private Boolean isActive;

    public User(int userId,
            String email,
                String nickName,
                String userRole,
                String tutorDegree,
                String studentSkillLevel,
                Boolean isActive) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
        this.tutorDegree = tutorDegree;
        this.studentSkillLevel = studentSkillLevel;
        this.isActive = isActive;
    }

    public int getUserId() {
        return userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getTutorDegree() {
        return tutorDegree;
    }

    public void setTutorDegree(String tutorDegree) {
        this.tutorDegree = tutorDegree;
    }

    public String getStudentSkillLevel() {
        return studentSkillLevel;
    }

    public void setStudentSkillLevel(String studentSkillLevel) {
        this.studentSkillLevel = studentSkillLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(email, user.email) &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(userRole, user.userRole) &&
                Objects.equals(tutorDegree, user.tutorDegree) &&
                Objects.equals(studentSkillLevel, user.studentSkillLevel) &&
                Objects.equals(isActive, user.isActive);
    }

    @Override
    public int hashCode() {
        return userId * 31;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", email='").append(email).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", userRole='").append(userRole).append('\'');
        sb.append(", tutorDegree='").append(tutorDegree).append('\'');
        sb.append(", studentSkillLevel='").append(studentSkillLevel).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
