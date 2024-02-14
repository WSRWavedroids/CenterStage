package org.firstinspires.ftc.teamcode.Autonomous.Red.RedLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Far Score")
    public class redFarScore extends AutonomousPLUS {

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
                moveRobotRight(585,2);
                robot.rotateArmDown();
                moveRobotForward(1600,2);
                turnRobotRight(1070,2);
                moveRobotLeft(100, 2);
                speed = 1;
                moveRobotForward(3000,2);
               speed = 0.75;
               robot.rotateArmUp();
                moveRobotRight(1000,2);
                sleepTime = 140;
                moveLift("Up", .50);
                moveRobotForward(620,2);
                robot.openClaw();
                moveRobotBackward(200,2);
                moveLift("Down", .55);
                robot.rotateArmDown();
                moveRobotLeft(870,2);
                moveRobotForward(500, 2);

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
                moveRobotLeft(900, 2);
                moveRobotForward(1200, 2);
                turnRobotRight(1060, 2);
                moveLift("Down",0.55);
               speed = 0.85;
                moveRobotForward(3600,2);
                moveRobotRight(1410,2);
               prepareNextAction(200);
               moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
               prepareNextAction(200);
                moveRobotForward(820,2);
                robot.openClaw();
                moveRobotBackward(100,2);
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
                moveRobotLeft(1300, 2);
                robot.rotateArmDown();
                prepareNextAction(200);
                speed = 0.90;
                moveRobotForward(3600,2);
                moveRobotRight(1500,2);
                prepareNextAction(200);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(500);
                moveRobotForward(460,2);
                robot.openClaw();

            } else {
                telemetry.addData("Going to", "Left");
                moveRobotForward(700, 2);
                moveRobotLeft(575, 2);
                moveRobotForward(450,2);
                prepareNextAction(200);//new
                robot.openSecondaryClaw();
                prepareNextAction(200);
                moveRobotBackward(750, 2);
                moveRobotRight(585,2);
                robot.rotateArmDown();
                moveRobotForward(1600,2);
                turnRobotRight(1070,2);
                moveRobotLeft(100, 2);
                speed = 1;
                moveRobotForward(3000,2);
                speed = 0.75;
                robot.rotateArmUp();
                moveRobotRight(1000,2);
                sleepTime = 140;
                moveLift("Up", .50);
                moveRobotForward(620,2);
                robot.openClaw();
                moveRobotBackward(200,2);
                moveLift("Down", .55);
                robot.rotateArmDown();
                moveRobotLeft(870,2);
                moveRobotForward(500, 2);

            }
        }
    }

