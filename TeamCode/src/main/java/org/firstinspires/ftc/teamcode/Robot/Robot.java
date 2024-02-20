package org.firstinspires.ftc.teamcode.Robot;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Robot {

    public Drivetrain DT;
    public Lift lift;
    public Launcher launcher;

    public Claw claw;
    public Hook hook;
    public Arm arm;
    public Lights lights;

    public WebcamName CamCam;

    public Telemetry telemetry;
    public IMU imu;

    //init and declare war
    public OpMode opmode;
    public HardwareMap hardwareMap;
    public double drivetrainSpeed = 0.75;

    //These are for odometry
    double lastLeftPos;
    double lastRightPos;
    double lastCenterPos;
    public float actualX;
    public float actualY;
    double deltaLeft;
    double deltaRight;
    double deltaCenter;
    double deltaX;
    double deltaY;
    double phi;
    double deltaMiddle;
    double deltaPerpendicular;
    public double refHeading;
    double trackWidth = 12.25; //inches between centers of side odometry pods
    double centerPodToCenter = 7.5; //inches between center of center pod and center of robot


    //Initialize motors and servos
    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        // This section turns the names of the pieces of hardware into variables that we can program with.
        // Make sure that the device name is the exact same thing you typed in on the configuration on the driver hub.
        CamCam = hardwareMap.get(WebcamName.class, "CamCam");

        claw = new Claw(hardwareMap.get(Servo.class, "leftClaw"), hardwareMap.get(Servo.class, "rightClaw"), hardwareMap.get(Servo.class, "SecondaryClaw"), telemetry);
        DT = new Drivetrain(hardwareMap.get(DcMotor.class, "frontRightDrive"), hardwareMap.get(DcMotor.class, "frontLeftDrive"), hardwareMap.get(DcMotor.class, "backRightDrive"), hardwareMap.get(DcMotor.class, "backLeftDrive"), telemetry);
        launcher = new Launcher(hardwareMap.get(DcMotor.class, "droneAndOdoPodL"));
        hook = new Hook(hardwareMap.get(Servo.class,"hookServo"),hardwareMap.get(DcMotor.class,"hookAndOdoPodC"));
        lift = new Lift(hardwareMap.get(DcMotor.class, "slideL"),hardwareMap.get(DcMotor.class, "slideRAndOdoPodR"));
        arm = new Arm(hardwareMap.get(Servo.class, "armL"),hardwareMap.get(Servo.class, "armR"));
        lights = new Lights(hardwareMap.get(RevBlinkinLedDriver.class,"lights"));

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

        DT.setDefaultBehaviors();
        lift.setDefaultBehaviors();

        //This is new..
        telemetry.addData("Status", "Initialized");

        refHeading = imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;

    }

    @SuppressLint("DefaultLocale")
    public void standardTelemetryOutput(){
        telemetry.addData("Odometry",String.format("Odo Pod Left Location (%d)", launcher.droneAndOdoPodL.getCurrentPosition()));
        telemetry.addData("Odometry",String.format("Odo Pod Right Location (%d)", lift.slideRAndOdoPodR.getCurrentPosition()));
        telemetry.addData("Odometry",String.format("Odo Pod Center Location (%d)", hook.hookAndOdoPodC.getCurrentPosition()));

        telemetry.addData("Motors", String.format("FL Power(%.2f) FL Location (%d) FL Target (%d)", DT.frontLeftDrive.getPower(), DT.frontLeftDrive.getCurrentPosition(), DT.frontLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("FR Power(%.2f) FR Location (%d) FR Target (%d)", DT.frontRightDrive.getPower(), DT.frontRightDrive.getCurrentPosition(), DT.frontRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BL Power(%.2f) BL Location (%d) BL Target (%d)", DT.backLeftDrive.getPower(), DT.backLeftDrive.getCurrentPosition(), DT.backLeftDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("BR Power(%.2f) BR Location (%d) BR Target (%d)", DT.backRightDrive.getPower(), DT.backRightDrive.getCurrentPosition(), DT.backRightDrive.getTargetPosition()));
        telemetry.addData("Motors", String.format("Slide Power (%.2f) Arm Location (%d) Arm Target (%d)", lift.slideL.getPower(), lift.slideL.getCurrentPosition(), lift.slideL.getTargetPosition()));
        telemetry.addData("Motors", String.format("Hook Motor Power (%.2f)", hook.hookAndOdoPodC.getPower()));


        telemetry.addData("ArmL", arm.armL.getPosition());
        telemetry.addData("ArmR", arm.armR.getPosition());
        telemetry.addData("ClawL", claw.leftClaw.getPosition());
        telemetry.addData("ClawR", claw.rightClaw.getPosition());
        telemetry.update();
    }

    public void findDisplacement(){

        //Calculate changes from the last time we measured
        deltaLeft = launcher.droneAndOdoPodL.getCurrentPosition() - lastLeftPos;
        deltaRight = lift.slideRAndOdoPodR.getCurrentPosition() - lastRightPos;
        deltaCenter = hook.hookAndOdoPodC.getCurrentPosition() - lastCenterPos;

        phi = (deltaLeft - deltaRight) / trackWidth;
        deltaMiddle = (deltaLeft + deltaRight) / 2;
        deltaPerpendicular = deltaCenter - centerPodToCenter * phi;

        deltaX = deltaMiddle * Math.cos(refHeading) - deltaPerpendicular * Math.sin(refHeading);
        deltaY = deltaMiddle * Math.sin(refHeading) + deltaPerpendicular * Math.cos(refHeading);

        actualX += deltaX;
        actualY += deltaY;
        refHeading += phi;

        lastLeftPos = launcher.droneAndOdoPodL.getCurrentPosition();
        lastRightPos = lift.slideRAndOdoPodR.getCurrentPosition();
        lastCenterPos = hook.hookAndOdoPodC.getCurrentPosition();
    }

    public void odoPodReset(){
        launcher.droneAndOdoPodL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.slideRAndOdoPodR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hookAndOdoPodC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



}