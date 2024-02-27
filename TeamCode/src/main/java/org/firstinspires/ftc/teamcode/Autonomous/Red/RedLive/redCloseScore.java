package org.firstinspires.ftc.teamcode.Autonomous.Red.RedLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Close Score")
    public class redCloseScore extends AutonomousPLUS {

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
            //Branches here
            //moveRobotForward(150, 2);
            moveRobotLeft(100, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                moveRobotForward(1150, 2);
                turnRobotLeft(1070, 2);
                moveRobotForward(570, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1470, 2);
                prepareNextAction(200);
                moveLift("Down", .55);
                robot.rotateArmUp();
                prepareNextAction(200);
                turnRobotRight(2140,2);
                moveRobotForward(730, 2);
                moveRobotLeft(275, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(1600, 2);
                moveRobotForward(700, 2);
            }


            else if(target == "Center")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Center");
                moveRobotForward(1780, 50);
                prepareNextAction(200);//new
                sleepTime = 175;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(750, 10);
                moveLift("Down", .55);
                robot.rotateArmUp();
                turnRobotRight(1065, 2);
                prepareNextAction(100);
                moveRobotForward(1620, 2);
                moveRobotLeft(250, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(1250, 2);
                moveRobotForward(450, 2);

            }

            else if(target == "Right Zone")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Right");
                moveRobotRight(640, 2);//This value is off
                moveRobotForward(1375, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(650, 2);
                moveLift("Down", .55);
                moveRobotRight(700, 2);
                turnRobotRight(1070, 2);
                robot.rotateArmUp();

                turnRobotLeft(55, 2);
                moveRobotForward(520, 0);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(800, 2);
                moveRobotForward(450, 2);

            } else {

                telemetry.addData("Going to", "Left");
                moveRobotForward(1150, 2);
                turnRobotLeft(1070, 2);
                moveRobotForward(570, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1470, 2);
                prepareNextAction(200);
                moveLift("Down", .55);
                robot.rotateArmUp();
                prepareNextAction(200);
                turnRobotRight(2140,2);
                moveRobotForward(730, 2);
                moveRobotLeft(275, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(1675, 2);
                moveRobotForward(700, 2);

            }
        }
    }

