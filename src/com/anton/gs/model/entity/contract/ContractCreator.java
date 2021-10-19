package com.anton.gs.model.entity.contract;

import java.sql.Date;

public class ContractCreator {
    private ContractCreator() {
    }

    public static Contract createContract(
            int contractId,
            String tutorEmail,
            String studentEmail,
            String currentLevel,
            String targetLevel,
            String guitarType,
            String genre,
            Date startDate,
            Date endDate
    ) {
        Contract contract = new Contract(
                contractId,
                tutorEmail,
                studentEmail,
                currentLevel,
                targetLevel,
                guitarType,
                genre,
                startDate,
                endDate);
        return contract;
    }
}
