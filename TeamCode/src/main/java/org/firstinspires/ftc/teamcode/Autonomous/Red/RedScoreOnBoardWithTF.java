package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;

@Disabled

@Autonomous(group = "Stage", name = "Red score on board")
    public class RedScoreOnBoardWithTF extends AutonomousPLUS {
        @Override

        public void runOpMode() {




            super.runOpMode();
            //Needs TF reference



            //Start and position yellow
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            moveLift("Up", .55);
            prepareNextAction(300);
            //Branches here
            moveRobotForward(150, 2);
            moveRobotRight(1800, 2);

            //If center
            moveRobotLeft(1610, 2);
            speed = 0.5;//new
            moveRobotForward(1050, 50);
            prepareNextAction(100);//new
            robot.openClaw();
            speed = 0.5;//new
            moveRobotBackward(900, 2);
            turnRobotRight(1070, 2);//
            moveRobotRight(250, 2);
            moveRobotForward(1450, 50);//new
//Center ends here

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
            moveRobotForward(800, 300);// 1375 was too far
            robot.openClaw();
            moveRobotBackward(100, 2);
            prepareNextAction(300);
            moveLift("Down", .55);//

            robot.openClaw();
            prepareNextAction(1000);


        }
    }

