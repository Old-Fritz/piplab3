package beans;

import lombok.Getter;
import lombok.Setter;
import repository.DataRowRepository;
import java.util.Comparator;
import java.util.List;

@Setter
@Getter
public class CheckData {
    private DataRowRepository repository;

    public List<DataRow> getData() {
        List<DataRow> results = repository.getAllResults();
        results.sort(Comparator.comparing(DataRow::getDate).reversed());
        return results;
    }
}