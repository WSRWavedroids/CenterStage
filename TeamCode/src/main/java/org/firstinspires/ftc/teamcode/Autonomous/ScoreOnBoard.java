package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;


    @Autonomous(group = "Stage", name = "Score on board")
    public class ScoreOnBoard extends AutonomousPLUS {
        @Override
        public void runOpMode() {

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

            moveRobotRight(1800, 2);
            moveRobotLeft(1610, 2); // was 100
            speed = 0.2;//new
            moveRobotForward(1000, 100); //900 was soo close
            prepareNextAction(300);//new
            robot.openClaw();
            speed = 0.4;//new
            moveRobotBackward(900, 2);
            turnRobotRight(1070, 100);// 90 degree turn 1100 was a little t0o far
            moveRobotLeft(1025, 100);//was 900
            prepareNextAction(1000);// remove these when testing is done
            robot.closeClaw();
            prepareNextAction(500); //remove these when testing is done
            //sleepTime = 190;//prob needs adjusted
            //moveLift("Up", .55);//
            robot.rotateArmUp();
            prepareNextAction(1000);
            moveRobotForward(1265, 300);// 1375 was too far
            robot.openClaw();
            prepareNextAction(300);
            moveLift("Down", .55);//

            robot.openClaw();
            prepareNextAction(1000);
            //moveRobotForward(150, 100); // prob needs adjusted

            // code goes here


        }
    }

