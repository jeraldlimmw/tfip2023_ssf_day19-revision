package sg.edu.nus.iss.day19revision.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class LoveCalculator implements Serializable{
    private String fname;
    private String sname;
    private int percentage;
    private String result;
    
    public LoveCalculator() {
    }

    public LoveCalculator(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
            .add("fname", this.getFname())
            .add("sname", this.getSname())
            .add("percentage", this.getPercentage())
            .add("result", this.getResult());
    }

    public static LoveCalculator jsonToObj(String json) throws IOException {
        LoveCalculator lc = new LoveCalculator();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();

            lc.setFname(o.getString("fname"));
            lc.setSname(o.getString("sname"));
            lc.setPercentage(Integer.parseInt(o.getString("percentage")));
            lc.setResult(o.getString("result"));
        }
        return lc;
    }
    
}
