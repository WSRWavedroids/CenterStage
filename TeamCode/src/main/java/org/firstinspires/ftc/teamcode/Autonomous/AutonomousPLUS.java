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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Launcher;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Arm;

import java.util.Objects;

/**
 * This is the autonomous mode. It moves the robot without us having to touch the controller.
 * Previous programmers really sucked at explaining what any of this meant, so we're trying to do better.
 * This is our third year now of using this file. It's kind of poetic and also adorable.
 */

public class AutonomousPLUS extends LinearOpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    public double speed = 0.4;
    //public double slidePos;

    //DO NOT DELETE THIS LINE! CAPITALIZATION IS VERY IMPORTANT!!!
    public Robot robot = null;
    public Claw claw;
    public Lift lift;
    public Launcher launcher;
    public Arm arm;
    public Hook hook;
    //public Drivetrain robot.DT;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
        claw = new Claw(robot.claw.rightClaw, robot.claw.leftClaw, robot.claw.secondClaw, robot.telemetry);
        lift = new Lift(robot.lift.slideL, robot.lift.slideRAndOdoPodR);
        launcher = new Launcher(robot.launcher.droneAndOdoPodL);
        arm = new Arm(robot.arm.armL,robot.arm.armR);
        hook = new Hook(robot.hook.hookServo, robot.hook.hookAndOdoPodC);

        robot.DT.setAutoBehaviors();
    }

    public void prepareNextAction(long pause) {
        robot.lift.setJankyHomemadeTimer(pause);
        robot.lift.runJankyHomemadeTimer();
    }

    public boolean inTolerance(float target, int pos, int tolerance){
        if(target == pos){
            return true;
        } else if(pos >= target - tolerance){
            //On the lower side of the tolerance
            return true;
        } else if(pos <= target + tolerance){
            //On the upper side of the tolerance
            return true;
        } else {
            return false;
        }
    }

    public void StrafeFromOdometry(float delta, String direction, int tolerance, long pause){

        //1. Find the difference between the target and the actual position (and find the actual position)

        float lTarget = 0;
        float rTarget = 0;
        float cTarget = 0;

        if (direction == "Up" || direction == "Down"){
            lTarget = launcher.droneAndOdoPodL.getCurrentPosition() + delta;
            rTarget = lift.slideRAndOdoPodR.getCurrentPosition() + delta;
        } else {
            cTarget = hook.hookAndOdoPodC.getCurrentPosition() + delta;
        }

        //2. Translate that to motor power

            if (Objects.equals(direction, "Right")) {
                //Just right
                robot.DT.backLeftDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.backRightDrive.setPower(robot.drivetrainSpeed);
                robot.DT.frontLeftDrive.setPower(robot.drivetrainSpeed);
                robot.DT.frontRightDrive.setPower(-robot.drivetrainSpeed);

            } else if (Objects.equals(direction, "Left")) {
                //Just left
                robot.DT.backLeftDrive.setPower(robot.drivetrainSpeed);
                robot.DT.backRightDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.frontLeftDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.frontRightDrive.setPower(robot.drivetrainSpeed);

            } else if (Objects.equals(direction, "Up")){
                //Just up
                robot.DT.backLeftDrive.setPower(robot.drivetrainSpeed);
                robot.DT.backRightDrive.setPower(robot.drivetrainSpeed);
                robot.DT.frontLeftDrive.setPower(robot.drivetrainSpeed);
                robot.DT.frontRightDrive.setPower(robot.drivetrainSpeed);

            } else if (Objects.equals(direction, "Down")){
                //Just down
                robot.DT.backLeftDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.backRightDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.frontLeftDrive.setPower(-robot.drivetrainSpeed);
                robot.DT.frontRightDrive.setPower(-robot.drivetrainSpeed);

            }

        //3. Check the targets against the odometry pod position
        while (!(inTolerance(lTarget, launcher.droneAndOdoPodL.getCurrentPosition(), tolerance) && inTolerance(cTarget, hook.hookAndOdoPodC.getCurrentPosition(), tolerance) && inTolerance(rTarget, lift.slideRAndOdoPodR.getCurrentPosition(), tolerance) && opModeIsActive())){
            robot.findDisplacement();
            telemetry.addData("Odometry", String.valueOf(lTarget), String.valueOf(cTarget), String.valueOf(rTarget));
            telemetry.update();
        }

        //4. Once it hits that point, stop driving
        robot.DT.powerSet(0);
        sleep(pause);

    }

    public void TurnFromOdometry(double Angle, String direction, long pause){

        double startAngle = robot.imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
        double targetAngle = startAngle + Angle;
        double currentAngle = robot.imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;

        if (Objects.equals(direction, "Left")){

            robot.DT.backLeftDrive.setPower(-robot.drivetrainSpeed);
            robot.DT.backRightDrive.setPower(robot.drivetrainSpeed);
            robot.DT.frontLeftDrive.setPower(-robot.drivetrainSpeed);
            robot.DT.frontRightDrive.setPower(robot.drivetrainSpeed);

        } else if (Objects.equals(direction, "Right")){

            robot.DT.backLeftDrive.setPower(robot.drivetrainSpeed);
            robot.DT.backRightDrive.setPower(-robot.drivetrainSpeed);
            robot.DT.frontLeftDrive.setPower(robot.drivetrainSpeed);
            robot.DT.frontRightDrive.setPower(-robot.drivetrainSpeed);

        } else {
            telemetry.addData("Error", "Direction is not called correctly");
        }

        while (currentAngle != targetAngle && opModeIsActive()){
            currentAngle = robot.imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
            if(currentAngle == targetAngle){
                break;
            }
        }

        robot.DT.powerSet(0);
        sleep(pause);

    }

}