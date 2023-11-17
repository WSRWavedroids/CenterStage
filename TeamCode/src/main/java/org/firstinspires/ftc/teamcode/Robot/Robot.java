package org.firstinspires.ftc.teamcode.Robot;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.comp.Todo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.android.util.Size;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class Robot {

    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor slideL;
    public DcMotor slideRAndOdoPodR;
    public DcMotor hookAndOdoPodC;
    public DcMotor droneAndOdoPodL;

    public Servo leftClaw;
    public Servo rightClaw;
    public Servo hookServo;
    public Servo armL;
    public Servo armR;

    public WebcamName CamCam;

    public Telemetry telemetry;
    public IMU imu;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public String startingPosition;
    public String controlMode = "Robot Centric";
    public double drivetrainSpeed = 0.75;

    //These are for odometry
    double lastLeftPos;
    double lastRightPos;
    double lastCenterPos;
    public double actualX;
    public double actualY;

    //Initialize motors and servos
    public Robot(HardwareMap hardwareMap, Telemetry telemetry, OpMode opmode){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opmode = opmode;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        slideL = hardwareMap.get(DcMotor.class, "slideL");
        slideRAndOdoPodR = hardwareMap.get(DcMotor.class, "slideR");
        hookAndOdoPodC = hardwareMap.get(DcMotor.class, "hookAndOdoPodC");

        armL = hardwareMap.get(Servo.class, "armL");
        armR = hardwareMap.get(Servo.class, "armR");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        hookServo = hardwareMap.get(Servo.class, "hookServo");
        CamCam = hardwareMap.get(WebcamName.class, "CamCam");
        droneAndOdoPodL = hardwareMap.get(DcMotor.class, "droneAndOdoPodL");

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        new Orientation(
                                AxesReference.INTRINSIC,
                                AxesOrder.XYZ,
                                AngleUnit.DEGREES,
                                45,
                                0,
                                90,
                                0  // acquisitionTime, not used
                                //TODO: Check these values
                        )
                )
        );

        imu.initialize(myIMUparameters);

        // This section sets the direction of all of the motors. Depending on the motor, this may change later in the program.
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE); //Was inverted as forward
        slideL.setDirection(DcMotor.Direction.REVERSE);//inverted
        slideRAndOdoPodR.setDirection(DcMotor.Direction.FORWARD);
        hookAndOdoPodC.setDirection(DcMotor.Direction.FORWARD);

        // This tells the motors to chill when we're not powering them.
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hookAndOdoPodC.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //This is new..
        telemetry.addData("Status", "Initialized");

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
            slideL.setTargetPosition(ticks + slideL.getCurrentPosition());
            slideRAndOdoPodR.setTargetPosition(ticks + slideRAndOdoPodR.getCurrentPosition());

        }

    }

    @SuppressLint("DefaultLocale")
    public void standardTelemetryOutput(){
        telemetry.addData("Control Mode", controlMode);
        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", frontLeftDrive.getPower(), frontLeftDrive.getCurrentPosition(), frontLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", frontRightDrive.getPower(), frontRightDrive.getCurrentPosition(), frontRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", backLeftDrive.getPower(), backLeftDrive.getCurrentPosition(), backLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", backRightDrive.getPower(), backRightDrive.getCurrentPosition(), backRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("Slide Power (%.2f) Arm Location (%d) Arm Target (%d)", slideL.getPower(), slideL.getCurrentPosition(), slideL.getTargetPosition()));
        telemetry.addData("Motors", String.format("Hook Motor Power (%.2f) Arm Location (%d) Arm Target (%d)", hookAndOdoPodC.getPower()));
        telemetry.addData("Odometry",String.format("Odo Pod Left Location (%d)", droneAndOdoPodL.getCurrentPosition()));
        telemetry.addData("Odometry",String.format("Odo Pod Right Location (%d)", slideRAndOdoPodR.getCurrentPosition()));
        telemetry.addData("Odometry",String.format("Odo Pod Center Location (%d)", hookAndOdoPodC.getCurrentPosition()));
        telemetry.addData("ArmL", armL.getPosition());
        telemetry.addData("ArmR", armR.getPosition());
        telemetry.addData("ClawL", leftClaw.getPosition());
        telemetry.addData("ClawR", rightClaw.getPosition());
        telemetry.update();
    }

    public double inchesToTicks(double inches){
        // returns the inches * ticks per rotation / wheel circ
        return ((inches/12.25) * 537.6 / .5);
        //todo Reference that 1 inch ~= 50 ticks
    }


    public void findDisplacement(){
        double deltaLeft;
        double deltaRight;
        double deltaCenter;
        double deltaX;
        double deltaY;
        double phi;
        double deltaMiddle;
        double deltaPerpendicular;
        double heading = imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
        double trackWidth = 12.25; //inches between centers of side odometry pods
        double centerPodToCenter = 7.5; //inches between center of center pod and center of robot

        //Calculate changes from the last time we measured
        deltaLeft = droneAndOdoPodL.getCurrentPosition() - lastLeftPos;
        deltaRight = slideRAndOdoPodR.getCurrentPosition() - lastRightPos;
        deltaCenter = hookAndOdoPodC.getCurrentPosition() - lastCenterPos;

        phi = (deltaLeft - deltaRight) / trackWidth;
        deltaMiddle = (deltaLeft + deltaRight) / 2;
        deltaPerpendicular = deltaCenter - centerPodToCenter * phi;

        deltaX = deltaMiddle * Math.cos(heading) - deltaPerpendicular * Math.sin(heading);
        deltaY = deltaMiddle * Math.sin(heading) + deltaPerpendicular * Math.cos(heading);

        actualX += deltaX;
        actualY += deltaY;
        heading += phi;

        lastLeftPos = droneAndOdoPodL.getCurrentPosition();
        lastRightPos = slideRAndOdoPodR.getCurrentPosition();
        lastCenterPos = hookAndOdoPodC.getCurrentPosition();
    }

}