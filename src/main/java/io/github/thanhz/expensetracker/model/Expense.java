package io.github.thanhz.expensetracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String date;
    private String name;
    private String type;
    private double cost;

    public Expense() {
    } //No argument Constructor required for hibernate

    public Expense(String name, String type, double cost) {
        LocalDate now = LocalDate.now();
        date = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.UK));
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String regex = "(0?[1-9]|[12][0-9]|3[01])[- /.](0?[1-9]|1[012])[- /.](19|20)\\d\\d";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date);
        boolean matchFound = matcher.find();
        if(matchFound) {
            this.date = date;
        } else {
            LocalDate now = LocalDate.now();
            this.date = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.UK));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setExpense(Expense original, Expense update) {
        original.setName(update.getName());
        original.setType(update.getType());
        original.setCost(update.getCost());
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                '}';
    }
}
