package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Autonomous(group = "Stage", name = "Blue Far pixel pusher")
    public class BluePixelPusherFarSide extends AutonomousPLUS {

    public Robot robot = null;
    public Lift lift = new Lift();
    public Hook hook = new Hook();
    public Claw claw = new Claw();
    public Drivetrain DT = new Drivetrain();
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            sleepTime = 120;
            lift.moveLift("Up", .55);//
            prepareNextAction(300);
            StrafeFromOdometry(150,0,2);
            //moveRobotRight(150, 2);
            StrafeFromOdometry(0,1195,2);
            //moveRobotForward(1195, 2); //1175 was soo close
            claw.openClaw();
            StrafeFromOdometry(0,-300,2);
            //moveRobotBackward(300, 2);
            StrafeFromOdometry(800,0,2);
            //moveRobotRight(800, 2);
            StrafeFromOdometry(0,1350,2);
            //moveRobotForward(1350, 2); //1200 was too short
            sleepTime = 120;
            lift.moveLift("Down", .55);//
            TurnFromOdometry(90,"Left", 100);
            //turnRobotLeft(1070, 100);
            StrafeFromOdometry(0,4500,2);
            //moveRobotForward(4500, 2);
            //robot.openClaw();
            //moveRobotBackward(150, 2);
            //moveRobotRight(3600, 2);
            // code goes here


        }
    }
