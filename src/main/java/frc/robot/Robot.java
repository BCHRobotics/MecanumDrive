/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.*;
import frc.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Drivetrain m_drivetrain = null;
  public static OI m_oi = null;
  public static Grabber m_grabber = null;
  public static Lift m_lift = null;

  //Gyro
  public ADXRS450_Gyro m_gyro = null;
  public AHRS ahrs = null;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_drivetrain = new Drivetrain();
    m_oi = new OI();
    m_grabber = new Grabber();
    m_lift = new Lift();   
    m_gyro = new ADXRS450_Gyro();

    ahrs = new AHRS(SerialPort.Port.kUSB);

    m_gyro.reset();
    m_gyro.calibrate();
    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("Gyro", m_gyro.getAngle());

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  //Reset gyro when the robot is disabled
  @Override
  public void disabledInit(){
    m_gyro.reset();
  }

  //Reset gyro when the robot is entering teleop
  @Override
  public void teleopInit() {
    super.teleopInit();
    m_gyro.reset();
  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //For testing checking gyro values on console out
    //System.out.println(m_gyro.getAngle());
    //System.out.println(Robot.m_drivetrain.getEncoderLeft());

    //Init the moveSpeed variable
    double moveSpeed = 0.7;

    //Check for Snail and Turbo buttons to speed or slow the robot
    if(Robot.m_oi.ButtonSnail.get() == true){
      moveSpeed = 0.5;
    } else if(Robot.m_oi.ButtonTurbo.get() == true){
      moveSpeed = 1;
    }

    double rotateSpeed = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE);

      double kP = -ahrs.getRate();
    if (rotateSpeed==0){
     kP = kP* 0.2;
    } else{
   kP = kP * 0;
    }

    //Arcade drive command
    Robot.m_drivetrain.mecanumDrive(moveSpeed, rotateSpeed);
    
    //Check if grabber buttons are pressed then open or close grabber
    if(Robot.m_oi.ButtonGrabOpen.get() == true){
        Robot.m_grabber.grabberOpen();
    } else if(Robot.m_oi.ButtonGrabClose.get() == true){
      Robot.m_grabber.grabberClose();
    } 

    //Manipulate the Lift
    if(Robot.m_oi.ButtonLiftEnable.get() == true){
      Robot.m_lift.MoveLift(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT));
    } else {
      Robot.m_lift.MoveLift(-0.15);
    }
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
