package beans;

import repository.DataRowRepository;

import java.util.Date;

public class DataRow{
    private double paramX;
    private double paramY;
    private double paramR;
    private int result;
    private Date date;
    private DataRowRepository repository;

    public DataRow(double paramX, double paramY, double paramR, int result, Date date) {
        this.paramX = paramX;
        this.paramY = paramY;
        this.paramR = paramR;
        this.result = result;
        this.date = date;
    }

    public DataRow()
    {
        this.paramX = 0;
        this.paramY = 0;
        this.paramR = 1;
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

    public double getParamX() {
        return paramX;
    }

    public void setParamX(double paramX) {
        this.paramX = paramX;
    }

    public double getParamY() {
        return paramY;
    }

    public void setParamY(double paramY) {
        this.paramY = paramY;
    }

    public double getParamR() {
        return paramR;
    }

    public void setParamR(double paramR) {
        this.paramR = paramR;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DataRowRepository getRepository() {
        return repository;
    }

    public void setRepository(DataRowRepository repository) {
        this.repository = repository;
    }
}