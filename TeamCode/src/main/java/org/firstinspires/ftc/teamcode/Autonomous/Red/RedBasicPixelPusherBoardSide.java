package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

@Autonomous(group = "Stage", name = "Red Pixel Pusher Board Side")
    public class RedBasicPixelPusherBoardSide extends AutonomousPLUS {

        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            lift.moveLift("Up",120);//
            prepareNextAction(300);
            telemetry.addData("Checkpoint", 1);
            telemetry.update();
            StrafeFromOdometry(0,150,2);
            telemetry.addData("Checkpoint", 2);
            telemetry.update();
            //robot.rotateRightArm(0.2);

            StrafeFromOdometry(1800,0,2);
            telemetry.addData("Checkpoint", 3);
            telemetry.update();
            StrafeFromOdometry(-1650,0,2);
            telemetry.addData("Checkpoint", 4);
            telemetry.update();
            StrafeFromOdometry(0,1080,2);
            telemetry.addData("Checkpoint", 5);
            telemetry.update();
            claw.openClaw();
            telemetry.addData("Checkpoint", 6);
            telemetry.update();
            StrafeFromOdometry(0,-900,2);
            telemetry.addData("Checkpoint", 7);
            telemetry.update();
            StrafeFromOdometry(1780,0,2);
            telemetry.addData("Checkpoint", 8);
            telemetry.update();
            StrafeFromOdometry(-150,0,2);
            telemetry.addData("Checkpoint", 9);
            telemetry.update();

            //Note: this made it through all of them but just shook a lot?

        }
    }