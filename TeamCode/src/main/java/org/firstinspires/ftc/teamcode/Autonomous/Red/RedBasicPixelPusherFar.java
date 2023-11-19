package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(group = "Stage", name = "Red Far pixel pusher")
public class RedBasicPixelPusherFar extends AutonomousPLUS {

    public Robot robot = null;
    @Override
    public void runOpMode() {

        super.runOpMode();

        //Do this to pass inspection.
        waitForStart();
        robot.claw.closeClaw();
        prepareNextAction(300);
        robot.lift.moveLift("Up",120);//
        prepareNextAction(300);
        StrafeFromOdometry(-150,0,2);
        StrafeFromOdometry(0,1195,2);
        robot.claw.openClaw();
        StrafeFromOdometry(0,-300,2);
        StrafeFromOdometry(-800,0,2);
        StrafeFromOdometry(0,1350,2);
        robot.lift.moveLift("Down",120);//
        TurnFromOdometry(90,"Right",100);
        StrafeFromOdometry(0,4500,2);

    }
}
