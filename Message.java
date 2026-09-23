import java.util.*;
import java.io.*;

public class Message {
    public static List<String> messageIDs = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> recipients = new ArrayList<>();
    public static List<String> messagesText = new ArrayList<>();
    public static List<String> statuses = new ArrayList<>();
    public static int numMessagesSent = 0;

    private String messageID;
    private String recipient;
    private String text;
    private String hash;

    public Message(String recipient, String text) {
        this.messageID = String.format("%010d", new Random().nextInt(1000000000));
        this.recipient = recipient;
        this.text = text;
    }
    public boolean checkMessageID() { return messageID.length() <= 10; }
    public int checkRecipientCell() {
        if(recipient.length() <= 12 && recipient.startsWith("+27")) return 1;
        return 0;
    }
    public String createMessageHash() {
        String[] words = text.trim().split("\\s+");
        String first = words[0].toUpperCase();
        String last = words[words.length-1].replaceAll("[^a-zA-Z!]", "").toUpperCase();
        return messageID.substring(0,2) + ":" + messageIDs.size() + ":" + first + last;
    }
    public String SentMessage(int choice) {
        this.hash = createMessageHash();
        if(choice == 1) {
            messageIDs.add(messageID); messageHashes.add(hash);
            recipients.add(recipient); messagesText.add(text);
            statuses.add("Sent"); numMessagesSent++;
            storeMessage();
            return "Message successfully sent.";
        } else if(choice == 2) {
            messageIDs.add(messageID); messageHashes.add(hash);
            recipients.add(recipient); messagesText.add(text);
            statuses.add("Disregard");
            return "Press 0 to delete message.";
        } else {
            messageIDs.add(messageID); messageHashes.add(hash);
            recipients.add(recipient); messagesText.add(text);
            statuses.add("Stored");
            storeMessage();
            return "Message successfully stored.";
        }
    }
    public void storeMessage() {
        try {
            FileWriter fw = new FileWriter("stored.json", true);
            fw.write("{\"ID\":\""+messageID+"\",\"Hash\":\""+hash+"\",\"To\":\""+recipient+"\",\"Msg\":\""+text+"\"}\n");
            fw.close();
        } catch(Exception e) {}
    }
    public static int returnTotalMessages() { return numMessagesSent; }
    public String getMessageID(){return messageID;}
    public String getHash(){return hash;}
    public static String getLongestMessage() {
        if(messagesText.isEmpty()) return "";
        int idx=0; for(int i=1;i<messagesText.size();i++) if(messagesText.get(i).length()>messagesText.get(idx).length()) idx=i;
        return messagesText.get(idx);
    }
    public static String deleteByHash(String h) {
        for(int i=0;i<messageHashes.size();i++) if(messageHashes.get(i).equals(h)) {
            messageIDs.remove(i); messageHashes.remove(i); recipients.remove(i);
            messagesText.remove(i); statuses.remove(i);
            return "Message successfully deleted";
        }
        return "Not found";
    }
    public static String displayReport() {
        String s=""; for(int i=0;i<messageIDs.size();i++) s+="Hash: "+messageHashes.get(i)+" To: "+recipients.get(i)+" Msg: "+messagesText.get(i)+"\n";
        return s;
    }
}