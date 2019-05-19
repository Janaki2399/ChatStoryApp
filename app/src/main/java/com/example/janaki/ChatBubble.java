package com.example.janaki;


public class ChatBubble {

    public String content;
    public  String myMessage;
    public  String image;

    public ChatBubble(String content,String myMessage,String image ) {
        this.content = content;
        this.myMessage = myMessage;
        this.image=image;
    }

    public String getContent() {
        return content;
    }

    public  String myMessage() {
        return myMessage;
    }

    public  String getImage()
    {
        return image;
    }
    public void setImage(String image) {
        this.image=image;
    }
}

