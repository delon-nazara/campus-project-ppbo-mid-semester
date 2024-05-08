package projectbankingsystem;

public class ProjectBankingSystem {
    
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        while (true) {
            try {
                bankingSystem.showMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}