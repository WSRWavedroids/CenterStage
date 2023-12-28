package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

@Autonomous(group = "D Test", name = "Secondary Claw Test")
    public class SecondaryClawTest extends AutonomousPLUS {
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            robot.closeSecondaryClaw();
            prepareNextAction(800);
            robot.openSecondaryClaw();
            robot.closeSecondaryClaw();
            prepareNextAction(800);
            robot.openSecondaryClaw();
            robot.closeSecondaryClaw();
            prepareNextAction(800);
            robot.openSecondaryClaw();
            robot.closeSecondaryClaw();
            prepareNextAction(800);
            robot.openSecondaryClaw();

            // code goes here


        }
    }


