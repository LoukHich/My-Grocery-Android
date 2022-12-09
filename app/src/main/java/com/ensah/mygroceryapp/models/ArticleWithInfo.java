package com.ensah.mygroceryapp.models;

public class ArticleWithInfo {

    private ArticleWithCount article;

    private  String info;




    public ArticleWithInfo(ArticleWithCount article,String info) {
        this.article= article;
        this.info = info;
    }

    public ArticleWithCount getArticle(){
        return  article;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
