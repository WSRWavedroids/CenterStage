package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue score on board With TF")
    public class BlueCloseScoreOnBoardTF extends AutonomousPLUS {

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
                    }
                    telemetry.update();
                    sleep(20);
                }
            }
            //Start and position yellow
            waitForStart();
            telemetry.addData(currentPosition,"here now");
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            moveLift("Up", .55);
            prepareNextAction(300);
            //Branches here
            moveRobotForward(150, 2);
            //moveRobotLeft(1800, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                robot.rotateArmUp();
                moveRobotLeft(530, 2);//This value is off
                moveRobotForward(1100, 2);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(500, 2);
                moveRobotLeft(700, 2);
                turnRobotLeft(1070, 2);
                moveRobotRight(200, 2);
                moveRobotForward(430, 0);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotLeft(800, 2);
                moveRobotForward(450, 2);
            }


            else if(target == "Center")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Center");
                moveRobotForward(1638, 50);
                prepareNextAction(200);//new
                sleepTime = 175;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(750, 2);
                moveLift("Down", .55);
                robot.rotateArmUp();
                turnRobotLeft(1070, 2);
                moveRobotForward(1500, 2);
                moveRobotRight(150, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                moveRobotLeft(1100, 2);
                moveRobotForward(450, 2);
            }

            else if(target == "Right Zone")
            {
                telemetry.addData("Going to", "Right ");
                moveRobotForward(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(500, 2);
                moveRobotForward(550, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1450, 2);
                prepareNextAction(200);
                moveLift("Down", .55);
                robot.rotateArmUp();
                prepareNextAction(200);
                turnRobotLeft(2140,2);
                moveRobotForward(700, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                moveRobotLeft(1500, 2);
                moveRobotForward(700, 2);

            } else {
                telemetry.addData("Going to", "Right ");
                moveRobotForward(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(500, 2);
                moveRobotForward(550, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1450, 2);
                prepareNextAction(200);
                moveLift("Down", .55);
                robot.rotateArmUp();
                prepareNextAction(200);
                turnRobotLeft(2140,2);
                moveRobotForward(700, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                moveRobotLeft(1500, 2);
                moveRobotForward(700, 2);

            }


        }
    }

