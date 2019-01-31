package frc.robot;

public class RobotMap {

    //RobotMap holds all the global variables for where things are plugged in


    //MOTORS

    //DriveTrain
    public static int TALONLEFTFRONT = 11;
    public static int TALONLEFTBACK = 12;

    public static int TALONRIGHTFRONT = 15;
    public static int TALONRIGHTBACK = 16;


    //Lift
    public static int TALONLIFT = 31;

    /********************************* */

    //CONTROLLERS
    public static int OI_DRIVESTICK = 0;
    public static int OI_FUNSTICK = 1;

    //DriveStick AXIS
    public static int OI_DRIVESTICK_MOVEX = 1;
    public static int OI_DRIVESTICK_MOVEY = 4;
    public static int OI_DRIVESTICK_ROTATE = 4;
    public static int OI_FUNSTICK_LIFT = 1; 

    //DirveStick BUTTONS
    public static int OI_FUNSTICK_GrabOpen = 1; 
    public static int OI_FUNSTICK_GrabClose = 2; 
    public static int OI_DRIVESTICK_Snail = 5; 
    public static int OI_DRIVESTICK_Turbo = 6; 
    public static int OI_FUNSTICK_LiftEnable = 5;

    /********************************* */

    //PHENUMATICS
    public static int GRABBER_SOLENOID_OPEN = 0;
    public static int GRABBER_SOLENOID_CLOSE = 1;


}