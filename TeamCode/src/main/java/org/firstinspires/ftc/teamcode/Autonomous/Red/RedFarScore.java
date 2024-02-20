package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Far Score")
public class RedFarScore extends AutonomousPLUS {

    public TensorFlow TF = new TensorFlow();
    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();
        if (opModeInInit()) {
            //Needs TF reference
            TF.initTfod(robot.hardwareMap);
            while (opModeInInit()) {
                currentPosition = TF.position(TF.tfod);
                telemetry.addData(target, "Its here");
                if (currentPosition != null) {
                    target = currentPosition;
                    telemetry.addLine();
                    telemetry.addData("Its time to", "Gatekeep, Gasslight Girlboss");
                    telemetry.addData("Ur gonna do", "great");
                }
                telemetry.update();

                sleep(20);
            }
        }
        //Start and position yellow
        waitForStart();
        telemetry.addData(currentPosition,"here now");
        robot.claw.closeClaw();
        robot.claw.closeSecondaryClaw();
        prepareNextAction(100);
        robot.arm.rotateArmUp();
        prepareNextAction(100);
        //Branches here
        StrafeFromOdometry(150,"Up",0,2);
        StrafeFromOdometry(50,"Left",0,2);
        telemetry.addData(currentPosition,"still here");

        if(target == "Left Zone")
        {
            telemetry.addData("Going to", "Left");
            StrafeFromOdometry(700,"Up",2,2);
            StrafeFromOdometry(575,"Left",2,2);
            StrafeFromOdometry(450,"Up",1,2);
            prepareNextAction(200);//new
            robot.claw.openSecondaryClaw();
            prepareNextAction(200);
            StrafeFromOdometry(750,"Down",2,2);
            StrafeFromOdometry(585,"Right",2,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1600,"Up",2,2);
            TurnFromOdometry(90,"Right",2);
            StrafeFromOdometry(100,"Left",0,1);
            speed = 1;
            StrafeFromOdometry(3000,"Up",2,2);
            speed = 0.75;
            robot.arm.rotateArmUp();
            StrafeFromOdometry(1000,"Right",2,2);
            robot.lift.moveLift("Up", .50);
            StrafeFromOdometry(620,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(200,"Down",0,2);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(870,"Left",2,2);
            StrafeFromOdometry(500,"Up",1,2);

        }


        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            robot.arm.rotateArmDown();
            StrafeFromOdometry(100,"Left",2,2);
            StrafeFromOdometry(1600,"Up",2,2);
            prepareNextAction(200);//new
            speed = .75;
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(750,"Down",2,2);
            StrafeFromOdometry(900,"Left",2,2);
            StrafeFromOdometry(1200,"Up",2,2);
            TurnFromOdometry(90,"Right",2);
            robot.lift.moveLift("Down",0.55);
            speed = 0.85;
            StrafeFromOdometry(3600,"Up",2,2);
            StrafeFromOdometry(1410,"Right",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up",0.55);
            prepareNextAction(200);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            StrafeFromOdometry(820,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(100,"Down",2,2);
        }

        else if(target == "Right Zone")
        {
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Right");
            //moveRobotLeft(1500, 2);//This value is off
            speed = .80;
            StrafeFromOdometry(830,"Up",2,2);
            TurnFromOdometry(90,"Right",2);
            StrafeFromOdometry(250,"Left",0,2);
            StrafeFromOdometry(540,"Up",2,2);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            StrafeFromOdometry(1000,"Down",2,2);
            StrafeFromOdometry(1300,"Left",2,2);
            robot.arm.rotateArmDown();
            prepareNextAction(200);
            speed = 0.90;
            StrafeFromOdometry(3600,"Up",2,2);
            StrafeFromOdometry(1550,"Right",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up",0.55);
            prepareNextAction(200);
            robot.arm.rotateArmUp();
            prepareNextAction(500);
            StrafeFromOdometry(460,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(150,"Down",0,2);
            robot.arm.rotateArmDown();

        } else {
            telemetry.addData("Going to", "Left");
            StrafeFromOdometry(700,"Up",2,2);
            StrafeFromOdometry(575,"Left",2,2);
            StrafeFromOdometry(450,"Up",1,2);
            prepareNextAction(200);//new
            robot.claw.openSecondaryClaw();
            prepareNextAction(200);
            StrafeFromOdometry(750,"Down",2,2);
            StrafeFromOdometry(585,"Right",2,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1600,"Up",2,2);
            TurnFromOdometry(90,"Right",2);
            StrafeFromOdometry(100,"Left",0,1);
            speed = 1;
            StrafeFromOdometry(3000,"Up",2,2);
            speed = 0.75;
            robot.arm.rotateArmUp();
            StrafeFromOdometry(1000,"Right",2,2);
            robot.lift.moveLift("Up", .50);
            StrafeFromOdometry(620,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(200,"Down",0,2);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(870,"Left",2,2);
            StrafeFromOdometry(500,"Up",1,2);

        }
    }
}
