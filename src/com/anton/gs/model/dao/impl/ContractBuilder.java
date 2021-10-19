package com.anton.gs.model.dao.impl;

import com.anton.gs.model.dao.table.ContractTable;
import com.anton.gs.model.entity.contract.Contract;
import com.anton.gs.model.entity.contract.ContractCreator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

final class ContractBuilder {
    private ContractBuilder(){}

    static Contract buildContract(ResultSet set) throws SQLException {
        Contract contract;
        int id = set.getInt(ContractTable.CONTRACT_ID);
        String tutor = set.getString(ContractTable.TUTOR);
        String student = set.getString(ContractTable.STUDENT);
        String current = set.getString(ContractTable.CURRENT);
        String target = set.getString(ContractTable.TARGET);
        String guitar = set.getString(ContractTable.GUITAR);
        String genre = set.getString(ContractTable.GENRE);
        Date start = set.getDate(ContractTable.START);
        Date finish = set.getDate(ContractTable.FINISH);
        contract = ContractCreator.createContract(id, tutor, student, current, target, guitar, genre, start, finish);
        return contract;
    }
}
