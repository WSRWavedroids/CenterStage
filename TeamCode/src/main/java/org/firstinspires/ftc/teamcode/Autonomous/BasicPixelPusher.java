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
            moveRobotRight(90, 15);
            moveRobotLeft(90, 15);
            moveRobotForward(90, 15);
            robot.openClaw();
            moveRobotBackward(90, 15);
            moveRobotRight(90, 15);
            // code goes here


        }
    }
