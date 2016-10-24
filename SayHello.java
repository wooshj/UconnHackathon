import java.util.TimerTask;

class SayHello extends TimerTask {
	public CarClass car1 = new CarClass();
	
    public void run() {
       car1.increaseTime();
       System.out.println(car1.getTime());
    }
 }