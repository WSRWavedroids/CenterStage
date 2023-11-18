package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;

public class Hook extends LinearOpMode {

    public Robot robot = null;

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void SuspendRobot(){
        //hookMotor.setDirection(DcMotor.Direction.REVERSE);//Inverted
        robot.hookAndOdoPodC.setPower(0.5); //if no work then resume set direction
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
