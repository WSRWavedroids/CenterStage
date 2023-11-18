package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Autonomous(group = "Stage", name = "Blue Far pixel pusher")
    public class BluePixelPusherFarSide extends AutonomousPLUS {

    public Robot robot = null;
    public Lift lift = new Lift();
    public Claw claw = new Claw();
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            lift.moveLift("Up",120);
            prepareNextAction(300);
            StrafeFromOdometry(150,0,2);
            StrafeFromOdometry(0,1195,2);
            claw.openClaw();
            StrafeFromOdometry(0,-300,2);
            StrafeFromOdometry(800,0,2);
            StrafeFromOdometry(0,1350,2);
            lift.moveLift("Down",120);
            TurnFromOdometry(90,"Left", 100);
            StrafeFromOdometry(0,4500,2);

        }
    }
