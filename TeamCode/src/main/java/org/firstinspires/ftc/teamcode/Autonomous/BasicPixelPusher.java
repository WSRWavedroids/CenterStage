package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

    @Autonomous(group = "Stage", name = "Stage pixel pusher")
    public class BasicPixelPusher extends AutonomousPLUS {
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            moveArm("Up", .85);//
            robot.rotateRightArm(0.2);
            prepareNextAction(300);
            moveRobotRight(900, 15);
            moveRobotLeft(900, 15);
            moveRobotForward(900, 15);
            robot.openClaw();
            moveRobotBackward(900, 15);
            moveRobotRight(900, 15);
            // code goes here


        }
    }
