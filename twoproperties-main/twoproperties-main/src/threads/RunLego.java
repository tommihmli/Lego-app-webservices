package threads;


import data.*;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class RunLego implements Runnable{
    private static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    private static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if(Robot.getRun()==1){
				motorA.setPower(Robot.turnRight());
				motorB.setPower(Robot.turnLeft());
			}
			else{
				motorA.setPower(0);
				motorB.setPower(0);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}


	}

	public static void peruuta(){

		 int s=-20;
		 int tu=40;
		 int peruutus1=-20-40;
		int peruutus2=-20+40;
		motorA.setPower(peruutus1);
		motorB.setPower(peruutus2);
	}

}
