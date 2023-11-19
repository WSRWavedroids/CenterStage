package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
//import com.qualcomm.robotcore.hardware.DcMotor;

public class Hook {

    public final Servo hookServo;
    public final DcMotor hookAndOdoPodC;

    public Robot robot = null;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;

    public Hook(Servo hookServo, DcMotor hookAndOdoPodC) {
        this.hookServo = hookServo;
        this.hookAndOdoPodC = hookAndOdoPodC;
    }

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
    }

    public void SuspendRobot(){
        hookAndOdoPodC.setDirection(DcMotor.Direction.FORWARD);
        hookAndOdoPodC.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //hookMotor.setDirection(DcMotor.Direction.REVERSE);//Inverted
        hookAndOdoPodC.setPower(0.5); //if no work then resume set direction
    }

    /*
    public void raiseHook(String direction)
    {
        if (direction == "HookGoUp")
        {
            robot.hookAndOdoPodC.setPower(.8);
            robot.hookAndOdoPodC.setDirection(DcMotor.Direction.FORWARD);
        }
        else if (direction == "HookGoDown")
        {
            robot.hookAndOdoPodC.setPower(0.25);
            robot.hookAndOdoPodC.setDirection(DcMotor.Direction.REVERSE);
        }
    }

     */

}
