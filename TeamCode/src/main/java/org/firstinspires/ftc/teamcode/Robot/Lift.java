package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

import java.util.Objects;

public class Lift extends LinearOpMode {

    public Robot robot = null;
    //public AutonomousPLUS AP = new AutonomousPLUS();

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void moveLift(String direction, long wait) {
        if (Objects.equals(direction, "Up")) {
            robot.slideL.setDirection(DcMotor.Direction.FORWARD);
            robot.slideL.setPower(0.75);

            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
            robot.slideRAndOdoPodR.setPower(0.75);
            sleep(wait);
            robot.slideL.setPower(0.1);
            robot.slideRAndOdoPodR.setPower(0.1);
            sleep(500);
        } else if (Objects.equals(direction, "Down")) {
            robot.slideL.setDirection(DcMotor.Direction.REVERSE);
            robot.slideL.setPower(0.5);
            robot.slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
            robot.slideRAndOdoPodR.setPower(0.5);
        }
    }

    /*
    public void armPID(){

        double Kp = 5;
        double Ki = 0;
        double Kd = 0.2;

        double reference = AP.slidePos;
        float encoderPositionL = robot.slideL.getCurrentPosition();
        float encoderPositionR = robot.slideRAndOdoPodR.getCurrentPosition();
        double integralSumL = 0;
        double integralSumR = 0;
        double lastErrorL = 0;
        double lastErrorR = 0;

        ElapsedTime timer = new ElapsedTime();

        while (encoderPositionL != AP.slidePos) {

            // calculate the error
            double errorL = reference - encoderPositionL;
            double errorR = reference - encoderPositionR;

            // rate of change of the error
            double derivativeL = (errorL - lastErrorL) / timer.seconds();
            double derivativeR = (errorR - lastErrorR) / timer.seconds();

            // sum of all error over time
            integralSumL = integralSumL + (errorL * timer.seconds());

            double outL = (Kp * errorL) + (Ki * integralSumL) + (Kd * derivativeL);
            double outR = (Kp * errorR) + (Ki * integralSumR) + (Kd * derivativeR);

            robot.slideL.setPower(outL);
            robot.slideRAndOdoPodR.setPower(outR);

            lastErrorL = errorL;
            lastErrorR = errorR;

            // reset the timer for next time
            timer.reset();

        }
    }

     */

}
