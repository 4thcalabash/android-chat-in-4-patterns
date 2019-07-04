package nju.androidchat.client.component;

import android.content.Context;
import android.view.View;

import java.util.UUID;

public class ItemFactory {
    private static String labels[] = new String[]{"![image](",")"};
    private static boolean isImageURL(String text){
        if (text.length() <= labels[0].length() + labels[1].length())return false;
        for (int i = 0;i < labels[0].length();i++){
            if (text.charAt(i) != labels[0].charAt(i))return false;
        }
        for (int i=0;i<labels[1].length();i++){
            if (text.charAt(text.length() - 1 - i) != labels[1].charAt(i))return false;
        }
        return true;
    }
    private static String get_url(String text){
        return text.substring(labels[0].length(),text.length() - labels[1].length());
    }
    public static View createReceive(Context context, String text, UUID messageId){
        if (isImageURL(text)){
            return new ItemImageReceive(context,get_url(text),messageId);
        }else{
            return new ItemTextReceive(context,text,messageId);
        }
    }
    public static View createSend(Context context,String text,UUID messageId,OnRecallMessageRequested onRecallMessageRequested){
        if (isImageURL(text)){
            return new ItemImageSend(context,get_url(text),messageId,onRecallMessageRequested);
        }else{
            return new ItemTextSend(context,text,messageId,onRecallMessageRequested);
        }
    }
}
