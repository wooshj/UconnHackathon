import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		 int[] carTimes1 = {1,1,4,8,12,15,18,17,15,5,4};
		  int[] carTimes2 = {1,1,5,7,11,14,14,13,13,9,7,5};
		  int[] carTimes3 = {1,1,1,1,5,6,7,8,6,4,3,2};
		  int[] carTimes4 = {0,0,0,0,0,0,0,100,0,0,0,0};
		  int[] distributed = {8,8,8,8,8,9,9,9,9,8,8,8};
		// trafficSimulation(carTimes1,12,10);
		// trafficSimulation(carTimes2,12,10);
		trafficSimulation(carTimes1, 12, 5);
	}

	public static void trafficSimulation(int[] carTimes1, int carPerHourMax, int carPerHourMin) throws InterruptedException {
		ArrayList<CarClass> cars = new ArrayList<>();
		ArrayList<CarClass> exited = new ArrayList<>();

		int carNumber = 0;
		for (int i : carTimes1)
			carNumber += i;
		System.out.println("Number of Cars: " + carNumber);
		int[] carTimes = new int[carNumber / 5];
		for (int j = 0; j < carTimes1.length; j++)
			carTimes[j] = carTimes1[j];
		int timeSpent = 0;
		int carPerHour;
		int hour = 0;
		for (int i = 0; i < 12; i++) {
			carPerHour = carPerHourMax;
			for (int j = 0; j < carTimes[i]; j++)
				cars.add(new CarClass());
			System.out.print("Hour " + (hour) + ": ");
			hour++;
			for (CarClass car1 : cars) {
				car1.increaseTime();
				System.out.print(car1.getTime() + " ");
			}
			if (cars.size() > carPerHour)
				carPerHour -= cars.size() / 5;
			if (carPerHour <= carPerHourMin)
				carPerHour = carPerHourMin;
			System.out.println();
			if (cars.size() > 12) {
				for (int j = 0; j < carPerHour; j++)
					if (cars.isEmpty() != true)
						exited.add(cars.remove(0));
			} else {
				while (cars.isEmpty() == false)
					exited.add(cars.remove(0));
			}
			 Thread.sleep(300);
		}
		while (cars.isEmpty() == false) {
			System.out.print("Hour " + (hour) + ": ");
			hour++;
			carPerHour = carPerHourMax;
			for (CarClass car1 : cars) {
				car1.increaseTime();
				System.out.print(car1.getTime() + " ");
			}
			if (cars.size() > carPerHourMax)
				carPerHour -= cars.size() / 5;
			if (carPerHour <= carPerHourMin)
				carPerHour = carPerHourMin;
			System.out.println();
			if (cars.size() > 12) {
				for (int j = 0; j < carPerHour; j++)
					exited.add(cars.remove(0));
			} else {
				while (cars.isEmpty() == false)
					exited.add(cars.remove(0));
			}

			 Thread.sleep(300);
		}
		int max = 0;
		for (CarClass car : exited) {
			timeSpent += car.getTime();
			if (car.getTime() > max)
				max = car.getTime();
		}
		DecimalFormat df = new DecimalFormat("#.##");
		double timeSpentTotal = timeSpent*2*260/600;
		System.out.println("Number of Cars Exited: " + exited.size());
		System.out.println("Average Time in Minutes Spent in Traffic: " + (double) (timeSpent) / 10);
		System.out.println("Hours Per Year: " + df.format(timeSpentTotal));
		System.out.println("Max Time in Traffic: " + (max) * 10 + " minutes");
	}

}