package org.firstinspires.ftc.teamcode.Autonomous.Legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
@Disabled
@Autonomous(group = "B Legacy", name = "Red Pixel Pusher Board Side")
    public class RedBasicPixelPusherBoardSide extends AutonomousPLUS {
        @Override
        public void runOpMode() throws InterruptedException {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 120;
            moveLift("Up", .55);//
            prepareNextAction(300);
            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotRight(1800, 2);
            moveRobotLeft(1650, 2); // was 100
            moveRobotForward(1080, 2); //900 was soo close
            robot.openClaw();
            moveRobotBackward(900, 2);
            moveRobotRight(1780, 2);
            moveRobotLeft(150, 2);

            // code goes here


        }
    }
