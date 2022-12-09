package com.ensah.mygroceryapp.models;

public class ArticleWithCount {
    public static final String COURSE_NAME = "couseName";
    private Integer id;
    private String name;
    private  String unite;
    private Integer CategorieId;
    private  Integer count;



    public ArticleWithCount(int id, String name, String unite,Integer count) {
        this.id = id;
        this.name = name;
        this.unite=unite;
        this.count=count;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Integer getCount(){
        return this.count;
    }
    public void setCount(Integer count){
        this.count=count;
    }
}
