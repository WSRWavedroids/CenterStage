/* Copyright (c) 2019 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode.Autonomous.Red;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

/*
 * This OpMode illustrates the basics of TensorFlow Object Detection,
 * including Java Builder structures for specifying Vision parameters.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
@Autonomous(name = "Basic TF Red Score on Board", group = "Stage")

public class TensorFlowRedBoard extends AutonomousPLUS {
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "D20.tflite";
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private static final String TFOD_MODEL_FILE = "D20.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "Blue Prop", "Red Prop"
    };

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    public TfodProcessor tfod;
    /**
     * The variable to store our instance of the vision portal.
     */
    public VisionPortal visionPortal;
    public String currentPosition;
    public String target;

    @Override
    public void runOpMode() {

        super.runOpMode();
        initTfod(robot.hardwareMap);
        waitForStart();

        while(opModeInInit()){
            currentPosition = position(tfod);

            if(currentPosition != null){
                currentPosition = target;
            }
            telemetry.update();
            sleep(20);
        }
        //Start and position yellow
        waitForStart();
        robot.closeClaw();
        prepareNextAction(300);
        sleepTime = 175;
        moveLift("Up", .55);
        prepareNextAction(300);
        //Branches here
        moveRobotForward(150, 2);
        moveRobotRight(1800, 2);


        if(target == "Left Zone")
        {
            telemetry.addData("Going to", "Left");
        }


        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            moveRobotLeft(1610, 2);
            speed = 0.5;//new
            moveRobotForward(1050, 50);
            prepareNextAction(100);//new
            robot.openClaw();
            speed = 0.5;//new
            moveRobotBackward(900, 2);
            turnRobotRight(1070, 2);//
            moveRobotRight(250, 2);
            moveRobotForward(1450, 50);
        }

        else if(target == "Right Zone")
        {
            telemetry.addData("Going to", "Right");
        }

        //Pick up starts here
        moveLift("Down", .22);//new
        prepareNextAction(500);//new
        sleep(500);
        robot.closeClaw();//new
        sleep(500);
        moveLift("Up", .44);//new
        moveRobotBackward(900, 2);//new
        moveRobotLeft(1160, 2);//was 900

        //Place
        robot.rotateArmUp();
        prepareNextAction(200);
        speed = 0.4;
        moveRobotForward(700, 300);// 1375 was too far
        robot.openClaw();
        moveRobotBackward(100, 2);
        prepareNextAction(300);
        moveLift("Down", .55);//

        robot.openClaw();
        prepareNextAction(1000);

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }   // end runOpMode()

    /**
     * Initialize the TensorFlow Object Detection processor.
     */

    public void initTfod(HardwareMap hardwareMap) {

        tfod = new TfodProcessor.Builder()
                .setModelAssetName(TFOD_MODEL_ASSET)
                .setModelLabels(LABELS)
                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "CamCam"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        builder.setCameraResolution(new Size(1280, 720));

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        tfod.setMinResultConfidence(0.40f);


    }   // end method initTfod()

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */


    public String position(TfodProcessor tfod) {
        List<Recognition> currentRecognitions;

        if (tfod.getRecognitions() == null){
            telemetry.addLine("Couldn't find anything :(");
            return null;
        } else {
           currentRecognitions = tfod.getRecognitions();
        }
        telemetry.addData("# Objects Detected", currentRecognitions.size());
        String Position = "";
        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2;
            double y = (recognition.getTop() + recognition.getBottom()) / 2;

            telemetry.addData("", " ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());

            if (x >= 290 && x <= 525 && y >= 180 && y <= 410) {//good
                //Left Zone
                Position = "Left Zone";
                telemetry.addData("Prop in", Position);
            } else if (x >= 680 && x <= 950 && y >= 200 && y <= 380) {//good
                //Middle zone
                Position = "Center";
                telemetry.addData("Prop in", Position);
            } else if (x >= 1090 && x <= 1280 && y >= 240 && y <= 450) {
                //Right zone
                Position = "Right Zone";
                telemetry.addData("Prop in", Position);
            }
            else
            {
                telemetry.addData("It not work :(", x);
                telemetry.addData("It boken", y);
            }

        }
            return Position; // needed to be here
    }

}   // end class
