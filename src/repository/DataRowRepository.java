package repository;

import beans.DataRow;

import java.util.List;

public interface DataRowRepository {
    List<DataRow> getAllResults();

    boolean save(DataRow check);
}