package com.anton.gs.model.entity.contract;

import com.anton.gs.model.entity.Entity;

import java.sql.Date;
import java.util.Objects;

public class Contract extends Entity {
    private int contractId;
    private String tutorEmail;
    private String studentEmail;
    private String currentLevel;
    private String targetLevel;
    private String guitarType;
    private String genre;
    private Date startDate;
    private Date endDate;

    public Contract(int contractId,
                    String tutorEmail,
                    String studentEmail,
                    String currentLevel,
                    String targetLevel,
                    String guitarType,
                    String genre,
                    Date startDate,
                    Date endDate) {
        this.contractId = contractId;
        this.tutorEmail = tutorEmail;
        this.studentEmail = studentEmail;
        this.currentLevel = currentLevel;
        this.targetLevel = targetLevel;
        this.guitarType = guitarType;
        this.genre = genre;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(String targetLevel) {
        this.targetLevel = targetLevel;
    }

    public String getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(String guitarType) {
        this.guitarType = guitarType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contractId == contract.contractId &&
                Objects.equals(tutorEmail, contract.tutorEmail) &&
                Objects.equals(studentEmail, contract.studentEmail) &&
                Objects.equals(currentLevel, contract.currentLevel) &&
                Objects.equals(targetLevel, contract.targetLevel) &&
                Objects.equals(guitarType, contract.guitarType) &&
                Objects.equals(genre, contract.genre) &&
                Objects.equals(startDate, contract.startDate) &&
                Objects.equals(endDate, contract.endDate);
    }

    @Override
    public int hashCode() {
        return contractId * 31;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contract{");
        sb.append("contractId=").append(contractId);
        sb.append(", tutorEmail='").append(tutorEmail).append('\'');
        sb.append(", studentEmail='").append(studentEmail).append('\'');
        sb.append(", currentLevel='").append(currentLevel).append('\'');
        sb.append(", targetLevel='").append(targetLevel).append('\'');
        sb.append(", guitarType='").append(guitarType).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
