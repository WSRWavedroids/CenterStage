package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Far Place")
public class BlueFarPlace extends AutonomousPLUS {

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
                    telemetry.addData("Its time to", "Gatekeep, Gaslight Girlboss");
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
        prepareNextAction(300);
        prepareNextAction(300);

        //Branches here
        StrafeFromOdometry(150, "Up", 2,2);
        telemetry.addData(currentPosition,"still here");

        if(target == "Left Zone")
        {
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Right");
            speed = .70;
            StrafeFromOdometry(1030,"Up",5,2);
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(555,"Up",2,2);
            prepareNextAction(400);
            robot.claw.openSecondaryClaw();
            robot.arm.rotateArmUp();
            prepareNextAction(700);
            speed = .60;
            StrafeFromOdometry(1000,"Down",5,2);
        }


        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            robot.arm.rotateArmDown();
            StrafeFromOdometry(100,"Right", 2,2);
            StrafeFromOdometry(1600,"Up", 2,2);
            prepareNextAction(200);//new
            speed = .75;
            robot.lift.moveLift("Up",.55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(850,"Down",2,1);
        }

        else if(target == "Right Zone")
        {
            telemetry.addData("Going to", "Right");
            StrafeFromOdometry(950,"Up",5,2);
            StrafeFromOdometry(450,"Right",2,2);
            prepareNextAction(200);//new
            robot.lift.moveLift("Up",.55);
            StrafeFromOdometry(450,"Up",2,2);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(850,"Down",2,1);

        } else {
            telemetry.addData("Going to", "Right");
            StrafeFromOdometry(950,"Up",5,2);
            StrafeFromOdometry(450,"Right",2,2);
            prepareNextAction(200);//new
            robot.lift.moveLift("Up",.55);
            StrafeFromOdometry(450,"Up",2,2);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(850,"Down",2,1);
        }
    }
}
