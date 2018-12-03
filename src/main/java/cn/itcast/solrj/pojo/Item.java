package cn.itcast.solrj.pojo;

import org.apache.solr.client.solrj.beans.Field;

public class Item {
    @Field
    private long id;
    @Field
    private String title;
    @Field
    private long price;

    public Item() {
    }

    public Item(Long id, String title, long price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
