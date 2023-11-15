package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Arm;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Autonomous(group = "Stage", name = "Red score on board")
    public class RedScoreOnBoard extends AutonomousPLUS {
    public Robot robot = null;
    public Lift lift = new Lift();
    public Hook hook = new Hook();
    public Claw claw = new Claw();
    public Drivetrain DT = new Drivetrain();

    public Arm arm = new Arm();
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            lift.moveLift("Up", .55);//
            prepareNextAction(300);

            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotRight(1800, 2);
            moveRobotLeft(1610, 2); // was 100
            speed = 0.5;//new
            moveRobotForward(1050, 50); //900 was soo close
            prepareNextAction(100);//new
            claw.openClaw();
            speed = 0.5;//new
            moveRobotBackward(900, 2);
            turnRobotRight(1070, 2);// 90 degree turn 1100 was a little t0o far
            moveRobotRight(250, 2);
            moveRobotForward(1450, 50);//new

            lift. moveLift("Down", .22);//new
            prepareNextAction(500);//new
            sleep(500);
            claw.closeClaw();//new
            sleep(500);
            lift.moveLift("Up", .44);//new
            moveRobotBackward(900, 2);//new
            moveRobotLeft(1160, 2);//was 900
            //prepareNextAction(1000);// remove these when testing is done
            //robot.closeClaw();
            //prepareNextAction(500); //remove these when testing is done
            //sleepTime = 190;//prob needs adjusted
            //moveLift("Up", .55);//
            arm.rotateArmUp();
            prepareNextAction(200);
            speed = 0.4;
            moveRobotForward(965, 300);// 1375 was too far
            claw.openClaw();
            prepareNextAction(300);
            lift.moveLift("Down", .55);//

            claw.openClaw();
            prepareNextAction(1000);
            //moveRobotForward(150, 100); // prob needs adjusted

            // code goes here


        }
    }

