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

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


/**
 * This file is our iterative (Non-Linear) "OpMode" for TeleOp.
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is selected on the Robot Controller and executed.
 * This particular one is called "Lean Mean TeleOp Machine". I had a little too much fun with naming this.
 *
 * This OpMode controls the functions of the robot during the driver-controlled period.
 *
 * If the "@Disabled" line is not commented out, the program will not show up on the driver hub.
 * If you ever have problems with the program not showing up on the driver hub, it's probably because of that.
 *
 * Throughout this program, there are comments explaining what everything does because previous programmers
 * did a horrible job of doing that.
 */

@TeleOp(name="CenterStage Chonk Drive :)", group="CompBot")
public class Basic_TeleOp extends OpMode {

    // This section tells the program all of the different pieces of hardware that are on our robot that we will use in the program.
    private ElapsedTime runtime = new ElapsedTime();
    private double speed = 0.75;
    public Robot robot = null;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {

        // Call the initialization protocol from the Robot class.
        robot = new Robot(hardwareMap, telemetry, this);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    public void init_loop() {telemetry.addData("HYPE", "ARE! YOU! READY?!?!?!?!");}

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    public void start() {
        runtime.reset();
        telemetry.addData("HYPE", "Let's do this!!!");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {

        singleJoystickDrive();

        // This little section updates the driver hub on the runtime and the motor powers.
        // It's mostly used for troubleshooting.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        robot.tellMotorOutput();

        float armStickY = this.gamepad2.left_stick_y;
        float turntableStickX = this.gamepad2.right_stick_x;

        // This section checks what buttons on the Dpad are being pressed and changes the speed accordingly.

        if (gamepad1.back) {
            if (robot.controlMode == "Robot Centric"){
                robot.controlMode = "Field Centric";
                telemetry.addData("Control Mode", "Field Centric Controls");
            } else if (robot.controlMode == "Field Centric"){
                robot.controlMode = "Robot Centric";
                telemetry.addData("Control Mode", "Robot Centric Controls");
            }
        }

        if (gamepad1.dpad_up) {
            speed = 1;
        } else if (gamepad1.dpad_down) {
            speed = 0.25;
        } else if (gamepad1.dpad_left) {
            speed = 0.5;
        } else if (gamepad1.dpad_right) {
            speed = 0.75;
        }

        if (speed == 1) {
            telemetry.addData("Speed", "Fast Boi");
        } else if (speed == 0.5) {
            telemetry.addData("Speed", "Slow Boi");
        } else if (speed == 0.25) {
            telemetry.addData("Speed", "Super Slow Boi");
        } else if (speed == 0.75) {
            telemetry.addData("Speed", "Normal Boi");
        }

        if (gamepad2.left_stick_y < -0.5){
            robot.slideL.setPower(-armStickY * 0.75);
            robot.slideR.setPower(-armStickY * 0.75);
        } else if (gamepad2.left_stick_y > 0.5){
            robot.slideL.setPower(-armStickY * 0.75);
            robot.slideR.setPower(-armStickY * 0.75);
        } else {
            robot.holdArm();
        }


        //A bunch of messy last minute code
        boolean readyToSuspend = false;
        if(gamepad1.y) // might need a
        {
            robot.hookMotor.setPower(0.85);
        }

        if(gamepad1.back)
        {
            robot.hookMotor.setPower(-0.2);
        }

        /*
        while (gamepad1.y)
        {
            robot.hookMotor.setPower(0.85);
        }
        */

        if(gamepad2.dpad_up)
        {
            robot.firePlane(650); //650 was short
        }


        if (!gamepad1.y && readyToSuspend == false && !gamepad1.back )
        {
            robot.hookMotor.setPower(0);
        }

        else if(gamepad1.x)
        {
            if (readyToSuspend == false) //enable hold
            {
                robot.hookMotor.setPower(0.4);
                readyToSuspend = true;
            }
            else if (readyToSuspend == true) //disable hold
            {
                robot.hookMotor.setPower(0);
                readyToSuspend = false;
            }



        }
         if (gamepad1.b)
        {
            robot.hookServo.setPosition(.5);
        }
        else if (gamepad1.a)
        {
            robot.hookServo.setPosition(0);
        }

        if (readyToSuspend ==  true)
        {
            robot.SuspendRobot();
        }






        //Moves the turntable based on the x-coordinate of the right joystick
        //We need to switch out these motor functions for servo stuff... Idk the position we need
        if (gamepad2.y){ // up
            //robot.armL.setPosition(1);
           //robot.rotateLeftArm(0.59); // good no change
            robot.rotateArmUp();

        } else if (gamepad2.x) { //lower
            //robot.rotateLeftArm(0.85); // GOOD NO CHANGE
            //robot.rotateLeftArm(0.6);
            robot.rotateArmDown();
        }
        /*else {
            robot.armR.setPosition(0);
            robot.armL.setPosition(0);
        }
        */


        //other possible code is this without this
        if (this.gamepad2.b) { // open
            robot.openClaw();
        } else if (this.gamepad2.a) {//close
            robot.closeClaw();
        }



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop () { telemetry.addData("Status", "Robot Stopped"); }


    /*
     * The holding cell for all of the random functions we call above.
     */

    public void setIndividualPowers ( float[] motorPowers){
        // This function creates an array so that the function below works.
        // Don't mess with this function unless you know what you're doing.

        if (motorPowers.length != 4) {
            return;
        }
        robot.frontLeftDrive.setPower(-motorPowers[0]);
        robot.frontRightDrive.setPower(-motorPowers[1]);               
        robot.backLeftDrive.setPower(-motorPowers[2]);
        robot.backRightDrive.setPower(-motorPowers[3]);
    }

    private void singleJoystickDrive () {
        // We don't really know how this function works, but it makes the wheels drive, so we don't question it.
        // Don't mess with this function unless you REALLY know what you're doing.

        float rightX = -this.gamepad1.right_stick_x;
        float leftY = this.gamepad1.left_stick_y;
        float leftX = -this.gamepad1.left_stick_x;

        double leftStickAngle = Math.atan2(leftY, leftX);
        double leftStickMagnitude = Math.sqrt(leftX * 2.0 + leftY * 2.0);
        //double robotAngle = robot.imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;

        if (leftStickMagnitude > 1){
            leftStickMagnitude = 1;
        }

        float[] motorPowers = new float[4];

        if (robot.controlMode == "Robot Centric") {

            motorPowers[0] = (leftY + leftX + rightX);
            motorPowers[1] = (leftY - leftX - rightX);
            motorPowers[2] = (leftY - leftX + rightX);
            motorPowers[3] = (leftY + leftX - rightX);

        } else if (robot.controlMode == "Field Centric") {
            /*
            motorPowers[0] = (float) (Math.sin(leftStickAngle + 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[1] = (float) (Math.sin(leftStickAngle - 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[2] = (float) (Math.sin(leftStickAngle - 45 - robotAngle) * leftStickMagnitude + rightX);
            motorPowers[3] = (float) (Math.sin(leftStickAngle + 45 - robotAngle) * leftStickMagnitude + rightX);
            */
        }

        float max = getLargestAbsVal(motorPowers);
        if (max < 1) {
            max = 1;
        }

        for (int i = 0; i < motorPowers.length; i++) {
            motorPowers[i] *= (speed / max);

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
