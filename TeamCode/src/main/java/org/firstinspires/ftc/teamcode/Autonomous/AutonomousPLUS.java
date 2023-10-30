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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
//import org.firstinspires.ftc.teamcode.Autonomous.AprilTags.MayFlowers;

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

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }



    //I think the setTargets Function is broken. Motors don't stop at the right place
    public void moveRobotForward(int ticks, long pause) {
        if (opModeIsActive()) {
            robot.setTargets("Backward", ticks);
            robot.positionRunningMode();
        }
        robot.powerSet(speed);

        while (opModeIsActive() && robot.isWheelsBusy()) {
            robot.tellMotorOutput();
        }

        robot.stopAllMotors();
        robot.encoderRunningMode();
        robot.stopAllMotors();
        sleep(pause);
        robot.encoderReset();
    }

    public void moveRobotBackward(int ticks, long pause) {
        if (opModeIsActive()) {
            robot.setTargets("Forward", ticks);
            robot.positionRunningMode();
            robot.powerSet(speed);

            while (opModeIsActive() && robot.isWheelsBusy()) {
                robot.tellMotorOutput();
            }

            robot.stopAllMotors();
            robot.encoderRunningMode();
            sleep(pause);
            robot.encoderReset();
        }

    }

    public void moveRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Right", ticks);
            robot.positionRunningMode();
            robot.powerSet(speed);

            while (opModeIsActive() && robot.isWheelsBusy()) {
                robot.tellMotorOutput();
            }

            robot.stopAllMotors();
            robot.encoderRunningMode();
            sleep(pause);
            robot.encoderReset();
        }
    }

    public void moveRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Left", ticks);
            robot.positionRunningMode();
            robot.powerSet(speed);

            while (opModeIsActive() && robot.isWheelsBusy()) {
                robot.tellMotorOutput();
            }

            robot.stopAllMotors();
            robot.encoderRunningMode();
            sleep(pause);
            robot.encoderReset();
        }
    }

    public void turnRobotRight(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Turn Right", ticks);
            robot.positionRunningMode();
            robot.powerSet(speed);

            while (opModeIsActive() && robot.isWheelsBusy()) {
                robot.tellMotorOutput();
            }

            robot.stopAllMotors();
            robot.encoderRunningMode();
            sleep(pause);
            robot.encoderReset();
        }
    }

    public void turnRobotLeft(int ticks, long pause) {

        if (opModeIsActive()) {
            robot.setTargets("Turn Left", ticks);
            robot.positionRunningMode();
            robot.powerSet(speed);

            while (opModeIsActive() && robot.isWheelsBusy()) {
                robot.tellMotorOutput();
            }

            robot.stopAllMotors();
            robot.encoderRunningMode();
            sleep(pause);
            robot.encoderReset();

        }
    }

    public void prepareNextAction(long pause) {
        sleep(pause);
        robot.encoderReset();
    }

    public void moveArm(String direction, double power) {
        if (direction == "Up") {
            robot.slide.setDirection(DcMotor.Direction.REVERSE);
            robot.slide.setPower(0.75);
            sleep(sleepTime);
            robot.slide.setPower(0.1);
            sleep(500);
        } else if (direction == "Down") {
            robot.slide.setDirection(DcMotor.Direction.FORWARD);
            robot.slide.setPower(0.5);
        }
    }

    public void moveTurntable(String direction, int distance) {

        if (direction == "Left") {
            robot.turntable.setDirection(DcMotor.Direction.FORWARD); //Check on this...

        } else if (direction == "Right") {
            robot.turntable.setDirection(DcMotor.Direction.REVERSE); //Check on this...
        }

        if (opModeIsActive()) {
            robot.setTargets("Turntable", distance);
            robot.turntable.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.powerSet(0.45);

            while (opModeIsActive() && robot.turntable.isBusy()) {
                robot.tellMotorOutput();
            }

            robot.turntable.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        robot.turntable.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void armPID(){

        double Kp = 5;
        double Ki = 0;
        double Kd = 0.2;

        double reference = slidePos;
        float encoderPosition = robot.slide.getCurrentPosition();
        double integralSum = 0;
        double lastError = 0;

        ElapsedTime timer = new ElapsedTime();

        while (encoderPosition != slidePos) {

            // calculate the error
            double error = reference - encoderPosition;

            // rate of change of the error
            double derivative = (error - lastError) / timer.seconds();

            // sum of all error over time
            integralSum = integralSum + (error * timer.seconds());

            double out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

            robot.slide.setPower(out);

            lastError = error;

            // reset the timer for next time
            timer.reset();

        }
    }
}
