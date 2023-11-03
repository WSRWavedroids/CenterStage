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
            robot.rotateRightArm(0.5);
            prepareNextAction(300);
            moveArm("Up", .75);// Down 3.5 before // perfec
            moveRobotLeft(-90, 15);
            moveRobotLeft(90, 15);
            moveRobotForward(90, 15);
            robot.openClaw();
            moveRobotBackward(90, 15);
            moveRobotRight(90, 15);
            // code goes here


        }
    }
