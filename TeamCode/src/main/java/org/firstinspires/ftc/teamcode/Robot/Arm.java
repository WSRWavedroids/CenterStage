package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm extends LinearOpMode {
    public Robot robot = null;

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void holdArm(){
        robot.slideL.setDirection(DcMotor.Direction.FORWARD);//Inverted
        robot.slideL.setPower(0.05);
        robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
        robot.slideRAndOdoPodR.setPower(0.05);//used to be 0.1
    }

    public void moveArm(String direction){
        if (direction == "Up"){
            robot.slideL.setPower(0.75);
            robot.slideL.setDirection(DcMotor.Direction.FORWARD);//inverted
            robot.slideRAndOdoPodR.setPower(0.75);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
        } else if (direction == "Down"){
            robot.slideL.setPower(0.25);
            robot.slideL.setDirection(DcMotor.Direction.REVERSE);//Inverted
            robot.slideRAndOdoPodR.setPower(0.25);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void rotateRightArm(double position) // Remeber these are opposite directions
    {
        robot.armR.setPosition(position);
    }

    public void rotateLeftArm(double position) // Rememeber these are opposite directions
    {
        robot.armL.setPosition(position);
    }

    public void rotateArmUp()
    {         //Raise
        rotateLeftArm(0.62); // perfect... The lower the value the higher it goes
        //rotateLeftArm(0.3); // was .3 but too shallow

    }

    public void rotateArmDown()
    {
        //lower
        rotateLeftArm(0.87);
        //rotateRightArm(0.09);

    }

}
