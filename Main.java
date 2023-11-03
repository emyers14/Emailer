// Ethan Myers
// Object-Orineted Programming
// Emailer
// 11/03/23
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an individual email communication.
 */
class Email {
    private ArrayList<String> recipients;
    private String subject;
    private String body;
    private boolean sent;

    public Email() {
        recipients = new ArrayList<String>();
        subject = "";
        body = "";
        sent = false;
    }

    public void addRecipient(String recipient) {
        recipients.add(recipient);
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSent() {
        return sent;
    }

    public void send() {
        sent = true;
    }

    @Override
    public String toString() {
        StringBuilder emailStr = new StringBuilder();
        emailStr.append("To: ");
        for (String recipient : recipients) {
            emailStr.append(recipient).append(",");
        }
        emailStr.setLength(emailStr.length() - 1);
        emailStr.append("\nSubject: ").append(subject).append("\nStatus: ").append(sent ? "sent" : "not sent");
        emailStr.append("\nBody:\n").append(body);
        return emailStr.toString();
    }
}

/**
 * Utility class for printing and saving emails.
 */
class EmailPrinter {
    public static void printEmails(ArrayList<Email> emails) {
        for (Email email : emails) {
            System.out.println(email);
        }
    }

    public static void saveEmailsToFile(ArrayList<Email> emails, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Email email : emails) {
                writer.write(email.toString());
                writer.write("\n\n");
            }
            System.out.println("Emails were saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save emails to the file.");
        }
    }
}

/**
 * The main application for the email program.
 */
public class EmailApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Email> emails = new ArrayList<Email>();

        while (true) {
            System.out.println("********************************************************************************");
            System.out.println("                          Emailer Version 1.0");
            System.out.println("********************************************************************************");
            System.out.println("Here are your choices:");
            System.out.println("1. Write email");
            System.out.println("2. List emails");
            System.out.println("3. Send emails");
            System.out.println("4. Save emails to file");
            System.out.println("5. Exit");
            System.out.print("Enter the number of your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    Email email = new Email();
                    System.out.print("Enter recipients' email (comma-separated): ");
                    String recipientsInput = scanner.nextLine();
                    String[] recipientsArray = recipientsInput.split(",");
                    for (String recipient : recipientsArray) {
                        email.addRecipient(recipient.trim());
                    }
                    System.out.print("Enter subject: ");
                    email.setSubject(scanner.nextLine());
                    System.out.print("Enter body: ");
                    email.setBody(scanner.nextLine());
                    emails.add(email);
                    break;

                case 2:
                    EmailPrinter.printEmails(emails);
                    break;

                case 3:
                    for (Email emailToSend : emails) {
                        emailToSend.send();
                    }
                    System.out.println("All emails have been sent.");
                    break;

                case 4:
                    System.out.print("Enter the name of the file to save: ");
                    String fileName = scanner.nextLine();
                    EmailPrinter.saveEmailsToFile(emails, fileName);
                    break;

                case 5:
                    System.out.println("Thank you for using this program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
