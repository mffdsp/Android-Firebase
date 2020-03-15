package com.example.tentativa;

import org.w3c.dom.Text;

public class TextStructure {

    private String title = "voidInfo";
    private String body = "voidInfo";
    private String ID = "voidInfo";
    public String EMAIL = "voidInfo";

    @Override
    public String toString(){
        return this.title;
    }
    public TextStructure(){

    }
    public TextStructure(String title, String body, String ID){
        this.ID = ID;
        this.body = body;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
