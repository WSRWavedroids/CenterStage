package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Close Score On Board TF")
    public class RedCloseScoreOnBoardTF extends AutonomousPLUS {

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
            moveRobotForward(150, 2);
            //moveRobotRight(1800, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                moveRobotForward(700, 2);
                turnRobotLeft(1070, 2);
                moveRobotRight(500, 2);
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
                turnRobotRight(2140,2);
                moveRobotForward(700, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                moveRobotRight(1500, 2);
                moveRobotForward(700, 2);
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
                turnRobotRight(1070, 2);
                moveRobotForward(1500, 2);
                moveRobotLeft(150, 2);
                robot.openClaw();
                moveRobotBackward(450, 2);
                moveRobotRight(1100, 2);
                moveRobotForward(450, 2);

            }

            else if(target == "Right Zone")
            {

                telemetry.addData("Going to", "Right");
                robot.rotateArmUp();
                moveRobotRight(530, 2);//This value is off
                moveRobotForward(1100, 2);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(500, 2);
                moveRobotRight(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(200, 2);
                moveRobotForward(430, 0);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(800, 2);
                moveRobotForward(450, 2);

            } else {

                telemetry.addData("Going to", "Right");
                robot.rotateArmUp();
                moveRobotRight(530, 2);//This value is off
                moveRobotForward(1100, 2);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(500, 2);
                moveRobotRight(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(200, 2);
                moveRobotForward(430, 0);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(800, 2);
                moveRobotForward(450, 2);

            }
             /* This scores on the board... values need adjusted
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
            */

        }
    }

