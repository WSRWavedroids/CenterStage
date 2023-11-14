package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

public class Lift extends LinearOpMode {

    public Robot robot = null;
    public AutonomousPLUS AP = new AutonomousPLUS();

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void moveLift(String direction, double power) {
        if (direction == "Up") {
            robot.slideL.setDirection(DcMotor.Direction.FORWARD);
            robot.slideL.setPower(0.75);

            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
            robot.slideRAndOdoPodR.setPower(0.75);
            sleep(AP.sleepTime);
            robot.slideL.setPower(0.1);
            robot.slideRAndOdoPodR.setPower(0.1);
            sleep(500);
        } else if (direction == "Down") {
            robot.slideL.setDirection(DcMotor.Direction.REVERSE);
            robot.slideL.setPower(0.5);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
            robot.slideRAndOdoPodR.setPower(0.5);
        }
    }

}
