import java.util.Scanner;
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance=initialBalance;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        if(amount>0){
            balance +=amount;
        }
    }
    public boolean withdraw(double amount){
        if(amount>0 && amount <= balance){
            balance -= amount;
            return true;
        }
        return false;
    }
}

class Atm{
    private BankAccount account;
    public Atm(BankAccount account) {
        this.account =account;
    }

public void displayMenu(){
    System.out.println("ATM Menu:");
     System.out.println("1.Withdraw:");
      System.out.println("2.Deposit:");
       System.out.println("3.Check Balance:");
        System.out.println("4.Exit:");
}
 public void run(){
    Scanner sc= new Scanner(System.in);

    while(true){
        displayMenu();
        System .out.print("Enter your choice:");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            System.out.print("Enter the amount to withdraw:");
            double withdrawAmount =sc.nextDouble();
            if(account.withdraw(withdrawAmount)){
                System.out.print("withdrawal successful.");
            }
            else{
                System.out.println("insufficient balance.");
            }
            break;

            case 2:
            System.out.print("Enter the amount to deposit:");
            double depositAmount =sc.nextDouble();
            account.deposit(depositAmount);
            System.out.println("Deposit successful.");
            break;

            case 3:
            System.out.println("Your account Balance is:" + account.getBalance());
            break;
             
            case 4:
            System.out.println("Thank you for using the ATM.");
            sc.close();
            return;
            default:
            System.out.println("Invalid choice, Please try again.");
            break;
            
          
        }
        }
    
    }
 }
public class MainBankAccount {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); //initial balance
        Atm atm = new Atm(userAccount);
        atm.run();

    }
}
