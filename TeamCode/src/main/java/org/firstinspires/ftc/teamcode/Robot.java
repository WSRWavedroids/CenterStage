package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.util.Objects;

public class Robot {

    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor slide;
    public DcMotor turntable;
    public Servo whiteClaw;
    public Telemetry telemetry;
    public BNO055IMU imu;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public static double parkingZone;
    public String startingPosition;
    public String controlMode = "Robot Centric";


    //construct robot
    public Robot() {

    }

    //Initialize motors and servos
    public void init(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        slide = hardwareMap.get(DcMotor.class, "slide");
        turntable = hardwareMap.get(DcMotor.class, "turntable");
        whiteClaw = hardwareMap.get(Servo.class, "whiteClaw");

        //this.frontLeftDrive = frontLeftDrive;
        //this.frontRightDrive = frontRightDrive;
        //this.backLeftDrive = backLeftDrive;
        //this.backRightDrive = backRightDrive;
        //this.slide = slide;
        //this.turntable = turntable;
        //this.whiteClaw = whiteClaw;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        // This section sets the direction of all of the motors. Depending on the motor, this may change later in the program.
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        // This tells the motors to chill when we're not powering them.
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");

    }


    public boolean isWheelsBusy(){
        return backLeftDrive.isBusy() || frontLeftDrive.isBusy() || frontRightDrive.isBusy() || backRightDrive.isBusy();
    }

    public void stopAllMotors() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void setTargets(String direction, int ticks) {

        if (Objects.equals(direction, "Right")){
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Left"){
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks + backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks + backRightDrive.getCurrentPosition());

        } else if (direction == "Forward"){
            frontLeftDrive.setTargetPosition(-ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Backward") {
            frontLeftDrive.setTargetPosition(ticks - frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks - frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Right") {
            frontLeftDrive.setTargetPosition(ticks + frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(-ticks - frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(-ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Turn Left") {
            frontLeftDrive.setTargetPosition(-ticks - frontLeftDrive.getCurrentPosition());
            frontRightDrive.setTargetPosition(ticks + frontRightDrive.getCurrentPosition());
            backLeftDrive.setTargetPosition(-ticks - backLeftDrive.getCurrentPosition());
            backRightDrive.setTargetPosition(ticks - backRightDrive.getCurrentPosition());

        } else if (direction == "Arm"){
            slide.setTargetPosition(ticks + slide.getCurrentPosition());

        } else if (direction == "Turntable"){
            turntable.setTargetPosition(-ticks + turntable.getCurrentPosition());
        }

    }

    public void positionRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void powerSet(double speed) {
        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

    }

    public void openAndCloseClaw (double position){
        whiteClaw.setPosition(position);

        if (position == 0){
            telemetry.addData("Claw", "Closed");
        } else if (position >= 0.3){
            telemetry.addData("Claw", "Open");
        }
    }

    public void encoderRunningMode(){
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderReset(){
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @SuppressLint("DefaultLocale")
    public void tellMotorOutput(){
        telemetry.addData("Control Mode", controlMode);
        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", frontLeftDrive.getPower(), frontLeftDrive.getCurrentPosition(), frontLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", frontRightDrive.getPower(), frontRightDrive.getCurrentPosition(), frontRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", backLeftDrive.getPower(), backLeftDrive.getCurrentPosition(), backLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", backRightDrive.getPower(), backRightDrive.getCurrentPosition(), backRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("Arm Power (%.2f) Arm Location (%d) Arm Target (%d)", slide.getPower(), slide.getCurrentPosition(), slide.getTargetPosition()));
        telemetry.addData("Motors", String.format("Turntable (%.2f) Turntable Location (%d) Turntable Target (%d)", turntable.getPower(), turntable.getCurrentPosition(), turntable.getTargetPosition()));
        telemetry.addData("Claw", whiteClaw.getPosition());
        telemetry.update();
    }

    public double inchesToTicks(double inches){
        // returns the inches * ticks per rotation / wheel circ
        return ((inches/12.25) * 537.6 / .5);
        //todo Reference that 1 inch ~= 50 ticks
    }

    public void moveArm(String direction){
        if (direction == "Up"){
            slide.setPower(0.75);
            slide.setDirection(DcMotor.Direction.REVERSE);
        } else if (direction == "Down"){
            slide.setPower(0.25);
            slide.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void holdArm(){
        slide.setDirection(DcMotor.Direction.REVERSE);
        slide.setPower(0.1);
    }


}
