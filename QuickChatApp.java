import java.util.Scanner;

public class QuickChatApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();
        System.out.println("=== Registration ===");
        System.out.print("Enter username (kyl_1): ");
        String user = sc.nextLine();
        System.out.print("Enter password (Ch&&sec@ke99!): ");
        String pass = sc.nextLine();
        System.out.print("Enter cell (+27838968976): ");
        String cell = sc.nextLine();
        String reg = login.registerUser(user, pass, cell);
        System.out.println(reg);
        if(!reg.equals("User registered successfully.")) return;

        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String u2 = sc.nextLine();
        System.out.print("Password: ");
        String p2 = sc.nextLine();
        boolean ok = login.loginUser(user, pass, u2, p2);
        System.out.println(login.returnLoginStatus(ok));
        if(!ok) return;

        System.out.println("\nWelcome to QuickChat.");
        while(true) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choice: ");
            String ch = sc.nextLine();
            if(ch.equals("1")) {
                System.out.print("How many messages? ");
                int n = Integer.parseInt(sc.nextLine());
                for(int i=0;i<n;i++) {
                    System.out.print("Recipient (+27...): ");
                    String rec = sc.nextLine();
                    if(rec.length()>12 || !rec.startsWith("+27")) {
                        System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                        i--; continue;
                    }
                    System.out.print("Message (max 250 chars): ");
                    String txt = sc.nextLine();
                    if(txt.length()>250) {
                        System.out.println("Please enter a message of less than 250 characters");
                        i--; continue;
                    }
                    Message m = new Message(rec, txt);
                    System.out.println("1) Send 2) Disregard 3) Store");
                    int opt = Integer.parseInt(sc.nextLine());
                    System.out.println(m.SentMessage(opt));
                    System.out.println("ID: "+m.getMessageID()+" Hash: "+m.getHash());
                }
                System.out.println("Total sent: "+Message.returnTotalMessages());
            } else if(ch.equals("2")) {
                System.out.println(Message.displayReport());
                System.out.println("Longest: "+Message.getLongestMessage());
            } else if(ch.equals("3")) {
                break;
            }
        }
        sc.close();
    }
}