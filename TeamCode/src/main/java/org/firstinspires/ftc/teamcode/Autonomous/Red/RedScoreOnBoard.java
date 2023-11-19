package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Arm;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Autonomous(group = "Stage", name = "Red score on board")
    public class RedScoreOnBoard extends AutonomousPLUS {
    public Robot robot = null;

        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            lift.moveLift("Up",175);//
            prepareNextAction(300);

            StrafeFromOdometry(0,150,2);

            StrafeFromOdometry(1800,0,2);
            StrafeFromOdometry(-1610,0,2);
            speed = 0.5;//new
            StrafeFromOdometry(0,1050,50);
            prepareNextAction(100);//new
            claw.openClaw();
            speed = 0.5;//new
            StrafeFromOdometry(0,-900,2);
            TurnFromOdometry(90,"Right",2);
            StrafeFromOdometry(250,0,2);
            StrafeFromOdometry(0,1450,50);

            lift. moveLift("Down",175);//new
            prepareNextAction(500);//new
            sleep(500);
            claw.closeClaw();//new
            sleep(500);
            lift.moveLift("Up",175);//new
            StrafeFromOdometry(0,-900,2);
            StrafeFromOdometry(-1160,0,2);
            arm.rotateArmUp();
            prepareNextAction(200);
            speed = 0.4;
            StrafeFromOdometry(0,965,300);
            claw.openClaw();
            prepareNextAction(300);
            lift.moveLift("Down",175);//

            claw.openClaw();
            prepareNextAction(1000);

        }
    }