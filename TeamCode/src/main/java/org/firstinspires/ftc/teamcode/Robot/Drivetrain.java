package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain {

    public Robot robot = null;
    public HardwareMap hardwareMap;

    public final DcMotor frontRightDrive;
    public final DcMotor frontLeftDrive;
    public final DcMotor backRightDrive;
    public final DcMotor backLeftDrive;

    public final Telemetry telemetry;

    public Drivetrain(DcMotor frontRightDrive, DcMotor frontLeftDrive, DcMotor backRightDrive, DcMotor backLeftDrive, Telemetry telemetry) {
        this.frontRightDrive = frontRightDrive;
        this.frontLeftDrive = frontLeftDrive;
        this.backRightDrive = backRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.telemetry = telemetry;
    }

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
    }

    public boolean isWheelsBusy(){
        return backLeftDrive.isBusy() || frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backRightDrive.isBusy();
    }

    public void powerSet(double speed) {
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);
    }

    public void encoderReset(){
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setDefaultBehaviors(){
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setIndividualPowers ( float[] motorPowers){
        // This function creates an array so that the function below works.
        // Don't mess with this function unless you know what you're doing.

        if (motorPowers.length != 4) {
            return;
        }
        frontLeftDrive.setPower(-motorPowers[0]);
        frontRightDrive.setPower(-motorPowers[1]);
        backLeftDrive.setPower(-motorPowers[2]);
        backRightDrive.setPower(-motorPowers[3]);
    }

    public void moveMecanumDrive (float forwardPower, float strafePower, float turnPower) {
        // We don't really know how this function works, but it makes the wheels drive, so we don't question it.
        // Don't mess with this function unless you REALLY know what you're doing.

        float rightX = turnPower;
        float leftY = forwardPower;
        float leftX = strafePower;

        float[] motorPowers = new float[4];

        motorPowers[0] = (leftY + leftX + rightX);
        motorPowers[1] = (leftY - leftX - rightX);
        motorPowers[2] = (leftY - leftX + rightX);
        motorPowers[3] = (leftY + leftX - rightX);

        float max = getLargestAbsVal(motorPowers);
        if (max < 1) {
            max = 1;
        }

        for (int i = 0; i < motorPowers.length; i++) {
            motorPowers[i] *= (0.75 / max);

            float abs = Math.abs(motorPowers[i]);
            if (abs < 0.05) {
                motorPowers[i] = 0.0f;
            }
            if (abs > 1.0) {
                motorPowers[i] /= abs;
            }
        }

        setIndividualPowers(motorPowers);

    }

    private float getLargestAbsVal ( float[] values){
        // This function does some math!
        float max = 0;
        for (float val : values) {
            if (Math.abs(val) > max) {
                max = Math.abs(val);
            }
        }
        return max;
    }

}