package org.firstinspires.ftc.teamcode.Autonomous.Blue.BlueLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Far Score")
    public class BlueFarScore extends AutonomousPLUS {

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
            prepareNextAction(300);
            sleepTime = 175;
            prepareNextAction(300);
            //Branches here
            moveRobotForward(150, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                //This is certainly not tested
                robot.rotateArmDown();
                telemetry.addData("Going to", "Left");
                //moveRobotLeft(1500, 2);//This value is off
                speed = .70;
                moveRobotForward(1030, 2);
                turnRobotLeft(1060, 2);
                moveRobotForward(570, 2);
                prepareNextAction(400);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(700);
                speed = .60;
                moveRobotBackward(1020, 2);
                prepareNextAction(500);
                moveRobotRight(1200, 2);
                robot.rotateArmDown();
                turnRobotRight(35, 2); // this might be dumb
                prepareNextAction(400);
                speed = 0.90;
                moveRobotForward(3600,2);
                turnRobotRight(35, 2); // this might be dumb
                speed = .8;
                moveRobotLeft(1600,2);
                prepareNextAction(200);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(500);
                moveRobotForward(550,2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(550, 2);
                robot.rotateArmDown();
                moveLift("Down", 0.55);
                moveRobotRight(1450, 2);
                moveRobotForward(700, 2);
                moveRobotBackward(1, 0);//stop the robot

            }


            else if(target == "Center")
            {
                telemetry.addData("Going to", "Center");
                robot.rotateArmDown();
                moveRobotRight(100, 2);
                moveRobotForward(1600, 50);
                prepareNextAction(200);//new
                sleepTime = 100;
                speed = .75;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(850, 1);
                moveRobotRight(900, 2);
                moveRobotForward(1400, 2);
                turnRobotLeft(1060, 2);
                moveLift("Down",0.55);
                moveRobotRight(100, 2);
                turnRobotRight(45, 2); // this might be dumb
                speed = 0.85;
                moveRobotForward(3600,2);
                moveRobotLeft(1250,2);
                prepareNextAction(200);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(200);
                moveRobotForward(885,2);
                moveRobotLeft(450, 2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(150,2);
                moveLift("Down",0.55);
                robot.rotateArmDown();
            }

            else if(target == "Right Zone")
            {
                //None of the values after the inital place work. Be  warned
                telemetry.addData("Going to", "Right");
                moveRobotForward(950, 2);
                moveRobotRight(550, 2);
                prepareNextAction(200);//new
                moveLift("Up", .55);
                moveRobotForward(450,2);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(850, 1);
                /*moveRobotLeft(500, 2);
                moveRobotForward(1200, 2);
                turnRobotLeft(1070, 2);
                speed = .9;
                moveRobotForward(3600, 2);
                moveRobotLeft(900, 2);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(500);
                moveRobotForward(700, 2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(450, 2);
                robot.rotateArmDown();
                moveRobotRight(900, 2);
                moveRobotForward(450, 2);
                moveRobotBackward(1,1);
                /*
                 */

            } else {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Left");
                //moveRobotLeft(1500, 2);//This value is off
                speed = .70;
                moveRobotForward(1030, 2);
                turnRobotLeft(1060, 2);
                moveRobotForward(570, 2);
                prepareNextAction(400);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(700);
                speed = .60;
                moveRobotBackward(1020, 2);
                prepareNextAction(500);
                moveRobotRight(1200, 2);
                robot.rotateArmDown();
                turnRobotRight(35, 2); // this might be dumb
                prepareNextAction(400);
                speed = 0.90;
                moveRobotForward(3600,2);
                turnRobotRight(35, 2); // this might be dumb
                speed = .8;
                moveRobotLeft(1600,2);
                prepareNextAction(200);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(500);
                moveRobotForward(550,2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(550, 2);
                robot.rotateArmDown();
                moveLift("Down", 0.55);
                moveRobotRight(1450, 2);
                moveRobotForward(700, 2);
                moveRobotBackward(1, 0);//stop the robot

            }

        }
    }

