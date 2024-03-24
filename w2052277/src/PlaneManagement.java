import java.util.Scanner;
import java.io.*;

public class PlaneManagement {
    final static Scanner input = new Scanner(System.in);
    final static int[][] seat2D = new int[4][];
    final static Ticket[] tickets = new Ticket[56];
    static  int count = 0;

    public static void main(String[] args) {

        seat2D[0] = new int[14];
        seat2D[1] = new int[12];
        seat2D[2] = new int[12];
        seat2D[3] = new int[14];
//        seatInitializer();
        displayMenu();
        askUser();


//        Person input = new Person("raveesha","erda","dasa@gmail.com");
//
////        Person raveesha = new Person();
////        raveesha.setName("raveesha");
////        String name = raveesha.getName()

    }

    public static void askUser() {

        System.out.println("Please select an option: ");
        int userInput = input.nextInt();
//       System.out.println(userInput);

        switch (userInput) {
            case 1:
                buy_seat();
                count++;
                break;
            case 2:
                cancel_seat();
                break;
            case 3:
                find_first_available();
                break;
            case 4:
                show_seating_plan();
                break;
            case 5:
                print_ticket();
            case 6:
                search_ticket();
        }
    }

    public static void show_seating_plan() {
        String[][] seatingPlan = new String[4][];
        seatingPlan[0] = new String[14];
        seatingPlan[1] = new String[12];
        seatingPlan[2] = new String[12];
        seatingPlan[3] = new String[14];


        for (int i = 0; i < seat2D.length; i++) {
            for (int j = 0; j < seat2D[i].length; j++) {
                if (seat2D[i][j] == 0) {
                    seatingPlan[i][j] = "O ";
                    System.out.print(seatingPlan[i][j]);
                } else {
                    seatingPlan[i][j] = "X ";
                    System.out.print(seatingPlan[i][j]);
                }
            }
            System.out.println();
        }
        HomePage();
    }

    public static void find_first_available() {
        boolean found = true;
        String rowLetter;
        for (int j = 0; j < seat2D.length && found; j++) {
            for (int i = 0; i < seat2D[j].length; i++) {
                if (seat2D[j][i] == 0) {
                    if (j == 0) {
                        rowLetter = "A";
                    } else if (j == 1) {
                        rowLetter = "B";
                    } else if (j == 2) {
                        rowLetter = "C";
                    } else if (j == 3) {
                        rowLetter = "D";
                    } else {
                        rowLetter = "invalid";
                    }

                    System.out.println("First available seat is " + rowLetter + (i + 1));
                    found = false;
                    break;
                }
            }
        }
        HomePage();
    }

    public static void cancel_seat() {
        System.out.println("Enter your  booked seat row letter (A/B/C/D): ");
        String rowLetter = input.next().toUpperCase();
        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();

        int rowNumber = 0;

        if (rowLetter.equals("A")) {
            rowNumber = 0;
        } else if (rowLetter.equals("B")) {
            rowNumber = 1;
        } else if (rowLetter.equals("C")) {
            rowNumber = 2;
        } else if (rowLetter.equals("D")) {
            rowNumber = 3;
        }

        if (seat2D[rowNumber][seatNumber - 1] == 0) {
            System.out.println("not a booked seat");
        } else {
            seat2D[rowNumber][seatNumber - 1] = 0;
            System.out.println("Canceled");
        }
        HomePage();
    }

    public static void buy_seat() {
        System.out.println("Enter row letter (A/B/C/D): ");
        String rowLetter = input.next().toUpperCase();

        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();

        int rowNumber = 5;

        if (rowLetter.equals("A")) {
            rowNumber = 0;
        } else if (rowLetter.equals("B")) {
            rowNumber = 1;
        } else if (rowLetter.equals("C")) {
            rowNumber = 2;
        } else if (rowLetter.equals("D")) {
            rowNumber = 3;
        }

        System.out.println("Enter your name: ");
        String name = input.next();

        System.out.println("Enter your surname: ");
        String surname = input.next();

        System.out.println("Enter your email: ");
        String email = input.next();

        Person person = new Person(name, surname, email);
        int price = check_price(seatNumber);
        Ticket ticket = new Ticket(rowLetter, seatNumber, price, person);
        tickets[count] = ticket;

        if (seat2D[rowNumber][seatNumber - 1] == 0) {
            seat2D[rowNumber][seatNumber - 1] = 1;
            System.out.println("seat booked successfully");
        } else {
            System.out.println("Already booked seat!!! select another seat ");
        }

        HomePage();

//        System.out.println(rowLetter);
//        System.out.println(seatNumber);
    }

    public static void HomePage() {
        System.out.println("Press Y to hoome page: ");
        String s = input.next();
        if (s.equalsIgnoreCase("y")) {
            displayMenu();
            askUser();
        } else {
            return;
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Plane Management application");
//        Scanner input = new Scanner(System.in);

        System.out.println("*************************************************");
        System.out.println("*                 MENU OPTIONS                  *");
        System.out.println("*************************************************");

        String[] items = {
                "1) Buy a seat",
                "2) Cancel a seat",
                "3) Find first available seat",
                "4) Show seating plan",
                "5) Print ticket information and total seats",
                "6) Search ticket",
                "0) Quit",
        };
        for (int i = 0; i < 7; i++) {
            System.out.println("     " + items[i]);
        }
        System.out.println("*************************************************");
    }
//
//    public static void seatInitializer() {
//        for (int i = 0; i < seats.length; i++) {
//            seats[i] = false;
//        }
//
//    }

    public static void print_ticket() {

//        try {
//            FileWriter write = new FileWriter("text.txt");
//            BufferedWriter buffer = new BufferedWriter(write);
//            buffer.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
        int price = 0;
        if (tickets != null) {
            for (int k = 0; k < tickets.length; k++) {
                if (tickets[k] != null) {
                    tickets[k].ticketInfo();
                    price += tickets[k].getPrice();
                    System.out.println();
                }
            }
        } else {
            System.out.println("No booked seats to show");
        }
        System.out.println("Total = " + price);
        HomePage();
    }

    public static int check_price(int seat) {
        int price;
        if (seat <= 5) {
            price = 200;
            return price;
        }
        if (seat <= 9) {
            price = 150;
            return price;
        } else {
            price = 180;
        }
        return price;
    }

    public static void search_ticket() {
        System.out.println("Enter row letter (A/B/C/D): ");
        String rowLetter = input.next().toUpperCase();

        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();

        if (tickets != null) {
            for (int k = 0; k < tickets.length; k++) {
                if ((tickets[k]!= null)) {
                    if ((tickets[k].getRow().toUpperCase() + tickets[k].getSeat()).equals(rowLetter + seatNumber)) {
                        System.out.println("Booked seat");
                        tickets[k].ticketInfo();
                    } else {
                        System.out.println("seat is available");
                    }
                }else {
                    System.out.println("all seats are available");
                    break;
                }
            }
        }
        HomePage();
    }

}



