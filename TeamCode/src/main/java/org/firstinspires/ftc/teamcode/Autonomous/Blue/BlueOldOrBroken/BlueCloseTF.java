package org.firstinspires.ftc.teamcode.Autonomous.Blue.BlueOldOrBroken;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Close TF")
    public class BlueCloseTF extends AutonomousPLUS {

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
            moveRobotLeft(1800, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                moveRobotRight(1170, 2);//This value is off
                moveRobotForward(600, 2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(250, 2);
                moveRobotLeft(1200, 2);
            }


            else if(target == "Center")
            {
                telemetry.addData("Going to", "Center");
                moveRobotRight(1530, 2);
                speed = 0.5;//new
                moveRobotForward(1050, 50);
                prepareNextAction(200);//new
                robot.openClaw();
                prepareNextAction(200);
                speed = 0.5;//new
                moveRobotBackward(1000, 2);
                moveRobotLeft(1600, 2);
                moveRobotRight(100, 2);
                //moveRobotBackward(900, 2);
                //turnRobotRight(1070, 2);//
                //moveRobotRight(250, 2);
                //moveRobotForward(1450, 50);
            }

            else if(target == "Right Zone")
            {
                telemetry.addData("Going to", "Right");
                moveRobotRight(1500, 2);//This value is off
                moveRobotForward(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(400, 2);
                moveRobotForward(220, 2);
                prepareNextAction(200);
                robot.openClaw();
                prepareNextAction(200);
                moveRobotBackward(1550, 2);
                moveRobotRight(600, 2);

            } else {
                //This is certainly not tested
                moveRobotRight(1500, 2);//This value is off
                moveRobotForward(700, 2);
                turnRobotRight(1070, 2);
                moveRobotLeft(400, 2);
                moveRobotForward(300, 2);
                robot.openClaw();
                moveRobotBackward(1450, 2);
                telemetry.addLine("I don't know what to do... going left");

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

