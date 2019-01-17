package beans;

import lombok.Getter;
import lombok.Setter;
import repository.DataRowRepository;

import java.util.Date;

@Setter
@Getter
public class DataRow{
    private int id;
    private double paramX;
    private double paramY;
    private double paramR;
    private int result;
    private Date date;
    private DataRowRepository repository;

    public DataRow(int id, double paramX, double paramY, double paramR, int result, Date date) {
        this.id = id;
        this.paramX = paramX;
        this.paramY = paramY;
        this.paramR = paramR;
        this.result = result;
        this.date = date;
    }

    public DataRow()
    {
        this.id = 0;
        this.paramX = 0;
        this.paramY = 0;
        this.paramR = 2;
        this.result = 0;
        this.date = null;
    }

    private boolean checkArea(double x, double y, double r){
        x = Math.abs(x);
        return true;
    }

    public String check(){
        result = checkArea(paramX, paramY, paramR) ? 1 : 0;
        date = new Date();
        repository.save(this);
        return "check";
    }
}