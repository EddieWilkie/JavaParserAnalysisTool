public class Test {

    private int lock;

    public Test(int lock){
        if(lock == 0)
            this.lock = 0;
        else
            this.lock = 1;
    }

    public void printLockStatus(){
        switch(lock) {
            case 1:
                System.out.println("System is locked.");
                break;
            case 0:
                System.out.println("System is unlocked.");
                break;
        }
    }
    public static final void main(String args[]){
        Test lockTest = new Test(0);
        lockTest.printLockStatus();
    }
}
