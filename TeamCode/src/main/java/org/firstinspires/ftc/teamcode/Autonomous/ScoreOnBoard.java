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
            sleepTime = 250;
            moveLift("Up", .55);//
            prepareNextAction(300);
            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotRight(1800, 2);
            moveRobotLeft(1630, 2); // was 100
            moveRobotForward(1000, 100); //900 was soo close
            robot.openClaw();
            moveRobotBackward(900, 2);
            turnRobotRight(1100, 100);// 90 degree turn
            moveRobotLeft(900, 100);
            robot.closeClaw();
            sleepTime = 700;//prob needs adjusted
            moveLift("Up", .55);//
            robot.rotateArmUp();
            prepareNextAction(1000);
            moveRobotForward(1050, 300);
            robot.openClaw();
            prepareNextAction(300);
            moveLift("Down", .55);//

            robot.openClaw();
            moveRobotForward(150, 100); // prob needs adjusted

            // code goes here


        }
    }

