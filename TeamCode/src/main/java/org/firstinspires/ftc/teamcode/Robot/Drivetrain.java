package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Drivetrain extends LinearOpMode{

    public Robot robot = null;

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public boolean isWheelsBusy(){
        return robot.backLeftDrive.isBusy() || robot.frontLeftDrive.isBusy() || robot.frontRightDrive.isBusy() || robot.backRightDrive.isBusy();
    }

    public void powerSet(double speed) {
        robot.frontLeftDrive.setPower(speed);
        robot.frontRightDrive.setPower(speed);
        robot.backLeftDrive.setPower(speed);
        robot.backRightDrive.setPower(speed);
    }

    public void encoderReset(){
        robot.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



}
