package cn.itcast.solrj.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.ArrayList;

public class Item2 {
    @Field
    private String id;
    @Field
    private ArrayList<String> title;
    @Field
    private float price;

    @Override
    public String toString() {
        return "Item2{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }
}
