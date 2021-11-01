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

        if (contractId != contract.contractId) return false;
        if (tutorEmail != null ? !tutorEmail.equals(contract.tutorEmail) : contract.tutorEmail != null) return false;
        if (studentEmail != null ? !studentEmail.equals(contract.studentEmail) : contract.studentEmail != null)
            return false;
        if (currentLevel != null ? !currentLevel.equals(contract.currentLevel) : contract.currentLevel != null)
            return false;
        if (targetLevel != null ? !targetLevel.equals(contract.targetLevel) : contract.targetLevel != null)
            return false;
        if (guitarType != null ? !guitarType.equals(contract.guitarType) : contract.guitarType != null) return false;
        if (genre != null ? !genre.equals(contract.genre) : contract.genre != null) return false;
        if (startDate != null ? !startDate.equals(contract.startDate) : contract.startDate != null) return false;
        return endDate != null ? endDate.equals(contract.endDate) : contract.endDate == null;
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
