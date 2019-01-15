package beans;

import repository.DataRowRepository;
import java.util.Comparator;
import java.util.List;

public class CheckData {
    private DataRowRepository repository;

    public List<DataRow> getData() {
        List<DataRow> results = repository.getAllResults();
        results.sort(Comparator.comparing(DataRow::getDate).reversed());
        return results;
    }

    public DataRowRepository getRepository() {
        return repository;
    }

    public void setRepository(DataRowRepository repository) {
        this.repository = repository;
    }
}