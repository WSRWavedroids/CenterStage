package org.firstinspires.ftc.teamcode.Autonomous.Blue.BlueLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Far Score")
    public class NewBlueFarTFPlacement extends AutonomousPLUS {

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
                telemetry.addData("Going to", "Right");
                //moveRobotLeft(1500, 2);//This value is off
                speed = .70;
                moveRobotForward(1030, 2);
                turnRobotLeft(1060, 2);
                moveRobotForward(555, 2);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(400);
                speed = .60;
                moveRobotBackward(1000, 2);
                prepareNextAction(500);
                moveRobotRight(1200, 2);
                robot.rotateArmDown();
                turnRobotRight(25, 2); // this might be dumb
                prepareNextAction(400);
                speed = 0.90;
                moveRobotForward(3600,2);
                moveRobotLeft(1400,2);
                prepareNextAction(200);
                moveLift("Up",0.55);
                prepareNextAction(200);
                robot.rotateArmUp();
                prepareNextAction(500);
                moveRobotForward(650,2);
                robot.openClaw();
                moveRobotBackward(700, 2);
                robot.rotateArmDown();
                moveLift("Down", 0.55);
                moveRobotLeft(900, 2);
                moveRobotForward(700, 2);

            }


            else if(target == "Center")
            {
                telemetry.addData("Going to", "Center");
                moveRobotRight(150, 2);
                moveRobotForward(1000, 2);

                prepareNextAction(200);//new
                robot.openClaw();
                prepareNextAction(200);

                //moveRobotBackward(900, 2);
                //turnRobotRight(1070, 2);//
                //moveRobotRight(250, 2);
                //moveRobotForward(1450, 50);
            }

            else if(target == "Right Zone")
            {
                telemetry.addData("Going to", "Right");
                moveRobotForward(700, 2);
                moveRobotRight(450, 2);
                prepareNextAction(200);//new
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(400, 2);

            } else {
                //This is certainly not tested
                telemetry.addLine("oooof nothing detected");
                moveRobotForward(200, 2);
                speed = 100;
                //15 360 spins
                turnRobotRight(64200,2);

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

