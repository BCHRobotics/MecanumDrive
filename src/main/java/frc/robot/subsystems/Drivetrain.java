package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem{

    //How much we have to divde our encoders to get inches
    double encoderCal = 1;

    //Init talons
    Spark SPARKLEFTFRONT = new Spark(RobotMap.TALONLEFTFRONT);
    Spark SPARKLEFTBACK = new Spark(RobotMap.TALONLEFTBACK);

    Spark SPARKRIGHTFRONT = new Spark(RobotMap.TALONRIGHTFRONT);
    Spark SPARKRIGHTBACK = new Spark(RobotMap.TALONRIGHTBACK);

    

    public Drivetrain(){

/*        //Set ramp rates
        double rampRate = 0.4;
        SPARKLEFTFRONT.configOpenloopRamp(rampRate);
        SPARKLEFTBACK.configOpenloopRamp(rampRate);

        SPARKRIGHTFRONT.configOpenloopRamp(rampRate);
        SPARKRIGHTBACK.configOpenloopRamp(rampRate);*/

    }

    public void mecanumDrive(double moveSpeed, double rotateSpeed) {
        
        double r = Math.hypot(RobotMap.OI_DRIVESTICK_MOVEX, RobotMap.OI_DRIVESTICK_MOVEY);
        double robotAngle = Math.atan2(RobotMap.OI_DRIVESTICK_MOVEY, RobotMap.OI_DRIVESTICK_MOVEX) - Math.PI / 4;
        double rightX = RobotMap.OI_DRIVESTICK_ROTATE;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;


        SPARKLEFTFRONT.set(v1*.2);
        SPARKRIGHTFRONT.set(v2*.2);
        SPARKRIGHTBACK.set(v3*.2);
        SPARKLEFTBACK.set(v4*.2);
        
    }
    
   /* public double getEncoderRight(){
    
        double encoderMove = this.TALONLEFT2.getSelectedSensorVelocity();
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }

    public double getEncoderLeft(){
    
        double encoderMove = this.TALONRIGHT2.getSelectedSensorVelocity();
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }*/

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}