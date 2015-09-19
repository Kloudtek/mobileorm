package com.kloudtek.mobileorm;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by yannick on 9/17/15.
 */
public interface DAO<X> {
    /**
     * Returns an SQL statement that can be used to create the table used to persist data for the relevant object
     * @return
     */
    String getCreateSQL();

    /**
     * Retrieves data from sql result set and populated the relevant data object
     * @param resultSet SQL result set
     * @return Data object
     */
    X toObj(ResultSet resultSet);

    /**
     * Creates an SQL statement that INSERTs all the data present in data object
     * @param obj Data object
     * @return SQL Statement
     */
    Statement toInsert(X obj);

    /**
     * Creates an SQL statement that UPDATEs all the data present in data object
     * @param obj Data object
     * @return SQL Statement
     */
    Statement toUpdate(X obj);
}
