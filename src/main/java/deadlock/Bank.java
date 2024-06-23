package deadlock;

class Bank{
    private int balance;
    private String name;
    public Bank(String name, int balance) {
        this.balance = balance;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
               "balance=" + balance +
               ", name='" + name + '\'' +
               '}';
    }
}