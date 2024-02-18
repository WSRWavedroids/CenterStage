package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Close Score")
public class RedCloseScore extends AutonomousPLUS {

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
        prepareNextAction(300);
        //Branches here
        StrafeFromOdometry(100,"Left",0,1);
        telemetry.addData(currentPosition,"still here");

        if(target == "Left Zone")
        {
            telemetry.addData("Going to", "Left");
            StrafeFromOdometry(1150,"Up",2,2);
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(570,"Up",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(1470,"Down",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            TurnFromOdometry(180,"Right",2);
            StrafeFromOdometry(730,"Up",2,2);
            StrafeFromOdometry(275,"Left",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(450,"Down",0,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1625,"Right",2,2);
            StrafeFromOdometry(700,"Up",2,2);
        }


        else if(target == "Center")
        {
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Center");
            StrafeFromOdometry(1780,"Up",2,2);
            prepareNextAction(200);//new
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(750,"Down",2,2);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            TurnFromOdometry(90,"Right",2);
            prepareNextAction(100);
            StrafeFromOdometry(1620,"Up",2,2);
            StrafeFromOdometry(200,"Left",0,2);
            robot.claw.openClaw();
            StrafeFromOdometry(450,"Down",0,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1100,"Right",2,2);
            StrafeFromOdometry(450,"Up",2,2);


        }

        else if(target == "Right Zone")
        {
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Right");
            StrafeFromOdometry(540,"Right",2,2);
            StrafeFromOdometry(1375,"Up",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(650,"Down",2,2);
            robot.lift.moveLift("Down", .55);
            StrafeFromOdometry(700,"Right",2,2);
            TurnFromOdometry(90,"Right",2);
            robot.arm.rotateArmUp();
            StrafeFromOdometry(200,"Left",0,2);
            StrafeFromOdometry(520,"Up",2,2);
            prepareNextAction(200);
            robot.claw.openClaw();
            prepareNextAction(200);
            StrafeFromOdometry(450,"Down",2,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(800,"Right",2,2);
            StrafeFromOdometry(500,"Up",2,2);

        } else {

            telemetry.addData("Going to", "Left");
            StrafeFromOdometry(1150,"Up",2,2);
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(570,"Up",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(1470,"Down",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            TurnFromOdometry(180,"Right",2);
            StrafeFromOdometry(730,"Up",2,2);
            StrafeFromOdometry(275,"Left",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(450,"Down",0,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1625,"Right",2,2);
            StrafeFromOdometry(700,"Up",2,2);

        }
    }
}
