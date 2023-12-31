package org.firstinspires.ftc.teamcode.Autonomous.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;


@Autonomous(group = "D Test", name = "Pixel Position")
    public class TFPlacingTest extends AutonomousPLUS {
        @Override
        public void runOpMode() throws InterruptedException {

            super.runOpMode();

            //Right
            waitForStart();
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            moveLift("Up", .55);//
            prepareNextAction(300);

            moveRobotForward(150, 2);
            moveRobotRight(1800, 2);


        }
    }

