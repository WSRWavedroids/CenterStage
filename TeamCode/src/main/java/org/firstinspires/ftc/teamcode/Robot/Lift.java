package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.Objects;

public class Lift {

    public final DcMotor slideL;
    public final DcMotor slideRAndOdoPodR;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public Robot robot = new Robot(hardwareMap, telemetry);

    public Lift(DcMotor slideL, DcMotor slideRAndOdoPodR) {
        this.slideL = slideL;
        this.slideRAndOdoPodR = slideRAndOdoPodR;
    }

    public void moveLift(String direction, long wait) {
        if (Objects.equals(direction, "Up")) {
            slideL.setDirection(DcMotor.Direction.FORWARD);
            slideL.setPower(0.75);

            slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
            slideRAndOdoPodR.setPower(0.75);
            robot.setJankyHomemadeTimer(wait);
            robot.runJankyHomemadeTimer();
            slideL.setPower(0.1);
            slideRAndOdoPodR.setPower(0.1);
            robot.setJankyHomemadeTimer(500);
            robot.runJankyHomemadeTimer();
        } else if (Objects.equals(direction, "Down")) {
            slideL.setDirection(DcMotor.Direction.REVERSE);
            slideL.setPower(0.5);
            slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
            slideRAndOdoPodR.setPower(0.5);
        }
    }

    public void setDefaultBehaviors(){
        slideL.setDirection(DcMotor.Direction.REVERSE);//inverted
        slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);

        slideL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRAndOdoPodR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void holdLift(){
        slideL.setDirection(DcMotor.Direction.FORWARD);//Inverted
        slideL.setPower(0.05);
        slideRAndOdoPodR.setDirection(DcMotor.Direction.REVERSE);
        slideRAndOdoPodR.setPower(0.05);//used to be 0.1
    }

    public void raiseAndLowerLift(double armStickY){
        slideL.setPower(-armStickY * 0.75);
        slideRAndOdoPodR.setPower(-armStickY * 0.75);
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

            slideL.setPower(outL);
            slideRAndOdoPodR.setPower(outR);

            lastErrorL = errorL;
            lastErrorR = errorR;

            // reset the timer for next time
            timer.reset();

        }
    }

     */

}
