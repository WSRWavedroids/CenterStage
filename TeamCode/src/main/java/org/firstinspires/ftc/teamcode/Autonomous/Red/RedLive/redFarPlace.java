package org.firstinspires.ftc.teamcode.Autonomous.Red.RedLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Far Place")
    public class redFarPlace extends AutonomousPLUS {

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
                    telemetry.addData(target, "Its here");
                    if (currentPosition != null) {
                        target = currentPosition;
                        telemetry.addLine();
                        telemetry.addData("Its time to", "Gatekeep, Gasslight Girlboss");
                        telemetry.addData("Ur gonna do", "great");
                    }
                    telemetry.update();

                    sleep(20);
                }
            }
            //Start and position yellow
            waitForStart();
            telemetry.addData(currentPosition,"here now");
            robot.closeClaw();
            robot.closeSecondaryClaw();
            prepareNextAction(100);
            sleepTime = 175;
            robot.rotateArmUp();
            prepareNextAction(100);
            //Branches here
            moveRobotForward(150, 2);
           moveRobotLeft(50,2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                moveRobotForward(700, 2);
                moveRobotLeft(575, 2);
                moveRobotForward(450,2);
                prepareNextAction(200);//new
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(750, 2);
                robot.rotateArmDown();
            }


            else if(target == "Center")
            {
                telemetry.addData("Going to", "Center");
                robot.rotateArmDown();
                moveRobotLeft(100, 2);
                moveRobotForward(1600, 50);
                prepareNextAction(200);//new
                sleepTime = 100;
                speed = .75;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(750, 1);
                robot.rotateArmDown();
            }

            else if(target == "Right Zone")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Right");
                //moveRobotLeft(1500, 2);//This value is off
                speed = .80;
                moveRobotForward(830, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(195, 2);
                moveRobotForward(540, 2);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(200);
                moveRobotBackward(1000, 2);
                robot.rotateArmDown();

            } else {
                telemetry.addData("Going to", "Left");
                moveRobotForward(700, 2);
                moveRobotLeft(575, 2);
                moveRobotForward(450,2);
                prepareNextAction(200);//new
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(750, 2);
                robot.rotateArmDown();
            }
        }
    }

