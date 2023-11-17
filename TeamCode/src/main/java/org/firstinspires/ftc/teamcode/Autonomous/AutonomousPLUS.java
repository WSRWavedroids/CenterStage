package org.firstinspires.ftc.teamcode.Autonomous;
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Arm;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;

/**
 * This is the autonomous mode. It moves the robot without us having to touch the controller.
 * Previous programmers really sucked at explaining what any of this meant, so we're trying to do better.
 * This is our third year now of using this file. It's kind of poetic and also adorable.
 */

public class AutonomousPLUS extends LinearOpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();

    public double speed = 0.4;
    public int sleepTime;
    public boolean inMarker;
    public double power;
    public double slidePos;

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public Robot robot = null;
    public Hook hook = new Hook();
    public Claw claw = new Claw();
    public Drivetrain DT = new Drivetrain();


    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    //I think the setTargets Function is broken. Motors don't stop at the right place
    public void moveRobotForward(int ticks, long pause) {
        if (opModeIsActive()) {
            robot.setTargets("Backward", ticks);
            DT.positionRunningMode();
        }
        DT.powerSet(speed);

        while (opModeIsActive() && DT.isWheelsBusy()) {
            robot.standardTelemetryOutput();
        }

        DT.stopDrivetrainMotors();
        DT.encoderRunningMode();
        DT.stopDrivetrainMotors();
        sleep(pause);
        DT.encoderReset();
    }

    public void moveRobotBackward(int ticks, long pause) {
        if (opModeIsActive()) {
            robot.setTargets("Forward", ticks);
            DT.positionRunningMode();
            DT.powerSet(speed);

            while (opModeIsActive() && DT.isWheelsBusy()) {
                robot.standardTelemetryOutput();
            }

            DT.stopDrivetrainMotors();
            DT.encoderRunningMode();
            sleep(pause);
            DT.encoderReset();
        }

    }

    public void moveRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Right", ticks);
            DT.positionRunningMode();
            DT.powerSet(speed);

            while (opModeIsActive() && DT.isWheelsBusy()) {
                robot.standardTelemetryOutput();
            }

            DT.stopDrivetrainMotors();
            DT.encoderRunningMode();
            sleep(pause);
            DT.encoderReset();
        }
    }

    public void moveRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Left", ticks);
            DT.positionRunningMode();
            DT.powerSet(speed);

            while (opModeIsActive() && DT.isWheelsBusy()) {
                robot.standardTelemetryOutput();
            }

            DT.stopDrivetrainMotors();
            DT.encoderRunningMode();
            sleep(pause);
            DT.encoderReset();
        }
    }

    public void turnRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Turn Right", ticks);
            DT.positionRunningMode();
            DT.powerSet(speed);

            while (opModeIsActive() && DT.isWheelsBusy()) {
                robot.standardTelemetryOutput();
            }

            DT.stopDrivetrainMotors();
            DT.encoderRunningMode();
            sleep(pause);
            DT.encoderReset();
        }
    }

    public void turnRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Turn Left", ticks);
            DT.positionRunningMode();
            DT.powerSet(speed);

            while (opModeIsActive() && DT.isWheelsBusy()) {
                robot.standardTelemetryOutput();
            }

            DT.stopDrivetrainMotors();
            DT.encoderRunningMode();
            sleep(pause);
            DT.encoderReset();

        }
    }

    public void prepareNextAction(long pause) {
        sleep(pause);
        DT.encoderReset();
    }



    public void StrafeFromOdometry(double deltaX, double deltaY, long pause){

        //1. Find the difference between the target and the actual position (and find the actual position)

        double xTarget = robot.OdoPodL.getCurrentPosition() + deltaX;
        double yTarget = robot.slideRAndOdoPodR.getCurrentPosition() + deltaY;

        //2. Translate that to motor power

        if(deltaY == 0) {

            if (deltaX > 0) {
                //Just right
                robot.backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

                DT.powerSet(robot.drivetrainSpeed);

            } else if (deltaX < 0) {
                //Just left
                robot.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

                DT.powerSet(robot.drivetrainSpeed);
            }

        } else if (deltaY > 0){
            //We're going up

            if (deltaX > 0) {
                //Up+right
                robot.backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.backRightDrive.setPower(robot.drivetrainSpeed);
                robot.frontLeftDrive.setPower(robot.drivetrainSpeed);

            } else if (deltaX < 0) {
                //Up+left
                robot.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.backLeftDrive.setPower(robot.drivetrainSpeed);
                robot.frontRightDrive.setPower(robot.drivetrainSpeed);
            }

        } else if (deltaY < 0){

            if (deltaX > 0) {
                //Down+right
                robot.backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.backLeftDrive.setPower(robot.drivetrainSpeed);
                robot.frontRightDrive.setPower(robot.drivetrainSpeed);
            } else if (deltaX < 0) {
                //Down+left
                robot.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.backRightDrive.setPower(robot.drivetrainSpeed);
                robot.frontLeftDrive.setPower(robot.drivetrainSpeed);
            }
        }

        if(deltaY > 0){
            //Just up
            robot.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

            DT.powerSet(robot.drivetrainSpeed);

        }else if(deltaY < 0){
            //Just down
            robot.backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

            DT.powerSet(robot.drivetrainSpeed);

        }

        //3. Check the targets against the odometry pod position
        while (DT.isWheelsBusy()){
            robot.findDisplacement();
            if(robot.actualX == xTarget && robot.actualY == yTarget){
                break;
            }
        }

        //4. Once it hits that point, stop driving
        DT.powerSet(0);
        sleep(pause);

    }

    public void TurnFromOdometry(double Angle, String direction, long pause){

        double startAngle = robot.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
        double currentAngle;

        if (direction == "Left"){

            robot.backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

            DT.powerSet(robot.drivetrainSpeed);

        } else if (direction == "Right"){

            robot.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

            DT.powerSet(robot.drivetrainSpeed);

        } else {
            telemetry.addData("Error", "Direction is not called correctly");
        }

        while (DT.isWheelsBusy()){
            currentAngle = robot.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
            if(currentAngle == Angle){
                break;
            }
        }

        DT.powerSet(0);
        sleep(pause);

    }
}
