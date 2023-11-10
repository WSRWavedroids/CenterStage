package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;


@Autonomous(group = "Stage", name = "Blue Pixel Pusher Board Side")
    public class BlueBasicPixelPusherBoardSide extends AutonomousPLUS {
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeClaw();
            prepareNextAction(56);
            sleepTime = 170;
            moveLift("Up", .55);//
            prepareNextAction(300);
            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotLeft(1800, 2);//Flipped
            moveRobotRight(1650, 2); // was 100
            moveRobotForward(1000, 2); //900 was soo close
            robot.openClaw();
            moveRobotBackward(900, 2);
            moveRobotLeft(1950, 2);
            // code goes here


        }
    }

