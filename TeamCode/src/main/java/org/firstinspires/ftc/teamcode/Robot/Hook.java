package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Hook extends LinearOpMode {

    public Robot robot = null;

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void SuspendRobot(){
        //hookMotor.setDirection(DcMotor.Direction.REVERSE);//Inverted
        robot.hookMotor.setPower(0.5); //if no work then resume setdirection
    }

    public void raiseHook(String direction)
    {
        if (direction == "HookGoUp")
        {
            robot.hookMotor.setPower(.8);
            robot.hookMotor.setDirection(DcMotor.Direction.FORWARD);
        }
        else if (direction == "HookGoDown")
        {
            robot.hookMotor.setPower(0.25);
            robot.hookMotor.setDirection(DcMotor.Direction.REVERSE);
        }
    }

}
