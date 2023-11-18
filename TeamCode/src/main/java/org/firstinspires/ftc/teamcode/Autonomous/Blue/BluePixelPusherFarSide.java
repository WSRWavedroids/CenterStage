package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;



    @Autonomous(group = "Stage", name = "Blue Far pixel pusher")
    public class BluePixelPusherFarSide extends AutonomousPLUS {
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 120;
            moveLift("Up", .55);//
            prepareNextAction(300);
            moveRobotRight(150, 2);
            moveRobotForward(1195, 2); //1175 was soo close
            robot.openClaw();
            moveRobotBackward(300, 2);
            moveRobotRight(750, 2);
            moveRobotForward(1350, 2);//1200 was too short
            sleepTime = 120;
            moveLift("Down", .55);//
            turnRobotLeft(1070, 100);
            moveRobotForward(4600, 2);
            //robot.openClaw();
            //moveRobotBackward(150, 2);
            //moveRobotRight(3600, 2);
            // code goes here


        }
    }
