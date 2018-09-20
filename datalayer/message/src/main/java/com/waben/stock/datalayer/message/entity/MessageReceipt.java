package com.waben.stock.datalayer.message.entity;

import javax.persistence.*;

/***
* @author yuyidi 2018-01-03 11:44:14
* @class com.waben.stock.datalayer.message.entity.MessageReceipt
* @description 消息回执
*/
@Entity
@Table(name = "message_receipt")
public class MessageReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Messaging.class,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "message")
    private Messaging message;
    /**
     * 接收人
     */
    @Column
    private String recipient;
    /**
     * 0 未读  1 已读
     */
    private Boolean state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Messaging getMessage() {
        return message;
    }

    public void setMessage(Messaging message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
