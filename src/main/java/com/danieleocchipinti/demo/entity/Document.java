package com.danieleocchipinti.demo.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String filename;
    
    @Column(nullable = false, columnDefinition="longblob")
    private byte[] content;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id", nullable = false)
    private Deal deal;    
    
    @Column(nullable = false)    
    private Date uploadedAt = new Date();

    protected Document() { }

    public Document(String filename, byte[] content, Deal deal) {
        this.filename = filename;
        this.content = content;
        this.deal = deal;
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
    
    public Deal getDeal() {
		return this.deal;
	}    
    
    public Date getUploadedAt() {
    	return this.uploadedAt;
    }
}