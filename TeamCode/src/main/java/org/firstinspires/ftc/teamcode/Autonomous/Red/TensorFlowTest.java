package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "Stage", name = "TensorFlowTest")
    public class TensorFlowTest extends AutonomousPLUS {

        public TensorFlow TF = new TensorFlow();
        public String currentPosition;
        public String target;

        public void runOpMode() {

            super.runOpMode();
            if (opModeInInit()) {
                //Needs TF reference
                TF.initTfod(robot.hardwareMap);
                while (opModeInInit()) {
                    currentPosition = TF.position(TF.tfod);

                    if (currentPosition != null) {
                        currentPosition = target;
                    }
                    telemetry.addData("Target equals", target);
                    telemetry.addData("Current position equals", currentPosition);

                    telemetry.update();
                    sleep(20);
                }
            }
            //Start and position yellow
            waitForStart();


            if(currentPosition == "Left Zone")
            {
                telemetry.addData("Going to", "Left");

            }

            else if(currentPosition == "Center")
            {
                telemetry.addData("Going to", "Center");
            }

            else if(currentPosition == "Right Zone")
            {
                telemetry.addData("Going to", "Right");
            }

            sleep(2000);


        }
    }

