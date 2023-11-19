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
            claw.closeClaw();
            prepareNextAction(300);
            lift.moveLift("Up",120);
            prepareNextAction(300);
            StrafeFromOdometry(0,150,2);

            //robot.rotateRightArm(0.2);

            StrafeFromOdometry(-1800,0,2);
            StrafeFromOdometry(1650,0,2);
            StrafeFromOdometry(0,1080,2);
            claw.openClaw();
            StrafeFromOdometry(0,-900,2);
            StrafeFromOdometry(-1780,0,2);
            StrafeFromOdometry(0,150,2);

        }
    }