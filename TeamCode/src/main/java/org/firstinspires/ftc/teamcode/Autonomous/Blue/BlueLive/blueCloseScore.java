package org.firstinspires.ftc.teamcode.Autonomous.Blue.BlueLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Close Score")
    public class blueCloseScore extends AutonomousPLUS {

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
            robot.closeSecondaryClaw();
            prepareNextAction(300);
            sleepTime = 175;
            //moveLift("Up", .55);
            prepareNextAction(300);
            //Branches here
            moveRobotForward(150, 2);
            //moveRobotLeft(1800, 2);
            telemetry.addData(currentPosition,"still here");
            speed = .75;

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                robot.rotateArmUp();
                moveRobotLeft(700, 1);//This value is off
                moveRobotForward(1100, 1);
                prepareNextAction(150);
                robot.openSecondaryClaw();
                prepareNextAction(150);
                moveRobotBackward(600, 1);
                moveRobotLeft(700, 1);
                speed = .5;
                turnRobotLeft(1070, 1);
                moveRobotRight(350, 1);
                speed = .75;
                moveRobotForward(685, 0);//was 645 before
                prepareNextAction(150);
                robot.openClaw();
                prepareNextAction(150);
                moveRobotBackward(450, 1);
                robot.rotateArmDown();
                moveRobotLeft(1050, 1);
                moveRobotForward(650, 1);
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
                moveLift("Down", .55);
                robot.rotateArmUp();
                turnRobotLeft(1000, 1);
                moveRobotForward(1425, 1);
               // moveRobotRight(150, 1);
               speed = .5;
                moveRobotForward(200, 1);
                robot.openClaw();
               prepareNextAction(50);
               moveRobotBackward(450, 1);
               robot.rotateArmDown();
                moveRobotLeft(1150, 1);
                moveRobotForward(500, 1);
            }

            else if(target == "Right Zone")
            {
                telemetry.addData("Going to", "Right ");
                speed = 0.5;
                moveRobotLeft(100, 1);
                moveRobotForward(1000, 1);
                turnRobotRight(1070, 1);
                moveRobotLeft(100, 1);
                moveRobotForward(565, 1);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                speed = .75;
                moveRobotBackward(1450, 1);
                prepareNextAction(200);
                moveLift("Down", .55);
                robot.rotateArmUp();
                prepareNextAction(200);
                turnRobotLeft(2140,1);
                moveRobotForward(785, 1);
                moveRobotRight(240, 1);
                prepareNextAction(100);
                robot.openClaw();
                prepareNextAction(100);
                robot.rotateArmDown();
                moveRobotBackward(450, 1);
                moveRobotLeft(1540, 1);
                robot.rotateArmDown();
                moveRobotForward(750, 1);

            } else {
                telemetry.addData("Going to", "Left");
                robot.rotateArmUp();
                moveRobotLeft(700, 1);//This value is off
                moveRobotForward(1100, 1);
                prepareNextAction(150);
                robot.openSecondaryClaw();
                prepareNextAction(150);
                moveRobotBackward(600, 1);
                moveRobotLeft(700, 1);
                speed = .5;
                turnRobotLeft(1070, 1);
                moveRobotRight(350, 1);
                speed = .75;
                moveRobotForward(685, 0);//was 645 before
                prepareNextAction(150);
                robot.openClaw();
                prepareNextAction(150);
                moveRobotBackward(450, 1);
                robot.rotateArmDown();
                moveRobotLeft(1050, 1);
                moveRobotForward(650, 1);

            }


        }
    }

