package org.firstinspires.ftc.teamcode.Autonomous.Legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

@Disabled
@Autonomous(group = "B Legacy", name = "Blue score on board")
    public class BlueScoreOnBoard extends AutonomousPLUS {
        @Override
        public void runOpMode() throws InterruptedException {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            moveLift("Up", .55);//
            prepareNextAction(300);

            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotLeft(1800, 2);
            moveRobotRight(1610, 2); // was 100
            speed = 0.5;//new
            moveRobotForward(1050, 50); //900 was soo close
            prepareNextAction(100);//new
            robot.openClaw();
            speed = 0.5;//new
            moveRobotBackward(900, 2);
            turnRobotLeft(1070, 2);// 90 degree turn 1100 was a little t0o far
            moveRobotLeft(250, 2);
            moveRobotForward(1450, 50);//new

            moveLift("Down", .22);//new
            prepareNextAction(500);//new
            sleep(500);
            robot.closeClaw();//new
            sleep(500);
            moveLift("Up", .44);//new
            moveRobotBackward(900, 2);//new
            moveRobotRight(1160, 2);//was 900
            //prepareNextAction(1000);// remove these when testing is done
            //robot.closeClaw();
            //prepareNextAction(500); //remove these when testing is done
            //sleepTime = 190;//prob needs adjusted
            //moveLift("Up", .55);//
            robot.rotateArmUp();
            prepareNextAction(200);
            speed = 0.4;
            moveRobotForward(700, 300);// perfect
            robot.openClaw();
            prepareNextAction(300);
            moveLift("Down", .55);//

            robot.openClaw();
            prepareNextAction(1000);
            //moveRobotForward(150, 100); // prob needs adjusted

            // code goes here


        }
    }

