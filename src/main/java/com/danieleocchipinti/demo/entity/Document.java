package com.danieleocchipinti.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String filename;
    
    @Column(nullable = false, columnDefinition="longblob")
    private byte[] content;

    @Column(nullable = false)    
    private Date uploadedAt = new Date();

    // ... additional members, often include @OneToMany mappings

    protected Document() { }

    public Document(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }    
    
    public String getFilename() {
        return this.filename;
    }

    public byte[] getContent() {
        return this.content;
    }
    
    public Date getUploadedAt() {
    	return this.uploadedAt;
    }
}