package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//import java.util.Objects;

public class Arm {
    public Robot robot = null;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;

    public final Servo armL;
    public final Servo armR;

    public Arm(Servo armL, Servo armR) {
        this.armL = armL;
        this.armR = armR;
    }


    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
    }


    /*
    public void moveArm(String direction){
        if (Objects.equals(direction, "Up")){
            robot.slideL.setPower(0.75);
            robot.slideL.setDirection(DcMotor.Direction.FORWARD);//inverted
            robot.slideRAndOdoPodR.setPower(0.75);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
        } else if (Objects.equals(direction, "Down")){
            robot.slideL.setPower(0.25);
            robot.slideL.setDirection(DcMotor.Direction.REVERSE);//Inverted
            robot.slideRAndOdoPodR.setPower(0.25);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
        }
    }


    public void rotateRightArm(double position) // Remember these are opposite directions
    {
        robot.armR.setPosition(position);
    }

     */

    public void rotateLeftArm(double position) // Remember these are opposite directions
    {
        armL.setPosition(position);
    }

    public void rotateArmUp()
    {         //Raise
        rotateLeftArm(0.62); // perfect... The lower the value the higher it goes
        //rotateLeftArm(0.38); // was .3 but too shallow

    }

    public void rotateArmDown()
    {
        //lower
        rotateLeftArm(0.87);
        //rotateRightArm(0.13);

    }

}
